package jgile2.mods.projecte.handlers;

import jgile2.mods.projecte.items.PEItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuelHandler implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack fuel) {
		Item var1 = fuel.getItem();
		
		if(var1 == PEItems.alchemicalCoal){
			return 50000;
		}else{
			return 0;
		}
	}

}
