package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.tile.TileEnergyCollectorMK1;

public class ContainerEnergyCollectorMK1 extends Container {

	// private TileEnergyCollectorMK1 tile;

	public ContainerEnergyCollectorMK1(InventoryPlayer inventory, TileEnergyCollectorMK1 tileentity) {
		// this.tile = tileentity;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlotToContainer(new SlotEmcFuel(tileentity, j + i * 2, 20 + j * 18, 8 + i * 18));
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

}
