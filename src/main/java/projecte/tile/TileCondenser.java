package projecte.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileCondenser extends TileEntity implements ISidedInventory {

	private ItemStack[] items = new ItemStack[92];

	public int playersCurrentlyUsingChest;
	
	public float lidAngle = 0;
	public float prevLidAngle = 0;

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
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.playersCurrentlyUsingChest);
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());

	}

	@Override
	public void closeInventory() {

	}

	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1) {
			this.playersCurrentlyUsingChest = par2;
			return true;
		} else {
			return super.receiveClientEvent(par1, par2);
		}
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
}
