package projecte.items;

import projecte.fluid.PEFluids;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
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
	public static Item DestructionCatalyst;
	public static Item AlchemyBag;
	public static Item SwiftwolfRing;
	public static Item Evertide;
	public static Item Volcanite;
	public static Item Test;



	public static void registerItems() {
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
		
		DestructionCatalyst = new ItemDestructionCatalyst();
		GameRegistry.registerItem(DestructionCatalyst, DestructionCatalyst.getUnlocalizedName());
		
		AlchemyBag = new ItemAlchemyBag();
		GameRegistry.registerItem(AlchemyBag, AlchemyBag.getUnlocalizedName());
		
		SwiftwolfRing = new ItemSwiftwolfRing(false);
		GameRegistry.registerItem(SwiftwolfRing, SwiftwolfRing.getUnlocalizedName());
		
		Evertide = new ItemEvertide();
		GameRegistry.registerItem(Evertide, Evertide.getUnlocalizedName());
		
		Volcanite = new ItemVolcanite();
		GameRegistry.registerItem(Volcanite, Volcanite.getUnlocalizedName());

		Test = new ItemTest();
		GameRegistry.registerItem(Test, Test.getUnlocalizedName());
		
		BucketLiquidEMC = new ItemBucketLiquidEMC();
		GameRegistry.registerItem(BucketLiquidEMC, BucketLiquidEMC.getUnlocalizedName());
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(PEFluids.liquidEMC.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(PEItems.BucketLiquidEMC), new ItemStack(Items.bucket)));
	}

}
