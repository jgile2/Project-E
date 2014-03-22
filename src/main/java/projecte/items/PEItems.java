package jgile2.mods.projecte.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class PEItems {
	
	public static Item philosophersStone,alchemicalCoal;
	
	public void RegisterItems(){
		philosophersStone = new philosophersStone();
		GameRegistry.registerItem(philosophersStone, "Philosophers Stone");
		
		alchemicalCoal = new alchemicalCoal();
		
		GameRegistry.registerItem(alchemicalCoal, "Alchemical Coal");
	}

}
