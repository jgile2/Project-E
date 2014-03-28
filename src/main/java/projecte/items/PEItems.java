package projecte.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class PEItems {
	
	public static Item philosophersStone;
	public static Item alchemicalCoal;
	public static Item KleinStarEin;
	public static Item KleinStarZwei;
	public static Item KleinStarDrei;
	public static Item KleinStarVier;
	public static Item KleinStarSphere;
	public static Item KleinStarOmega;
	public static Item BucketLiquidEMC;


	
	public static void registerItems(){
		philosophersStone = new ItemPhilosopherStone();
		GameRegistry.registerItem(philosophersStone, "Philosophers Stone");
		
		alchemicalCoal = new ItemAlchemicalCoal();
		GameRegistry.registerItem(alchemicalCoal, "Alchemical Coal");
		
		KleinStarEin = new ItemKleinStarEin();
		GameRegistry.registerItem(KleinStarEin, KleinStarEin.getUnlocalizedName());
		
		KleinStarZwei = new ItemKleinStarZwei();
		GameRegistry.registerItem(KleinStarZwei, KleinStarZwei.getUnlocalizedName());
		
		KleinStarDrei = new ItemKleinStarDrei();
		GameRegistry.registerItem(KleinStarDrei, KleinStarDrei.getUnlocalizedName());
		
		KleinStarVier = new ItemKleinStarVier();
		GameRegistry.registerItem(KleinStarVier, KleinStarVier.getUnlocalizedName());
		
		KleinStarSphere = new ItemKleinStarSphere();
		GameRegistry.registerItem(KleinStarSphere, KleinStarSphere.getUnlocalizedName());
		
		KleinStarOmega = new ItemKleinStarOmega();
		GameRegistry.registerItem(KleinStarOmega, KleinStarOmega.getUnlocalizedName());
		
		BucketLiquidEMC = new ItemBucketLiquidEMC();
		GameRegistry.registerItem(BucketLiquidEMC, BucketLiquidEMC.getUnlocalizedName());
	}

}
