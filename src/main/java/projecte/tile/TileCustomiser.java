package projecte.tile;

import projecte.items.PEItems;
import projecte.items.armor.ItemDarkArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileCustomiser extends TileEntity implements ISidedInventory {
    private ItemStack[] items = new ItemStack[13];// 91 slots + philosopher

    public TileCustomiser() {

    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.items[i] != null) {
            ItemStack itemstack;

            if (this.items[i].stackSize <= j) {
                itemstack = this.items[i];
                this.items[i] = null;
                return itemstack;
            } else {
                itemstack = this.items[i].splitStack(j);

                if (this.items[i].stackSize == 0) {
                    this.items[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.items[i] != null) {
            ItemStack itemstack = this.items[i];
            this.items[i] = null;
            return itemstack;
        } else {
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.items[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

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
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return true;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        // saveEmcToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                this.items[i].writeToNBT(tag);
                nbttaglist.appendTag(tag);
            }
        }
        // System.out.println("Saving EMC with value: " + this.getEmcStored());
        nbt.setTag("Items", nbttaglist);

    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        // readEmcFromNBT(nbt);

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.items = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbtSlot = nbttaglist.getCompoundTagAt(i);
            int var1 = nbtSlot.getByte("Slot") & 255;

            if (var1 >= 0 && var1 < this.items.length) {
                this.items[var1] = ItemStack.loadItemStackFromNBT(nbtSlot);
            }
        }
        // System.out.println("Reading NBT: " + nbt.getInteger("emcCount"));

    }

    @Override
    public void openInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack var2, int var3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2, int var3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateEntity() {
        if (items[12] != null) {
            for (int i = 0; i < 12; i++) {
                ItemStack currentItem = items[i];
                if (currentItem != null) {
                    if (currentItem.getItem() == PEItems.FlyingRing) {
                        ItemDarkArmor.writeToStack(currentItem, true);
                        
                        System.out.println("Flying ring is in slot: " + i);
                        return;
                    }
                }
            }
        }

    }
}
