package projecte.testing;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class PETesting {
	
	public static Block Hollow;

	public static void registerTestBlocks(){
		Hollow = new BlockHollow();
		GameRegistry.registerBlock(Hollow, Hollow.getUnlocalizedName());
	}
	
	public static void registerTestTiles(){
		GameRegistry.registerTileEntity(TileHollowMultiBlock.class,Hollow.getUnlocalizedName());
	}
}
