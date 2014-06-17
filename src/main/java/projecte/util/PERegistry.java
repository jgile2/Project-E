package projecte.util;

import projecte.crafting.ShapedPERecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class PERegistry {
	
	  public static IRecipe getShapedRecipe(ItemStack output, boolean hidden, Object[] input)
	  {
	    return new ShapedPERecipe(output, hidden, input);
	  }

	public static void registerShapedPERecipe(ItemStack item, boolean hidden, Object[] params)
	  {
		System.out.println("registering recipe with item: "+item);
	    GameRegistry.addRecipe(getShapedRecipe(item, hidden, params));
	  }
}
