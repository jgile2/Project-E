package projecte.items;

import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemRing extends Item {

	public ItemRing() {

		this.setCreativeTab(ProjectE.tab);
		this.setUnlocalizedName(ModInfo.MOD_ID + ".ring");
		
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":ring");
	}
}
