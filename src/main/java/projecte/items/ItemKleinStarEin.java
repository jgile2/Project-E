package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import projecte.ModInfo;

public class ItemKleinStarEin extends ItemKleinStar {
	public ItemKleinStarEin() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarEin");
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarEin");
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 50000;
	}

}
