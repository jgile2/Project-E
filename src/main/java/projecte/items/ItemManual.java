package projecte.items;

import projecte.ModInfo;
import projecte.ProjectE;
import projecte.util.GuiIds;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemManual extends Item{
	public ItemManual() {
		this.setCreativeTab(ProjectE.tab);
		this.setUnlocalizedName(ModInfo.MOD_ID + ".manual");
		
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":manual");
	}
	
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.openGui(ProjectE.inst, GuiIds.Manual, world, player.inventory.currentItem, 0, 0);
        return super.onItemRightClick(par1ItemStack, world, player);
    }
}
