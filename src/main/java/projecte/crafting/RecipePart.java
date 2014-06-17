package projecte.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class RecipePart {
	public abstract boolean doesItemMatch(ItemStack paramItemStack);

	  @SideOnly(Side.CLIENT)
	  public List<ItemStack> getExamples()
	  {
	    ArrayList list = new ArrayList();
	    addToExampleList(list);
	    return list;
	  }

	  @SideOnly(Side.CLIENT)
	  protected void addToExampleList(ArrayList<ItemStack> list)
	  {
	  }
}
