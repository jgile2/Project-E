package projecte.blocks;

import net.minecraft.block.Block;
import projecte.blocks.fluid.BlockLiquidEMC;
import projecte.tile.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class PEBlocks {
	public static Block energyCollectorMK1;
	public static Block energyCollectorMK2;
	public static Block energyCollectorMK3;
	public static Block relayMK1;
	public static Block energyCondenser;
	public static Block blockFluidEMC;

	public static void registerBlocks() {
		energyCollectorMK1 = new BlockEnergyCollectorMK1();
		GameRegistry.registerBlock(energyCollectorMK1, energyCollectorMK1.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEnergyCollectorMK1.class, energyCollectorMK1.getUnlocalizedName());
		
		energyCollectorMK2 = new BlockEnergyCollectorMK2();
		GameRegistry.registerBlock(energyCollectorMK2, energyCollectorMK2.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEnergyCollectorMK2.class, energyCollectorMK2.getUnlocalizedName());
		
		energyCollectorMK3 = new BlockEnergyCollectorMK3();
		GameRegistry.registerBlock(energyCollectorMK3, energyCollectorMK3.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEnergyCollectorMK3.class, energyCollectorMK3.getUnlocalizedName());
		
		
		relayMK1 = new BlockRelayMK1();
		GameRegistry.registerBlock(relayMK1, "RelayMK1");
		GameRegistry.registerTileEntity(TileRelayMK1.class, relayMK1.getUnlocalizedName());
		
		energyCondenser = new BlockEnergyCondenser();
		GameRegistry.registerBlock(energyCondenser, "EnergyCondensor");
		GameRegistry.registerTileEntity(TileCondenser.class, energyCondenser.getUnlocalizedName());
		
		blockFluidEMC = new BlockLiquidEMC();
		GameRegistry.registerBlock(blockFluidEMC, "LiquidEMC");
		
	}

}
