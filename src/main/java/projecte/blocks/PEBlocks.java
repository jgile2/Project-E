package projecte.blocks;

import net.minecraft.block.Block;
import projecte.tile.TileEnergyCollectorMK1;
import projecte.tile.TileRelayMK1;
import cpw.mods.fml.common.registry.GameRegistry;

public class PEBlocks {
	public static Block EnergyCollectMK1;
	public static Block RelayMK1;

	public static void registerBlocks() {
		EnergyCollectMK1 = new EnergyCollectorMK1();
		GameRegistry.registerBlock(EnergyCollectMK1, EnergyCollectMK1.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEnergyCollectorMK1.class, EnergyCollectMK1.getUnlocalizedName());
		
		RelayMK1 = new RelayMK1();
		GameRegistry.registerBlock(RelayMK1, "RelayMK1");
		GameRegistry.registerTileEntity(TileRelayMK1.class, RelayMK1.getUnlocalizedName());
	}

}
