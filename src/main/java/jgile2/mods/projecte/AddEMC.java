package jgile2.mods.projecte;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jgile2.mods.projecte.api.WrappedStack;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
	public static String itemName=null;

	@SuppressWarnings("unused")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void handleItemTooltipEvent(ItemTooltipEvent event) {
		EMCItemValues();
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			WrappedStack stack = new WrappedStack(event.itemStack);

			// event.itemStack= new ItemStack(Items.apple);
			// itemstack = new ItemStack(Items.diamond);
			// System.out.println("Diamond name is "+
			// itemstack.getUnlocalizedName() );
			Item item = (Item) Item.itemRegistry.getObject("diamond");
			for (int i = 0; i < emcValues.size(); i++) {
				String emcVal = emcValues.get(i);
				String[] emcValu = emcVal.split(":");
				itemName = emcValu[0];
				
				switch (event.itemStack.getItem().getUnlocalizedName()) {

				// String name = ((Item)
				// Item.itemRegistry.getObject("diamond")).getUnlocalizedName();
				
				case itemName:
					event.toolTip.add("EMC (Item): " + String.format("%s", emcValu[1]));
				}
			}
		}

	}

	public void EMCItemValues() {
		emcValues.add("item.diamond" + ":" + "2048");
	}
}
