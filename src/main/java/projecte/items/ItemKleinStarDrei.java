package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import projecte.ModInfo;

public class ItemKleinStarDrei extends ItemKleinStar {
	public ItemKleinStarDrei() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarDrei");
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarDrei");
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 800000;
	}

}
