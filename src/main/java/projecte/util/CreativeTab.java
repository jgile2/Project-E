package projecte.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import projecte.items.PEItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {

	public CreativeTab(String lable) {
		super(lable);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {

		return PEItems.philosophersStone;
	}

}
