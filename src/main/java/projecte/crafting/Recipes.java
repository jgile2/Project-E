package projecte.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import projecte.blocks.PEBlocks;
import projecte.items.PEItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void registerShapelessRecipes() {

	}

	public static void registerShapedRecipes() {
		GameRegistry.addRecipe(new ItemStack(PEItems.philosophersStone), new Object[] { "GRG", "RDR", "GRG", 'R', Blocks.redstone_block, 'G', Items.nether_star, 'D', Blocks.diamond_block });
		GameRegistry.addRecipe(new ItemStack(PEItems.philosophersStone), new Object[] { "RGR", "GDG", "RGR", 'R', Blocks.redstone_block, 'G', Items.nether_star, 'D', Blocks.diamond_block });
		GameRegistry.addRecipe(new ItemStack(PEBlocks.energyCollectorMK1), new Object[] { "GgG", "GDG", "GFG", 'G', Blocks.glowstone, 'g', Blocks.glass, 'D', Blocks.diamond_block, 'F', Blocks.furnace });
	}
}