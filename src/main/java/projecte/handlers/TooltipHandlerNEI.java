package projecte.handlers;

import codechicken.nei.guihook.IContainerTooltipHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TooltipHandlerNEI implements IContainerTooltipHandler {

	@Override
	public List<String> handleTooltip(GuiContainer paramGuiContainer, int paramInt1, int paramInt2, List<String> list) {
		return list;
	}

	@Override
	public List<String> handleItemDisplayName(GuiContainer paramGuiContainer, ItemStack paramItemStack, List<String> list) {
		return list;
	}

	@Override
	public List<String> handleItemTooltip(GuiContainer container, ItemStack stack, int id, int Meta, List<String> list) {
		if (stack != null) {
			list.add("Press Shift to view EMC Value");

			if ((GuiScreen.isShiftKeyDown() || GuiScreen.isCtrlKeyDown())) {
				TooltipHandler.getToolTip(list, stack);
			}
		}
		return list;

	}
}
