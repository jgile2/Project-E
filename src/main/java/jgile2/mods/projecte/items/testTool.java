package jgile2.mods.projecte.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jgile2.mods.projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class testTool extends Item {

	public testTool() {
		super();
		this.setUnlocalizedName("testTool");
		this.setCreativeTab(ProjectE.create);
		this.setTextureName("projecte:test");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("projecte:test");
	}

}