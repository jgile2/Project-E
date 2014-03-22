package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projecte.ProjectE;
import projecte.ModInfo;
import projecte.api.tile.IItemEmcBuffer;

public class ItemKleinStar extends Item implements IItemEmcBuffer {
	public ItemKleinStar() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarEin");
		this.setCreativeTab(ProjectE.tab);
		this.setMaxDamage(10000);
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarEin");
	}

	@Override
	public int getStoredEmc(ItemStack is) {
		
		return 0;
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 1;
	}

	@Override
	public void drain(int amt, ItemStack is) {
		
	}

	@Override
	public void add(int amt, ItemStack is) {
		
	}
	
	@Override
	public int getDisplayDamage(ItemStack is) {
		return ((int)(((double)(getMaxStoredEmc(is) - getStoredEmc(is)))/((double)(getMaxStoredEmc(is))) * getMaxDamage()));
	}
	
}
