package projecte.handlers;

import java.util.List;

import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.input.Keyboard;

import projecte.ModInfo;
import projecte.api.emc.EmcValue;
import projecte.api.emc.EmcValues;
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

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)
				|| Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

			EmcValue val = EmcValues.getValueForStack(event.itemStack);

			if (val == null) {
				tip.add(Color.RED + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.novalue"));
			} else {
				tip.add(Color.AQUA + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.itemValue")
						+ ": " + Color.GREEN + ((int) val.getValue()));
				tip.add(Color.AQUA + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.stackValue")
						+ ": "
						+ Color.GREEN
						+ ((int) val
								.getValueForStackSize(event.itemStack.stackSize)));
				tip.add("");
				tip.add(Color.WHITE + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.type") + ": "
						+ val.getType().toString());
			}
		} else {
			tip.add(Color.ITALIC + "<" + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.pressShift")
					+ ">");
		}

		int id = OreDictionary.getOreID(event.itemStack);
		String n = OreDictionary.getOreName(id);

		if (id != -1) {
			tip.add(Color.LIGHT_PURPLE + "[DEBUG] Oredict name: " + n);
		}
	}
}