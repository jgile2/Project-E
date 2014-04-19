package projecte.tile;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import projecte.blocks.PEBlocks;
import projecte.container.ContainerCondensor;

public class TileCondenser extends TileEntity implements ISidedInventory, ITileEmcBuffer {

	private ItemStack[] items = new ItemStack[92];

	public int playersCurrentlyUsingChest;
	private int ticksSinceSync = -1;

	public float lidAngle = 0;
	public float prevLidAngle = 0;

	public TileCondenser() {
		super();

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

		return getStackInSlot(i);

	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		this.items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}

	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				this.items[i].writeToNBT(tag);
				nbttaglist.appendTag(tag);
			}
		}

		nbt.setTag("Items", nbttaglist);

	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", items.length);
		this.items = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbtSlot = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			int var1 = nbtSlot.getByte("Slot") & 255;

			if (var1 >= 0 && var1 < this.items.length) {
				this.items[var1] = ItemStack.loadItemStackFromNBT(nbtSlot);
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
		if (this.playersCurrentlyUsingChest < 0) {
			this.playersCurrentlyUsingChest = 0;
		}

		this.playersCurrentlyUsingChest++;

	}

	@Override
	public void closeInventory() {
		boolean close = true;
		if (this.playersCurrentlyUsingChest == 0) {
			this.playersCurrentlyUsingChest = 0;
		} else {
			this.playersCurrentlyUsingChest--;
		}

	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {

		if (par1 == 1) {
			this.playersCurrentlyUsingChest = par2;
			return true;
		}
		return true;

	}

	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {

		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {

		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
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
		if (worldObj != null && !this.worldObj.isRemote && this.playersCurrentlyUsingChest != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
			// this.playersCurrentlyUsingChest = 0;
			float var1 = 5.0F;
			@SuppressWarnings("unchecked")
			List<EntityPlayer> var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double) ((float) this.xCoord - var1), (double) ((float) this.yCoord - var1), (double) ((float) this.zCoord - var1), (double) ((float) (this.xCoord + 1) + var1), (double) ((float) (this.yCoord + 1) + var1), (double) ((float) (this.zCoord + 1) + var1)));
			Iterator<EntityPlayer> var3 = var2.iterator();

			while (var3.hasNext()) {
				EntityPlayer var4 = var3.next();

				if (var4.openContainer instanceof ContainerCondensor) {
					++this.playersCurrentlyUsingChest;
				}
			}
		}

		if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0) {
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, PEBlocks.energyCondenser, 3, ((playersCurrentlyUsingChest << 3) & 0xF8));
		}

		this.ticksSinceSync++;
		prevLidAngle = lidAngle;
		float f = 0.1F;
		if (playersCurrentlyUsingChest > 0 && lidAngle == 0.0F) {
			double d = (double) xCoord + 0.5D;
			double d1 = (double) zCoord + 0.5D;
			worldObj.playSoundEffect(d, (double) yCoord + 0.5D, d1, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
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
				double d2 = (double) xCoord + 0.5D;
				double d3 = (double) zCoord + 0.5D;
				worldObj.playSoundEffect(d2, (double) yCoord + 0.5D, d3, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
			if (lidAngle < 0.0F) {
				lidAngle = 0.0F;
			}
		}
		createItem();
	}

	private int stored = 0;

	@Override
	public int getStoredEmc() {
		return stored;
	}

	@Override
	public int getMaxStoredEmc() {
		return 10000;
	}

	@Override
	public void drain(int amt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int amt) {
		// TODO Auto-generated method stub

	}

	public void createItem() {

		ItemStack itemCreate = items[0];
		if (items[0] != null) {
			if (items[1] == null) {
				items[1] = itemCreate;
			} else if (items[1] != null) {
				if (items[1].stackSize < 64) {
					items[1].stackSize++;
				}

			}
		}
	}
}
