package jgile2.mods.projecte;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jgile2.mods.projecte.api.WrappedStack;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * Allows mods using this event to add information to the items tooltip
 */
@SideOnly(Side.CLIENT)
public class AddEMC {
	private static DecimalFormat emcDecimalFormat = new DecimalFormat("###,###,###,###,###.###");
	public List<String> emcValues = new ArrayList<String>();
	public ItemStack itemstack;

	@SuppressWarnings("unused")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void handleItemTooltipEvent(ItemTooltipEvent event) {
		EMCItemValues();
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			WrappedStack stack = new WrappedStack(event.itemStack);
			for (int i = 0; i < emcValues.size(); i++) {
				String[] emcDetails = emcValues.get(i).split(":");
				
				 String item =   emcDetails[0];
				 
				
				
			}
			// event.itemStack= new ItemStack(Items.apple);
			// is = new ItemStack(Items.apple);
			if (emcValues.contains(event.itemStack.getItem())) {
				// EmcValue emcValue =
				// EmcRegistry.getInstance().getEmcValue(stack);

				event.toolTip.add("EMC (Item): " + String.format("%s", "Diamond: 2048"));
				// event.toolTip.add("EMC (Stack): " + String.format("%s",
				// emcDecimalFormat.format(stack.getStackSize() *
				// emcValue.getValue())));

			} else {
				event.toolTip.add("No EMC value");
			}
		}
	}

	public void EMCItemValues() {
		emcValues.add("Diamond:2048");
	}
}
