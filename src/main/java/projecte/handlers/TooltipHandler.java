package projecte.handlers;

import java.util.List;

import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.input.Keyboard;

import projecte.ModInfo;
import projecte.api.emc.EmcData;
import projecte.api.emc.EmcRegistry;
import projecte.api.tile.IEmcContainerItem;
import projecte.util.Color;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TooltipHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void handleItemTooltipEvent(ItemTooltipEvent event) {
		List<String> tip = event.toolTip;
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

			EmcData val = EmcRegistry.getValue(event.itemStack);

			if (val == null) {
				tip.add(Color.RED + "This item hasn't got an EMC value");
			} else {
				tip.add(Color.AQUA + "Item EMC value" + ": " + Color.GREEN + ((int) val.getValue()));
				tip.add(Color.AQUA + "Stack EMC value" + ": " + Color.GREEN + ((int) val.getValue(event.itemStack.stackSize)));
				tip.add("");
				tip.add(Color.WHITE + "Type of item" + ": " + val.getType());
			}

			if (event.itemStack.getItem() instanceof IEmcContainerItem) {
				IEmcContainerItem b = (IEmcContainerItem) event.itemStack.getItem();

				tip.add(Color.GOLD + "Stored EMC" + ": " + Color.GREEN + b.getStoredEmc(event.itemStack));
				tip.add(Color.GOLD + "Max stored EMC" + ": " + Color.GREEN + b.getMaxStoredEmc(event.itemStack));
			}
		} else {
			tip.add(Color.ITALIC + "<" + "Press shift to see the EMC value" + ">");
		}

		int id = OreDictionary.getOreID(event.itemStack);
		String n = OreDictionary.getOreName(id);

		if (id != -1) {
			// tip.add(Color.LIGHT_PURPLE + "[DEBUG] Oredict name: " + n);
		}
	}
}