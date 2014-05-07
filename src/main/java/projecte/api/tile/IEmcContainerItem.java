package projecte.api.tile;

import net.minecraft.item.ItemStack;

public interface IEmcContainerItem {

	public int getStoredEmc(ItemStack is);
	public int getMaxStoredEmc(ItemStack is);
	
	public void drain(int amt, ItemStack is);
	public void add(int amt, ItemStack is);
	
}
