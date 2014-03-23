package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import projecte.ModInfo;

public class ItemKleinStarZwei extends ItemKleinStar {
	public ItemKleinStarZwei() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarZwei");
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarZwei");
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 200000;
	}

}
