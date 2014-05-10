package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;

public class ItemVolcanite extends Item {
	public ItemVolcanite() {
		super();
		this.setUnlocalizedName(ModInfo.MOD_ID + ".Volcanite");
		this.setCreativeTab(ProjectE.tab);

	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":Volcanite");
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
		super.onUpdate(itemstack, world, entity, par4, par5);
		
		
	}
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float xOff, float yOff, float zOff) {
		if(!world.isRemote){
			System.out.println("Side is "+side);
			if(side==1){
				world.setBlock(x, y+1, z, Blocks.flowing_lava);
			}else if(side==2){
				world.setBlock(x, y, z-1, Blocks.flowing_lava);
			}else if(side ==3){
				world.setBlock(x, y, z+1, Blocks.flowing_lava);
			}else if(side ==4){
				world.setBlock(x-1, y, z, Blocks.flowing_lava);
			}else if(side ==5){
				world.setBlock(x+1, y, z, Blocks.flowing_lava);
			}else if(side ==0){
				world.setBlock(x, y-1, z, Blocks.flowing_lava);
			}
			
			

		}
		return super.onItemUse(is, player, world, x, y, z, side, xOff, yOff, zOff);
	}
}
