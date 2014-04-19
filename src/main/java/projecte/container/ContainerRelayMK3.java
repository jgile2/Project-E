package projecte.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.container.slot.SlotEmc;
import projecte.container.slot.SlotEmcStorage;
import projecte.tile.TileRelayMK3;

public class ContainerRelayMK3 extends Container {

	//private TileRelayMK1 tile;

	public ContainerRelayMK3(InventoryPlayer inventory, TileRelayMK3 tileentity) {
		//this.tile = tileentity;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				this.addSlotToContainer(new SlotEmc(tileentity, j + i * 3, 28 + j * 18, 18 + i * 18));
			}
		}
		
		this.addSlotToContainer(new SlotEmc(tileentity,13,104,58));
		this.addSlotToContainer(new SlotEmcStorage(tileentity,14,164,58));

		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 26 + j * 18, 113 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 26 + i * 18, 171));
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
