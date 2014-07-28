package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmc;
import projecte.container.slot.SlotEmcStorage;
import projecte.container.slot.SlotPhilosopherStone;
import projecte.tile.TileCondenser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerCondenser extends Container {

	private TileCondenser tile;

	public ContainerCondenser(InventoryPlayer inventory, TileCondenser tileentity) {
		this.tile = tileentity;
		tile.openInventory();

		this.addSlotToContainer(new SlotEmc(tileentity, tileentity.getSizeInventory() - 2, 30, 6));
		this.addSlotToContainer(new SlotPhilosopherStone(tileentity, tileentity.getSizeInventory() - 1, 228, 6));
		this.addSlotToContainer(new SlotEmcStorage(tileentity, tileentity.getSizeInventory() - 3, 206, 6));

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 13; j++) {
				this.addSlotToContainer(new SlotEmc(tileentity, j + i * 13, 12 + j * 18, 26 + i * 18));
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
	    ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i < 7 * 13)
            {
                if (!this.mergeItemStack(itemstack1, 7 * 13, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1,3, 94, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;


	}

	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		this.tile.closeInventory();
	}

//	@Override
//	public boolean mergeItemStack(ItemStack stack, int begin, int end, boolean backwards) {
//
//		int i = backwards ? end - 1 : begin, increment = backwards ? -1 : 1;
//		boolean flag = false;
//		while (stack.stackSize > 0 && i >= begin && i < end) {
//			Slot slot = this.getSlot(i);
//			ItemStack slotStack = slot.getStack();
//			int slotStacklimit = i < tile.getSizeInventory() ? tile.getInventoryStackLimit() : 64;
//			int totalLimit = slotStacklimit < stack.getMaxStackSize() ? slotStacklimit : stack.getMaxStackSize();
//
//			if (slotStack == null) {
//				int transfer = totalLimit < stack.stackSize ? totalLimit : stack.stackSize;
//				ItemStack stackToPut = stack.copy();
//				stackToPut.stackSize = transfer;
//				slot.putStack(stackToPut);
//				slot.onSlotChanged();
//				stack.stackSize -= transfer;
//				flag = true;
//			} else if (slotStack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getItemDamage() == slotStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, slotStack)) {
//				int maxTransfer = totalLimit - slotStack.stackSize;
//				int transfer = maxTransfer > stack.stackSize ? stack.stackSize : maxTransfer;
//				slotStack.stackSize += transfer;
//				slot.onSlotChanged();
//				stack.stackSize -= transfer;
//				flag = true;
//			}
//
//			i += increment;
//		}
//
//		return flag;
//
//	}

	private int buffer = 0;

	public void addCraftingToCrafters(ICrafting par1ICrafting) {

		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.getEmcStored());
	}

	public void detectAndSendChanges() {

		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.buffer != this.tile.getEmcStored()) {
				icrafting.sendProgressBarUpdate(this, 0, this.tile.getEmcStored());
			}
		}

		this.buffer = this.tile.getEmcStored();
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {

		if (par1 == 0) {
			this.tile.setEmcStored(par2);
		}
	}
	
}
