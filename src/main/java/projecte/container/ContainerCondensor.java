package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmc;
import projecte.container.slot.SlotEmcFuel;
import projecte.tile.TileCondenser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCondensor extends Container {

	private TileCondenser tile;

	public ContainerCondensor(InventoryPlayer inventory, TileCondenser tileentity) {
		this.tile = tileentity;
		tile.openInventory();

		this.addSlotToContainer(new SlotEmc(tileentity, 0, 12, 6));

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 13; j++) {
				this.addSlotToContainer(new SlotEmc(tileentity, 1 + j + i * 2, 12 + j * 18, 26 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 48 + j * 18, 154 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 48 + i * 18, 212));
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

	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		this.tile.closeInventory();
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
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.getStoredEmc());
	}

	public void detectAndSendChanges() {

		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.buffer != this.tile.getStoredEmc()) {
				icrafting.sendProgressBarUpdate(this, 0, this.tile.getStoredEmc());
			}
		}

		this.buffer = this.tile.getStoredEmc();
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {

		if (par1 == 0) {
			this.tile.drain(this.tile.getMaxStoredEmc());
			this.tile.add(par2);
		}
	}

}
