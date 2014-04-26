package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotBag;
import projecte.container.slot.SlotEmcFuel;
import projecte.tile.TileEnergyCollectorMK3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerEnergyCollectorMK3 extends Container {

	private TileEnergyCollectorMK3 tile;

	public ContainerEnergyCollectorMK3(InventoryPlayer inventory, TileEnergyCollectorMK3 tileentity) {
		this.tile = tileentity;
		int currentSlot = inventory.currentItem;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.addSlotToContainer(new SlotEmcFuel(tileentity, j + i * 4, 18 + j * 18, 8 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new SlotBag(currentSlot,inventory, j + i * 9 + 9, 30 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new SlotBag(currentSlot,inventory, i, 30 + i * 18, 142));
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

	public void addCraftingToCrafters(ICrafting par1ICrafting) {

		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.getStored());
	}

	public void detectAndSendChanges() {

		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.buffer != this.tile.getStored()) {
				icrafting.sendProgressBarUpdate(this, 0, this.tile.getStored());
			}
		}

		this.buffer = this.tile.getStored();
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {

		if (par1 == 0) {
			this.tile.setStored(par2);
		}
	}

}
