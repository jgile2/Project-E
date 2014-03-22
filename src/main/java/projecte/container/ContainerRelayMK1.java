package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.tile.TileRelayMK1;

public class ContainerRelayMK1 extends Container {

	//private TileRelayMK1 tile;

	public ContainerRelayMK1(InventoryPlayer inventory, TileRelayMK1 tileentity) {
		//this.tile = tileentity;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				this.addSlotToContainer(new SlotEmc(tileentity, j + i * 2, 27 + j * 18, 17 + i * 18));
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 95 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 153));
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
