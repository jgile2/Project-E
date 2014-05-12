package projecte.blocks;

import java.io.IOException;

import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockNetherStar extends Block{

	protected BlockNetherStar() {
		super(Material.iron);
		this.setBlockName(ModInfo.MOD_ID + ".netherStar");
		this.setCreativeTab(ProjectE.tab);
		this.setHardness(2F);
		this.setBlockTextureName(ModInfo.MOD_ID+":blockNetherStar");
	}
	
	

}
