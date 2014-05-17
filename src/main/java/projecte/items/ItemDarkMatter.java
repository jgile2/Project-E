package projecte.items;

import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemDarkMatter extends Item {

	public ItemDarkMatter() {
		this.setUnlocalizedName(ModInfo.MOD_ID + ".darkMatter");
		this.setCreativeTab(ProjectE.tab);
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":darkMatter");
	}

}
