package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmc;
import projecte.tile.TileCustomiser;

public class ContainerCustomiser extends Container{
	private TileCustomiser tile;

	
	public ContainerCustomiser(EntityPlayer player, TileCustomiser tileentity){
		this.tile = tileentity;
		tile.openInventory();

		this.addSlotToContainer(new Slot(tileentity,12,25,34));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				this.addSlotToContainer(new Slot(tileentity, j + i * 4, 79 + j * 18, 16 + i * 18));
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
	@Override
	protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
		return false;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return null;
	}
}
