package projecte.proxy;

import projecte.blocks.PEBlocks;
import projecte.crafting.Recipes;
import projecte.fluid.PEFluids;
import projecte.items.PEItems;
import projecte.testing.PETesting;

public class CommonProxy {

	public void registerBlocks() {
		PEBlocks.registerBlocks();
		PETesting.registerTestBlocks();
	}

	public void registerItems() {
		PEItems.registerItems();

	}

	public void addRecipes() {
		Recipes.registerShapedRecipes();
		Recipes.registerShapelessRecipes();

	}

	public void registerFluids() {
		PEFluids.registerFluids();
	}

	public void registerRenders() {

	}

	public void registerTiles() {
		PEBlocks.registerTiles();
		PETesting.registerTestTiles();
	}
	


}
