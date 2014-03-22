package projecte.event;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projecte.items.PEItems;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingEvent {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onCraft(ItemCraftedEvent event) {

		IInventory inv = event.craftMatrix;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null
						&& j.getItem() == PEItems.philosophersStone) {
					ItemStack k = new ItemStack(PEItems.philosophersStone, 2);

					inv.setInventorySlotContents(i, k);
				}
			}
		}
	}

}
