package projecte.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import projecte.api.emc.EmcRegistry;
import projecte.api.emc.StackEmcValue;
import projecte.api.tile.EmcContainerTile;
import projecte.api.tile.IEmcContainerItem;
import projecte.blocks.PEBlocks;
import projecte.container.ContainerCondenser;
import projecte.fluid.PEFluids;
import projecte.items.PEItems;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileCondenser extends EmcContainerTile implements IInventory {

    private ItemStack[] items = new ItemStack[94]; // 91 slots + philosopher
                                                   // stone + reference item +
                                                   // EMC storage

    public int playersCurrentlyUsingChest;
    private int ticksSinceSync = -1;

    public float lidAngle = 0;
    public float prevLidAngle = 0;

    public TileCondenser() {

        super();

        setMaxEmcInput(40);
    }

    @Override
    public int getSizeInventory() {

        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return items[i];

    }

    @Override
    public ItemStack decrStackSize(int i, int j) {

        if (items[i] != null) {
            ItemStack itemstack;

            if (items[i].stackSize <= j) {
                itemstack = items[i];
                items[i] = null;
                return itemstack;
            } else {
                itemstack = items[i].splitStack(j);

                if (items[i].stackSize == 0) {
                    items[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }

    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        if (items[i] != null) {
            ItemStack itemstack = items[i];
            items[i] = null;
            return itemstack;
        } else {
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {

        items[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                items[i].writeToNBT(tag);
                nbttaglist.appendTag(tag);
            }
        }

        nbt.setTag("Items", nbttaglist);

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        super.readFromNBT(nbt);
        // readEmcFromNBT(nbt);

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        items = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbtSlot = nbttaglist.getCompoundTagAt(i);
            int var1 = nbtSlot.getByte("Slot") & 255;

            if (var1 >= 0 && var1 < items.length) {
                items[var1] = ItemStack.loadItemStackFromNBT(nbtSlot);
            }
        }
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return true;
    }

    @Override
    public void openInventory() {

        if (playersCurrentlyUsingChest < 0) {
            playersCurrentlyUsingChest = 0;
        }

        playersCurrentlyUsingChest++;

    }

    @Override
    public void closeInventory() {

        if (playersCurrentlyUsingChest == 0) {
            playersCurrentlyUsingChest = 0;
        } else {
            playersCurrentlyUsingChest--;
        }

    }

    @Override
    public boolean receiveClientEvent(int par1, int par2) {

        if (par1 == 1) {
            playersCurrentlyUsingChest = par2;
            return true;
        }
        return true;

    }

    @Override
    public void invalidate() {

        super.invalidate();
        updateContainingBlockInfo();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return true;
    }

    @Override
    public String getInventoryName() {

        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {

        return false;
    }

    @Override
    public void updateEntity() {

        super.updateEntity();
        // Resynchronize clients with the server state
        if (worldObj != null && !worldObj.isRemote && playersCurrentlyUsingChest != 0 && (ticksSinceSync + xCoord + yCoord + zCoord) % 200 == 0) {
            // this.playersCurrentlyUsingChest = 0;
            float var1 = 5.0F;
            @SuppressWarnings("unchecked")
            List<EntityPlayer> var2 = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(xCoord - var1, yCoord - var1, zCoord - var1, xCoord + 1 + var1, yCoord + 1 + var1, zCoord + 1 + var1));
            Iterator<EntityPlayer> var3 = var2.iterator();

            while (var3.hasNext()) {
                EntityPlayer var4 = var3.next();

                if (var4.openContainer instanceof ContainerCondenser) {
                    ++playersCurrentlyUsingChest;
                }
            }
        }

        if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, PEBlocks.energyCondenser, 3, ((playersCurrentlyUsingChest << 3) & 0xF8));
        }

        ticksSinceSync++;
        prevLidAngle = lidAngle;
        float f = 0.1F;
        if (playersCurrentlyUsingChest > 0 && lidAngle == 0.0F) {
            double d = xCoord + 0.5D;
            double d1 = zCoord + 0.5D;
            worldObj.playSoundEffect(d, yCoord + 0.5D, d1, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }
        if (playersCurrentlyUsingChest == 0 && lidAngle > 0.0F || playersCurrentlyUsingChest > 0 && lidAngle < 1.0F) {
            float f1 = lidAngle;
            if (playersCurrentlyUsingChest > 0) {
                lidAngle += f;
            } else {
                lidAngle -= f;
            }
            if (lidAngle > 1.0F) {
                lidAngle = 1.0F;
            }
            float f2 = 0.5F;
            if (lidAngle < f2 && f1 >= f2) {
                double d2 = xCoord + 0.5D;
                double d3 = zCoord + 0.5D;
                worldObj.playSoundEffect(d2, yCoord + 0.5D, d3, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }
            if (lidAngle < 0.0F) {
                lidAngle = 0.0F;
            }
        }

        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (canConvert())
                doConversion();
        }
    }

    private boolean canConvert() {
        ItemStack resultItem = getStackInSlot(items.length - 2);
        StackEmcValue val = EmcRegistry.inst().getEmcValue(resultItem);
        if (resultItem != null) {
            return getStackInSlot(items.length - 1) != null && getStackInSlot(items.length - 1).getItem() == PEItems.philosophersStone && val.getValue() >= 0;
        }
        return false;
    }

    public void doConversion() {

        ItemStack resultItem = getStackInSlot(items.length - 2);

        if (resultItem == null)
            return;

        List<ItemStack> items = new ArrayList<ItemStack>();
        int resultSlot = -1;

        for (int i = 0; i < this.items.length - 3; i++) {
            ItemStack is = getStackInSlot(i);
            if (is != null && is.stackSize > 0) {
                if (!resultItem.isItemEqual(is)) {
                    items.add(is);
                } else {
                    if (is.stackSize < is.getMaxStackSize())
                        if (resultSlot < 0)
                            resultSlot = i;
                }
            } else {
                if (resultSlot < 0)
                    resultSlot = i;
            }
        }

        double value = EmcRegistry.inst().getValue(resultItem);
        if (getEmcStored() >= value) {
            if (resultSlot >= 0) {
                ItemStack is = getStackInSlot(resultSlot);
                if (is == null) {
                    is = resultItem.copy();
                    is.stackSize = 1;
                } else {
                    is.stackSize++;
                }
                setInventorySlotContents(resultSlot, is);
                ItemStack item = getStackInSlot(this.items.length - 3);
                if (item != null && item.getItem() instanceof IEmcContainerItem) {
                    IEmcContainerItem cont = (IEmcContainerItem) item.getItem();
                    cont.drain((int) value, item);
                    items.clear();
                    return;
                }
            }
        }

        int val = 0;
        for (ItemStack is : items) {
            if (val < value) {
                double d = EmcRegistry.inst().getValue(is);
                for (int i = 0; i < is.stackSize; i++)
                    if (val < value)
                        val += d;
            }
        }

        if (val < value)
            return;
        int val2 = 0;
        for (ItemStack is : items) {
            if (val2 < val) {
                double d = EmcRegistry.inst().getValue(is);
                for (int i = 0; i < is.stackSize; i++) {
                    if (val2 < val) {
                        val2 += d;
                        is.stackSize--;
                    }
                }
                if (is.stackSize == 0) {
                    for (int i = 0; i < this.items.length; i++) {
                        ItemStack is2 = this.items[i];
                        if (is2 == is) {
                            this.items[i] = null;
                            break;
                        }
                    }
                }
            }
        }

        if (resultSlot >= 0) {
            ItemStack is = getStackInSlot(resultSlot);
            if (is == null) {
                is = resultItem.copy();
                is.stackSize = 1;
            } else {
                is.stackSize++;
            }
            setInventorySlotContents(resultSlot, is);
        }
        setEmcStored((int) (val - value));
    }

    @Override
    public void setEmcStored(int amt) {

        ItemStack item = getStackInSlot(items.length - 3);
        if (item != null && item.getItem() instanceof IEmcContainerItem) {
            ((IEmcContainerItem) item.getItem()).drain(((IEmcContainerItem) item.getItem()).getStoredEmc(item), item);
            ((IEmcContainerItem) item.getItem()).add(amt, item);
        }
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

        if (resource.getFluid() != PEFluids.liquidEMC)
            return 0;

        ItemStack item = getStackInSlot(items.length - 3);
        if (item != null && item.getItem() instanceof IEmcContainerItem) {
            int amt = Math.min(Math.min(resource.amount, maxEmcInput), ((IEmcContainerItem) item.getItem()).getMaxStoredEmc(item) - ((IEmcContainerItem) item.getItem()).getStoredEmc(item));

            if (doFill) {
                ((IEmcContainerItem) item.getItem()).add(amt, item);
            }

            return amt;
        }
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {

        if (resource.getFluid() != PEFluids.liquidEMC)
            return null;

        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {

        ItemStack item = getStackInSlot(items.length - 3);
        if (item != null && item.getItem() instanceof IEmcContainerItem) {
            int amt = Math.min(Math.min(maxDrain, maxEmcOutput), ((IEmcContainerItem) item.getItem()).getStoredEmc(item));

            if (doDrain) {
                ((IEmcContainerItem) item.getItem()).drain(amt, item);
            }

            return new FluidStack(PEFluids.liquidEMC, amt);
        }

        return null;
    }

    @Override
    public int getEmcStored() {

        ItemStack item = getStackInSlot(items.length - 3);
        if (item != null && item.getItem() instanceof IEmcContainerItem)
            return ((IEmcContainerItem) item.getItem()).getStoredEmc(item);

        return 0;
    }

    @Override
    public int getMaxEmcStored() {

        ItemStack item = getStackInSlot(items.length - 3);
        if (item != null && item.getItem() instanceof IEmcContainerItem)
            return ((IEmcContainerItem) item.getItem()).getMaxStoredEmc(item);

        return 0;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {

        ItemStack item = getStackInSlot(items.length - 3);
        if (item != null && item.getItem() instanceof IEmcContainerItem)
            return new FluidTankInfo[] { new FluidTankInfo(new FluidStack(PEFluids.liquidEMC, ((IEmcContainerItem) item.getItem()).getStoredEmc(item)), Math.max(((IEmcContainerItem) item.getItem()).getMaxStoredEmc(item), 1)) };
        return new FluidTankInfo[] { new FluidTankInfo(new FluidStack(PEFluids.liquidEMC, 0), 0) };
    }

}
