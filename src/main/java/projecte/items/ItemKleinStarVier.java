package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import projecte.ModInfo;

public class ItemKleinStarVier extends ItemKleinStar {
	public ItemKleinStarVier() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarVier");
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarVier");
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 3200000;
	}

}
