package jgile2.mods.projecte;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import jgile2.mods.projecte.items.PEItems;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingEvent {
	int slot;

	public CraftingEvent() {
		System.out.println("calling here");

	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void ItemCraft(ItemCraftedEvent event) {
		IInventory inv = event.craftMatrix;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == PEItems.philosophersStone) {
					ItemStack k = new ItemStack(PEItems.philosophersStone);
					inv.setInventorySlotContents(i, k);
				}
			}
		}
	}

}
