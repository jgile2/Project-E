package projecte.api.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeWrapper {
    
    private List<ItemStack> input  = new ArrayList<ItemStack>();
    private ItemStack       output = null;
    
    private RecipeWrapper(ShapedRecipes recipe) throws Exception {
    
        if (recipe.recipeItems == null || recipe.recipeItems.length == 0 || recipe.getRecipeOutput() == null
                || recipe.getRecipeOutput().stackSize == 0) throw new Exception("Not a valid recipe!");
        
        for (ItemStack i : recipe.recipeItems)
            if (i != null) input.add(i);
        output = recipe.getRecipeOutput();
    }
    
    private RecipeWrapper(ShapelessRecipes recipe) throws Exception {
    
        if (recipe.recipeItems == null || recipe.recipeItems.size() == 0 || recipe.getRecipeOutput() == null
                || recipe.getRecipeOutput().stackSize == 0) throw new Exception("Not a valid recipe!");
        
        for (Object o : recipe.recipeItems)
            if (o != null) input.add((ItemStack) o);
        output = recipe.getRecipeOutput();
    }
    
    private RecipeWrapper(List<ItemStack> input, ItemStack output) {
    
        for (ItemStack is : input)
            if (is != null) this.input.add(is);
        this.output = output;
    }
    
    public List<ItemStack> getInput() {
    
        return input;
    }
    
    public ItemStack getOutput() {
    
        return output;
    }
    
    public void dispose() {
    
        input.clear();
        input = null;
        output = null;
    }
    
    public static RecipeWrapper wrap(ShapedRecipes recipe) {
    
        try {
            return new RecipeWrapper(recipe);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static RecipeWrapper wrap(ShapelessRecipes recipe) {
    
        try {
            return new RecipeWrapper(recipe);
        } catch (Exception e) {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<RecipeWrapper> wrap(ShapedOreRecipe recipe) {
    
        return null;// FIXME PROJECT-E: Make it work with oredict
    }
    
    public static List<RecipeWrapper> wrap(ShapelessOreRecipe recipe) {
    
        return null;// FIXME PROJECT-E: Make it work with oredict
    }
    
    public static List<RecipeWrapper> wrap(IRecipe recipe) {
    
        List<RecipeWrapper> recipes = new ArrayList<RecipeWrapper>();
        
        if (recipe instanceof ShapedRecipes) {
            recipes.add(wrap((ShapedRecipes) recipe));
        } else if (recipe instanceof ShapelessRecipes) {
            recipes.add(wrap((ShapelessRecipes) recipe));
        } else if (recipe instanceof ShapedOreRecipe) {
            return wrap((ShapedOreRecipe) recipe);
        } else if (recipe instanceof ShapelessOreRecipe) { //
            return wrap((ShapelessOreRecipe) recipe);
        }
        
        return recipes;
    }
    
}
