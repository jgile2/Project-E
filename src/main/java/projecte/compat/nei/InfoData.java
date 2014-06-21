package projecte.compat.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class InfoData
{
  public static List<InfoData> data = new ArrayList();
  public ItemStack item;
  public String[] info;
  public String name;
  public boolean isBlock = false;
  boolean precise = false;

  public InfoData(ItemStack item, String[] info, String name, boolean precise) {
    this.item = item;
    this.info = info;
    this.name = name;

    this.precise = precise;
    this.isBlock = (item.getItem() instanceof ItemBlock);
  }

  public static InfoData add(Object item, String name, String[] info) {
    InfoData newData = null;

    if ((item instanceof ItemStack))
      newData = new InfoData((ItemStack)item, info, name, true);
    else if ((item instanceof Item))
      newData = new InfoData(new ItemStack((Item)item), info, name, false);
    else if ((item instanceof Block)) {
      newData = new InfoData(new ItemStack((Block)item), info, name, false);
    }

    data.add(newData);
    return newData;
  }

  public boolean matches(ItemStack item) {
	  if(item.getItem() ==this.item.getItem()){
			return true;
		}
		System.out.println("item does not match");
		return false;
  }
}
