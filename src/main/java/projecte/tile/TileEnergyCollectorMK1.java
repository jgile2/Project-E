package projecte.tile;

import projecte.api.tile.ITileEmcBuffer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEnergyCollectorMK1 extends TileEntity implements
		ISidedInventory, ITileEmcBuffer {

	private ItemStack[] items = new ItemStack[11];
	public EntityPlayer entity;

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

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
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
			NBTTagCompound nbtSlot = (NBTTagCompound) nbttaglist
					.getCompoundTagAt(i);
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
		entity = entityplayer;
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

		return "";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}
	
	public boolean checkSun(){
		World w = this.worldObj;
		
		if(w.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.glowstone)
			return true;
		
		for(int y = yCoord + 1; y < w.getHeight(); y++){
			Block b = w.getBlock(xCoord, y, zCoord);
			if(b.getLightOpacity(w, xCoord, y, zCoord) > 0)//0 - 255
				return false;
		}
		return true;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		
		writeToNBT(tag);
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}
	
	/* EMC storage */

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
		stored -= Math.max(Math.min(amt, stored), 0);
	}

	@Override
	public void add(int amt) {
		stored += Math.max(Math.min(amt, getMaxStoredEmc() - stored), 0);
	}
	
}