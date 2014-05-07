package projecte.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projecte.ProjectE;
import projecte.api.tile.IEmcContainerItem;

public class ItemKleinStar extends Item implements IEmcContainerItem {
	public ItemKleinStar() {
		this.setCreativeTab(ProjectE.tab);
		this.setMaxStackSize(1);
	}

	@Override
	public int getStoredEmc(ItemStack is) {
		
		return is.getItemDamage();
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 1;
	}

	@Override
	public void drain(int amt, ItemStack is) {
		is.setItemDamage(is.getItemDamage() - amt);
	}

	@Override
	public void add(int amt, ItemStack is) {
		is.setItemDamage(is.getItemDamage() + amt);
	}
	
	@Override
	public int getDisplayDamage(ItemStack is) {
		return getMaxStoredEmc(is) - getStoredEmc(is);
	}
	
	@Override
	public int getMaxDamage(ItemStack stack) {
		return getMaxStoredEmc(stack);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item i, CreativeTabs t, List l) {
		l.add(new ItemStack(this, 1, 0));
		l.add(new ItemStack(this, 1, getMaxStoredEmc(new ItemStack(this, 1, 0))));
	}
	
}
