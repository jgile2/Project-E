package projecte.container;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmc;
import projecte.container.slot.SlotEmcFuel;
import projecte.items.DataItemAlchemyBag;
import projecte.items.PEItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAlChest extends Container {
	private EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	private DataItemAlchemyBag tile = new DataItemAlchemyBag(player,new ItemStack(PEItems.AlchemyBag));

	public ContainerAlChest(InventoryPlayer inventory) {
		tile.openInventory();

		

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 13; j++) {
				this.addSlotToContainer(new Slot(tile, j + i*13, 12 + j * 18, 5 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 48 + j * 18, 152 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 48 + i * 18, 210));
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

}
