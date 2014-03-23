package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import projecte.ModInfo;

public class ItemKleinStarOmega extends ItemKleinStar {
	public ItemKleinStarOmega() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".kleinStarOmega");
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":kleinStarOmega");
	}

	@Override
	public int getMaxStoredEmc(ItemStack is) {
		return 51200000;
	}

}
