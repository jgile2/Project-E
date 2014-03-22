package projecte.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class PEItems {
	
	public static Item philosophersStone;
	public static Item alchemicalCoal;
	public static Item KleinStarEin;
	
	public static void registerItems(){
		philosophersStone = new ItemPhilosopherStone();
		GameRegistry.registerItem(philosophersStone, "Philosophers Stone");
		
		alchemicalCoal = new ItemAlchemicalCoal();
		GameRegistry.registerItem(alchemicalCoal, "Alchemical Coal");
		
		KleinStarEin = new ItemKleinStar();
		GameRegistry.registerItem(KleinStarEin, "KleinStarEin");
	}

}
