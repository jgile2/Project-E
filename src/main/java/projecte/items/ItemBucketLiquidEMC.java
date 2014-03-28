package projecte.items;

import projecte.ModInfo;
import projecte.ProjectE;
import projecte.blocks.PEBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.world.World;

public class ItemBucketLiquidEMC extends ItemBucket{

	public ItemBucketLiquidEMC() {
		super(PEBlocks.blockFluidEMC);
		this.setUnlocalizedName(ModInfo.MOD_ID + ".BucketLiquidEMC");
		this.setCreativeTab(ProjectE.tab);
		this.setContainerItem(Items.bucket);
	}
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":bucketLiquidEMC");
	}
	
	@Override
	  public boolean tryPlaceContainedLiquid(World world, int x, int y, int z){
		if(!world.isAirBlock(x, y, z)&&world.getBlock(x, y, z).getMaterial().isSolid()){
			return false;
		}else{
			world.setBlock(x, y, z, PEBlocks.blockFluidEMC,0,3);
			return true;
		}
		
	}
}
