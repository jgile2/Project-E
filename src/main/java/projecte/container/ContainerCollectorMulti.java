package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmcFuel;
import projecte.testing.TileHollowMultiBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCollectorMulti extends Container {

	private TileHollowMultiBlock tile;

	public ContainerCollectorMulti(InventoryPlayer inventory, TileHollowMultiBlock tileentity) {
		this.tile = tileentity;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlotToContainer(new Slot(tileentity, j + i * 2, 20 + j * 18, 8 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;

	}

	@Override
	public boolean mergeItemStack(ItemStack stack, int begin, int end, boolean backwards) {

		int i = backwards ? end - 1 : begin, increment = backwards ? -1 : 1;
		boolean flag = false;
		while (stack.stackSize > 0 && i >= begin && i < end) {
			Slot slot = this.getSlot(i);
			ItemStack slotStack = slot.getStack();
			int slotStacklimit = i < tile.getSizeInventory() ? tile.getInventoryStackLimit() : 64;
			int totalLimit = slotStacklimit < stack.getMaxStackSize() ? slotStacklimit : stack.getMaxStackSize();

			if (slotStack == null) {
				int transfer = totalLimit < stack.stackSize ? totalLimit : stack.stackSize;
				ItemStack stackToPut = stack.copy();
				stackToPut.stackSize = transfer;
				slot.putStack(stackToPut);
				slot.onSlotChanged();
				stack.stackSize -= transfer;
				flag = true;
			} else if (slotStack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == slotStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, slotStack)) {
				int maxTransfer = totalLimit - slotStack.stackSize;
				int transfer = maxTransfer > stack.stackSize ? stack.stackSize : maxTransfer;
				slotStack.stackSize += transfer;
				slot.onSlotChanged();
				stack.stackSize -= transfer;
				flag = true;
			}

			i += increment;
		}

		return flag;

	}

	private int buffer = 0;

//	public void addCraftingToCrafters(ICrafting par1ICrafting) {
//
//		super.addCraftingToCrafters(par1ICrafting);
//		par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.getEmcStored());
//	}
//
//	public void detectAndSendChanges() {
//
//		super.detectAndSendChanges();
//
//		for (int i = 0; i < this.crafters.size(); ++i) {
//			ICrafting icrafting = (ICrafting) this.crafters.get(i);
//
//			if (this.buffer != this.tile.getEmcStored()) {
//				icrafting.sendProgressBarUpdate(this, 0, this.tile.getEmcStored());
//			}
//		}
//
//		this.buffer = this.tile.getEmcStored();
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void updateProgressBar(int par1, int par2) {
//
//		if (par1 == 0) {
//			this.tile.setEmcStored(par2);
//		}
//	}
//
//	

	

}
