package projecte.compat.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import projecte.ModInfo;
import projecte.crafting.ShapedPERecipe;
import baubles.common.Config;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class ShapedPERecipeHandler extends ShapedRecipeHandler {
	public String getRecipeName() {
		return "Project E Shaped";
	}

	public void loadCraftingRecipes(String outputId, Object[] results) {
		if (outputId.equals("crafting")) {
			List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();

			for (IRecipe irecipe : allrecipes) {
				ShapedPERecipeHandler.CachedShapedRecipe recipe = null;
				if ((irecipe instanceof ShapedPERecipe)) {
					recipe = new ShapedPERecipeHandler.CachedShapedRecipe(this, (ShapedPERecipe) irecipe);

				}

				if ((recipe == null)) {
					continue;
				}

				recipe.computeVisuals();
				this.arecipes.add(recipe);
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	public void loadCraftingRecipes(ItemStack result)
	  {
	    List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();

	    for (IRecipe irecipe : allrecipes)
	      if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result))
	      {
	        ShapedPERecipeHandler.CachedShapedRecipe recipe = null;
	        if ((irecipe instanceof ShapedPERecipe)) {
	          recipe = new ShapedPERecipeHandler.CachedShapedRecipe(this, (ShapedPERecipe)irecipe);
	        }

	        if ((recipe == null))
	        {
	          continue;
	        }

	        recipe.computeVisuals();
	        this.arecipes.add(recipe);
	      }
	  }

	public void loadUsageRecipes(ItemStack ingredient) {
		List<IRecipe> allrecipes = CraftingManager.getInstance().getRecipeList();

		for (IRecipe irecipe : allrecipes) {
			ShapedPERecipeHandler.CachedShapedRecipe recipe = null;
			if ((irecipe instanceof ShapedPERecipe)) {
				recipe = new ShapedPERecipeHandler.CachedShapedRecipe(this, (ShapedPERecipe) irecipe);
			}

			if ((recipe == null)) {
				continue;
			}

			recipe.computeVisuals();
			if (recipe.contains(recipe.ingredients, ingredient)) {
				recipe.setIngredientPermutation(recipe.ingredients, ingredient);
				this.arecipes.add(recipe);
			}
		}
	}
	
	@Override
	public String getGuiTexture() {
		return ModInfo.MOD_ID + ":textures/gui/shaped.png";
	}

	public class CachedShapedRecipe extends TemplateRecipeHandler.CachedRecipe {
		public ArrayList<PositionedStack> ingredients;
		public PositionedStack result;

		public CachedShapedRecipe(ShapedPERecipeHandler ShapedPERecipeHandler, int width, int height, Object[] items, ItemStack out) {
			super();
			this.result = new PositionedStack(out, 119, 24);
			this.ingredients = new ArrayList();
			setIngredients(width, height, items);
			
		}

		public CachedShapedRecipe(ShapedPERecipeHandler paramShapedPERecipeHandler, ShapedPERecipe recipe) {
			this(paramShapedPERecipeHandler, recipe.getRecipeWidth(), recipe.getRecipeHeight(), recipe.getInput(), recipe.getRecipeOutput());
		}

		public void setIngredients(int width, int height, Object[] items) {
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++) {
					if (items[(y * width + x)] == null)
						continue;
					PositionedStack stack;
					if (((items[(y * width + x)] instanceof List)) && (((List) items[(y * width + x)]).isEmpty())) {
						stack = new PositionedStack(new ItemStack(Blocks.fire), 25 + x * 18, 6 + y * 18, false);
					} else {
						if ((items[(y * width + x)] instanceof RecipePart)) {
							stack = new PositionedStack(((RecipePart) items[(y * width + x)]).getExamples(), 25 + x * 28, 6 + y * 18, false);
						} else {
							stack = new PositionedStack(items[(y * width + x)], 25 + x * 18, 6 + y * 18, false);
						}
					}

					stack.setMaxSize(1);
					this.ingredients.add(stack);
				}
		}

		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, this.ingredients);
		}

		public PositionedStack getResult() {
			return this.result;
		}

		public void computeVisuals() {
			for (PositionedStack p : this.ingredients) {
				p.generatePermutations();
			}

			this.result.generatePermutations();
		}
	}
}