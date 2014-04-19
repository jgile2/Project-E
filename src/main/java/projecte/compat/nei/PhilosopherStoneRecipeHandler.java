package projecte.compat.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.StatCollector;
import projecte.ModInfo;
import projecte.crafting.PhilosopherStoneCraftingHandler;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.ShapedRecipeHandler;

public class PhilosopherStoneRecipeHandler extends ShapedRecipeHandler {

	private static final List<RecipeInfo> recipes = new ArrayList<RecipeInfo>();

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal(ModInfo.MOD_ID + ".compat.nei.name");
	}

	
	@Override
	public List<PositionedStack> getIngredientStacks(int paramInt) {
		return null;
	}

	@Override
	public List<PositionedStack> getOtherStacks(int paramInt) {
		return null;
	}

	@Override
	public PositionedStack getResultStack(int paramInt) {
		return null;
	}

	@Override
	public void onUpdate() {

	}

	@Override
	public boolean hasOverlay(GuiContainer paramGuiContainer, Container paramContainer, int paramInt) {
		return false;
	}

	@Override
	public IRecipeOverlayRenderer getOverlayRenderer(GuiContainer paramGuiContainer, int paramInt) {
		return null;
	}

	@Override
	public IOverlayHandler getOverlayHandler(GuiContainer paramGuiContainer, int paramInt) {
		return null;
	}

	@Override
	public int recipiesPerPage() {
		return 2;
	}

	@Override
	public List<String> handleTooltip(GuiRecipe paramGuiRecipe, List<String> paramList, int paramInt) {
		return null;
	}

	@Override
	public List<String> handleItemTooltip(GuiRecipe paramGuiRecipe, ItemStack paramItemStack, List<String> paramList, int paramInt) {
		return null;
	}

	@Override
	public boolean keyTyped(GuiRecipe paramGuiRecipe, char paramChar, int paramInt1, int paramInt2) {
		return false;
	}

	@Override
	public boolean mouseClicked(GuiRecipe paramGuiRecipe, int paramInt1, int paramInt2) {
		return false;
	}

	@Override
	public ICraftingHandler getRecipeHandler(String paramString, Object... paramVarArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadCraftingRecipes(String outputId, Object[] results) {
		if (outputId.equals("crafting")) {
			List allrecipes = CraftingManager.getInstance().getRecipeList();
			for (IRecipe irecipe : allrecipes) {
				//PhilosopherStoneCraftingHandler.getResult(items) recipe = null;
				if ((irecipe instanceof ShapedAromicRecipe)) {
					recipe = new ShapedAromicRecipeHandler.CachedShapedRecipe(this, (ShapedAromicRecipe) irecipe);
				}

				if ((recipe == null) || ((ShapedAromicRecipeHandler.CachedShapedRecipe.access$000(recipe)) && (!Config.shouldShowHiddenRecipes()))) {
					continue;
				}
				recipe.computeVisuals();
				this.arecipes.add(recipe);
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	public void loadCraftingRecipes(ItemStack result) {
		List allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.func_77571_b(), result)) {
				ShapedAromicRecipeHandler.CachedShapedRecipe recipe = null;
				if ((irecipe instanceof ShapedAromicRecipe)) {
					recipe = new ShapedAromicRecipeHandler.CachedShapedRecipe(this, (ShapedAromicRecipe) irecipe);
				}

				if ((recipe == null) || ((ShapedAromicRecipeHandler.CachedShapedRecipe.access$000(recipe)) && (!Config.shouldShowHiddenRecipes()))) {
					continue;
				}
				recipe.computeVisuals();
				this.arecipes.add(recipe);
			}
		}
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		List allrecipes = CraftingManager.getInstance().getRecipeList();
		for (IRecipe irecipe : allrecipes) {
			ShapedAromicRecipeHandler.CachedShapedRecipe recipe = null;
			if ((irecipe instanceof ShapedAromicRecipe)) {
				recipe = new ShapedAromicRecipeHandler.CachedShapedRecipe(this, (ShapedAromicRecipe) irecipe);
			}

			if ((recipe == null) || (!recipe.contains(recipe.ingredients, ingredient.field_77993_c)) || ((ShapedAromicRecipeHandler.CachedShapedRecipe.access$000(recipe)) && (!Config.shouldShowHiddenRecipes()))) {
				continue;
			}

			recipe.computeVisuals();
			if (recipe.contains(recipe.ingredients, ingredient)) {
				recipe.setIngredientPermutation(recipe.ingredients, ingredient);
				this.arecipes.add(recipe);
			}
		}
	}
}
