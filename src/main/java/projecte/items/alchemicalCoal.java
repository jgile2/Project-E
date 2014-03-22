package jgile2.mods.projecte.items;

import jgile2.mods.projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class alchemicalCoal extends Item{
	public alchemicalCoal(){
		this.setUnlocalizedName("alchemicalCoal");
		this.setCreativeTab(ProjectE.create);
	}
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("ProjectE:alchemicalCoal");
	}
	
}
