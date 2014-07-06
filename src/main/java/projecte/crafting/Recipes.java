package projecte.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import projecte.blocks.PEBlocks;
import projecte.compat.nei.InfoData;
import projecte.items.PEItems;
import projecte.util.PERegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void registerShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.alchemicalCoal), PEItems.philosophersStone, Items.coal, Items.coal, Items.coal, Items.coal);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarZwei), PEItems.KleinStarEin, PEItems.KleinStarEin, PEItems.KleinStarEin, PEItems.KleinStarEin);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarDrei), PEItems.KleinStarZwei, PEItems.KleinStarZwei, PEItems.KleinStarZwei, PEItems.KleinStarZwei);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarVier), PEItems.KleinStarDrei, PEItems.KleinStarDrei, PEItems.KleinStarDrei, PEItems.KleinStarDrei);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarSphere), PEItems.KleinStarVier, PEItems.KleinStarVier, PEItems.KleinStarVier, PEItems.KleinStarVier);
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.KleinStarOmega), PEItems.KleinStarSphere, PEItems.KleinStarSphere, PEItems.KleinStarSphere, PEItems.KleinStarSphere);

	}

	public static void registerShapedRecipes() {
		
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.philosophersStone), false, new Object[] { "GRG", "RDR", "GRG", 'R', new ItemStack(Blocks.redstone_block), 'G', new ItemStack(Items.nether_star), 'D', new ItemStack(Blocks.diamond_block) });

		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.philosophersStone), false, new Object[] { "RGR", "GDG", "RGR", 'R', Blocks.redstone_block, 'G', Items.nether_star, 'D', Blocks.diamond_block });
		PERegistry.registerShapedPERecipe(new ItemStack(PEBlocks.energyCollectorMK1), false, new Object[] { "GgG", "GDG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'D', Blocks.diamond_block, 'F', Blocks.furnace });
		PERegistry.registerShapedPERecipe(new ItemStack(PEBlocks.energyCondenser, 1), false, new Object[] { "ODO", "DSD", "ODO", 'O', Blocks.obsidian, 'D', Items.diamond, 'S', PEBlocks.netherStar });
		PERegistry.registerShapedPERecipe(new ItemStack(PEBlocks.netherStar, 1), false, new Object[] { "SSS", "SSS", "SSS", 'S', Items.nether_star });
		PERegistry.registerShapedPERecipe(new ItemStack(PEBlocks.energyCollectorMK2, 1), false, new Object[] { "GgG", "GSG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'S', PEBlocks.netherStar, 'F', Blocks.furnace });
		PERegistry.registerShapedPERecipe(new ItemStack(PEBlocks.energyCollectorMK3, 1), false, new Object[] { "GgG", "GSG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'S', PEBlocks.netherStar, 'F', PEBlocks.energyCollectorMK2 });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.DarkMatter), false, new Object[] { "DDD", "DND", "DDD", 'D', Items.diamond, 'N', PEBlocks.netherStar });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.Evertide), false, new Object[] { "WWW", "DDD", "WWW", 'W', Items.water_bucket, 'D', PEItems.DarkMatter });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.Volcanite), false, new Object[] { "WWW", "DDD", "WWW", 'W', Items.lava_bucket, 'D', PEItems.DarkMatter });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.DestructionCatalyst), false, new Object[] { "TFT", "FSF", "TFT", 'T', Blocks.tnt, 'F', PEItems.alchemicalCoal, 'S', Items.nether_star });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.KleinStarEin), false, new Object[] { "DDD", "DND", "DDD", 'D', PEItems.alchemicalCoal, 'N', PEBlocks.netherStar });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.Pouch), false, new Object[] { "LII", "WCW", "WWW", 'L', Items.lead, 'I', Items.iron_ingot, 'W', Blocks.wool, 'C', Blocks.chest });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.FlyingRing), false, new Object[] { "TFT", "FSF", "TFT", 'T', PEItems.DarkMatter, 'F', Items.feather, 'S', PEItems.Ring });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.Ring), false, new Object[] { "DDD", "DND", "DDD", 'D', Items.iron_ingot, 'N', PEItems.Volcanite });
		PERegistry.registerShapedPERecipe(new ItemStack(PEItems.Ring), false, new Object[] { "DDD", "DND", "DDD", 'D', Items.iron_ingot, 'N', Items.lava_bucket });

	}
}
