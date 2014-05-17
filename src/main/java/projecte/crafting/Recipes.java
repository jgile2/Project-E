package projecte.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import projecte.blocks.PEBlocks;
import projecte.items.PEItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void registerShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.alchemicalCoal), PEItems.philosophersStone,Items.coal,Items.coal,Items.coal,Items.coal);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarZwei), PEItems.KleinStarEin,PEItems.KleinStarEin,PEItems.KleinStarEin,PEItems.KleinStarEin);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarDrei), PEItems.KleinStarZwei,PEItems.KleinStarZwei,PEItems.KleinStarZwei,PEItems.KleinStarZwei);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarVier), PEItems.KleinStarDrei,PEItems.KleinStarDrei,PEItems.KleinStarDrei,PEItems.KleinStarDrei);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarSphere), PEItems.KleinStarVier,PEItems.KleinStarVier,PEItems.KleinStarVier,PEItems.KleinStarVier);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarOmega), PEItems.KleinStarOmega,PEItems.KleinStarOmega,PEItems.KleinStarOmega,PEItems.KleinStarOmega);

	}

	public static void registerShapedRecipes() {
		GameRegistry.addRecipe(new ItemStack(PEItems.philosophersStone), new Object[] { "GRG", "RDR", "GRG", 'R', Blocks.redstone_block, 'G', Items.nether_star, 'D', Blocks.diamond_block });
		GameRegistry.addRecipe(new ItemStack(PEItems.philosophersStone), new Object[] { "RGR", "GDG", "RGR", 'R', Blocks.redstone_block, 'G', Items.nether_star, 'D', Blocks.diamond_block });
		GameRegistry.addRecipe(new ItemStack(PEBlocks.energyCollectorMK1), new Object[] { "GgG", "GDG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'D', Blocks.diamond_block, 'F', Blocks.furnace });
		GameRegistry.addRecipe(new ItemStack(PEBlocks.energyCondenser, 1), new Object[] { "ODO", "DSD", "ODO", 'O', Blocks.obsidian, 'D', Items.diamond, 'S', PEBlocks.netherStar });
		GameRegistry.addRecipe(new ItemStack(PEBlocks.netherStar, 1), new Object[] { "SSS", "SSS", "SSS", 'S', Items.nether_star });
		GameRegistry.addRecipe(new ItemStack(PEBlocks.energyCollectorMK2, 1), new Object[] { "GgG", "GSG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'S', PEBlocks.netherStar, 'F', Blocks.furnace });
		GameRegistry.addRecipe(new ItemStack(PEBlocks.energyCollectorMK3, 1), new Object[] { "GgG", "GSG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'S', PEBlocks.netherStar, 'F', PEBlocks.energyCollectorMK2 });
		GameRegistry.addRecipe(new ItemStack(PEItems.DarkMatter), new Object[] { "DDD", "DND", "DDD", 'D', Items.diamond, 'N', PEBlocks.netherStar });
		GameRegistry.addRecipe(new ItemStack(PEItems.Evertide), new Object[] { "WWW", "DDD", "WWW", 'W', Items.water_bucket, 'D', PEItems.DarkMatter });
		GameRegistry.addRecipe(new ItemStack(PEItems.Volcanite), new Object[] { "WWW", "DDD", "WWW", 'W', Items.lava_bucket, 'D', PEItems.DarkMatter });
		GameRegistry.addRecipe(new ItemStack(PEItems.DestructionCatalyst),new Object[]{"TFT","FSF","TFT",'T',Blocks.tnt,'F',PEItems.alchemicalCoal,'S',Items.nether_star});
		GameRegistry.addRecipe(new ItemStack(PEItems.KleinStarEin), new Object[] { "DDD", "DND", "DDD", 'D', PEItems.alchemicalCoal, 'N', PEBlocks.netherStar });
		GameRegistry.addRecipe(new ItemStack(PEItems.Pouch), new Object[]{"LII","WCW","WWW",'L',Items.lead,'I',Items.iron_ingot,'W',Blocks.wool,'C',Blocks.chest});
		GameRegistry.addRecipe(new ItemStack(PEItems.FlyingRing),new Object[]{"TFT","FSF","TFT",'T',PEItems.DarkMatter,'F',Items.feather,'S',PEItems.Ring});
		GameRegistry.addRecipe(new ItemStack(PEItems.Ring), new Object[] { "DDD", "DND", "DDD", 'D', Items.iron_ingot, 'N', PEItems.Volcanite});
		GameRegistry.addRecipe(new ItemStack(PEItems.Ring), new Object[] { "DDD", "DND", "DDD", 'D', Items.iron_ingot, 'N', Items.lava_bucket});

	}
}
