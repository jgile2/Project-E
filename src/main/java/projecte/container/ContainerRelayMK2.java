package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmc;
import projecte.container.slot.SlotEmcStorage;
import projecte.tile.TileRelayMK2;

public class ContainerRelayMK2 extends Container {

	//private TileRelayMK1 tile;

	public ContainerRelayMK2(InventoryPlayer inventory, TileRelayMK2 tileentity) {
		//this.tile = tileentity;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				this.addSlotToContainer(new SlotEmc(tileentity, j + i * 2, 26 + j * 18, 18 + i * 18));
			}
		}
		
		this.addSlotToContainer(new SlotEmc(tileentity,6,84,44));
		this.addSlotToContainer(new SlotEmcStorage(tileentity,7,144,44));

		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 16 + j * 18, 101 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 16 + i * 18, 159));
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
