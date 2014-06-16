package projecte.api.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeHelper {
    
    public static List<ItemStack> getRecipeInputs(IRecipe recipe) {
    
        List<ItemStack> recipeInputs = new ArrayList<ItemStack>();
        
        if (recipe instanceof ShapedRecipes) {
            
            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
            if(shapedRecipe.recipeItems == null)
                return recipeInputs;
            
            for (int i = 0; i < shapedRecipe.recipeItems.length; i++) {
                
                if (shapedRecipe.recipeItems[i] instanceof ItemStack) {
                    
                    ItemStack itemStack = shapedRecipe.recipeItems[i].copy();
                    
                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }
                    
                    recipeInputs.add(itemStack.copy());
                }
            }
        } else if (recipe instanceof ShapelessRecipes) {
            
            ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
            if(shapelessRecipe.recipeItems == null)
                return recipeInputs;
            for (Object object : shapelessRecipe.recipeItems) {
                
                if (object instanceof ItemStack) {
                    
                    ItemStack itemStack = ((ItemStack) object).copy();
                    
                    if (itemStack.stackSize > 1) {
                        itemStack.stackSize = 1;
                    }
                    
                    recipeInputs.add(itemStack.copy());
                }
            }
        } // else if (recipe instanceof ShapedOreRecipe) {
          //
          // ShapedOreRecipe shapedOreRecipe = (ShapedOreRecipe) recipe;
          //
          // for (int i = 0; i < shapedOreRecipe.getInput().length; i++) {
          //
          // /*
          // * If the element is a list, then it is an OreStack
          // */
          // if (shapedOreRecipe.getInput()[i] instanceof ArrayList) {
          // ItemStack oreStack = ((ItemStack)
          // shapedOreRecipe.getInput()[i]).copy();
          //
          // if (oreStack.getItemStack() instanceof OreStack) {
          // recipeInputs.add(oreStack);
          // }
          // } else if (shapedOreRecipe.getInput()[i] instanceof ItemStack) {
          //
          // ItemStack itemStack = ((ItemStack) shapedOreRecipe
          // .getInput()[i]).copy();
          //
          // if (itemStack.stackSize > 1) {
          // itemStack.stackSize = 1;
          // }
          //
          // recipeInputs.add(new ItemStack(itemStack));
          // }
          // }
          // } else if (recipe instanceof ShapelessOreRecipe) {
          //
          // ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe)
          // recipe;
          //
          // for (Object object : shapelessOreRecipe.getInput()) {
          //
          // if (object instanceof ArrayList) {
          // recipeInputs.add(new ItemStack(object));
          // } else if (object instanceof ItemStack) {
          //
          // ItemStack itemStack = ((ItemStack) object).copy();
          //
          // if (itemStack.stackSize > 1) {
          // itemStack.stackSize = 1;
          // }
          //
          // recipeInputs.add(new ItemStack(itemStack));
          // }
          // }
          // }
        
        return recipeInputs;
    }
    
}
