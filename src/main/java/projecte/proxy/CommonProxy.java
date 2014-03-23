package projecte.proxy;

import projecte.blocks.PEBlocks;
import projecte.crafting.Recipes;
import projecte.items.PEItems;

public class CommonProxy {

	public void registerBlocks() {
		PEBlocks.registerBlocks();

	}

	public void registerItems() {
		PEItems.registerItems();

	}

	public void addRecipes() {
		Recipes.registerShapedRecipes();
		Recipes.registerShapelessRecipes();

	}

	public void registerRenders() {

	}

}
