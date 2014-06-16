package projecte.tile;

import cpw.mods.fml.common.FMLCommonHandler;
import projecte.api.emc.EmcData;
import projecte.api.emc.EmcRegistry;
import projecte.api.tile.EmcContainerTile;
import projecte.packet.PacketKey;
import projecte.packet.PacketManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileConverter extends EmcContainerTile implements ISidedInventory {
	private ItemStack[] items = new ItemStack[12];// 91 slots + philosopher

	public TileConverter() {
		this.setMaxEmcStored(100000);
		this.setMaxEmcOutput(20);
		
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
		//saveEmcToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				this.items[i].writeToNBT(tag);
				nbttaglist.appendTag(tag);
			}
		}
		nbt.setInteger("emcCount", this.getEmcStored());
		// System.out.println("Saving EMC with value: " + this.getEmcStored());
		nbt.setTag("Items", nbttaglist);

	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		//readEmcFromNBT(nbt);

		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.items = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbtSlot = nbttaglist.getCompoundTagAt(i);
			int var1 = nbtSlot.getByte("Slot") & 255;

			if (var1 >= 0 && var1 < this.items.length) {
				this.items[var1] = ItemStack.loadItemStackFromNBT(nbtSlot);
			}
		}
		//System.out.println("Reading NBT: " + nbt.getInteger("emcCount"));
		
		this.setEmcStored(nbt.getInteger("emcCount"));
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
		super.updateEntity();
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

		// System.out.println(this.getEmcStored());
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			// System.out.println("Server is"+ this.getEmcStored());

			for (int i = 0; i < items.length; i++) {
				if (items[i] != null) {
					ItemStack stack = items[i];
					EmcData val = EmcRegistry.getValue(stack);
					int value = (int) val.getValue();
					this.setEmcStored(this.getEmcStored() + value);
					if (items[i].stackSize <= 1) {
						items[i] = null;
					} else {
						--items[i].stackSize;

					}
					
				}
			}
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
		
	}

	@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound tag = new NBTTagCompound();

		writeToNBT(tag);
		NetworkManager net = new NetworkManager(true);
		S35PacketUpdateTileEntity pkt =new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
		//onDataPacket(net, pkt);
		return pkt;
	}

}
