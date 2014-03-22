package projecte.items;

import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemAlchemicalCoal extends Item{
	public ItemAlchemicalCoal(){
		this.setUnlocalizedName(ModInfo.MOD_ID + ".alchemicalCoal");
		this.setCreativeTab(ProjectE.tab);
	}
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":alchemicalCoal");
	}
	
}
