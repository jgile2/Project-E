package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import projecte.ModInfo;

public class ItemKleinStarSphere extends ItemKleinStar {
	public ItemKleinStarSphere() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarSphere");
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarSphere");
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 12800000;
	}

}
