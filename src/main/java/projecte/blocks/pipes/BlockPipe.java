package projecte.blocks.pipes;

import projecte.ModInfo;
import projecte.ProjectE;
import projecte.blocks.PEBlocks;
import projecte.tile.TilePipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPipe extends BlockContainer{

	public BlockPipe() {
		super(Material.iron);
		this.setBlockName(ModInfo.MOD_ID + ".pipe");
		this.setCreativeTab(ProjectE.tab);
		this.setHardness(2F);
		this.setStepSound(Block.soundTypeStone);
		//this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.9375F, 0.9375F);

	}
	
	
	
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TilePipe();
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderBlockAsNormal() {
		return false;

	}
	
	@Override
	public int getRenderType() {
		return -1;
	}

}
