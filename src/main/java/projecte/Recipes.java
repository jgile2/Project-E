package jgile2.mods.projecte;

import jgile2.mods.projecte.items.PEItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public void AddShapeless() {
		GameRegistry.addShapelessRecipe(new ItemStack(PEItems.alchemicalCoal),PEItems.philosophersStone,Items.coal,Items.coal,Items.coal,Items.coal);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal,4),PEItems.philosophersStone,PEItems.alchemicalCoal);

	}

	public void AddShaped() {
		GameRegistry.addRecipe(new ItemStack(PEItems.philosophersStone), new Object[] { "GRG", "RDR", "GRG", 'R', Items.redstone, 'G', Items.nether_star, 'D', Blocks.diamond_block });
		GameRegistry.addRecipe(new ItemStack(PEItems.philosophersStone), new Object[] { "RGR", "GDG", "RGR", 'R', Items.redstone, 'G', Items.nether_star, 'D', Blocks.diamond_block });

	}
}
