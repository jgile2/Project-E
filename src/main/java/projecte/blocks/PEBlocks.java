package projecte.blocks;

import net.minecraft.block.Block;
import projecte.blocks.fluid.BlockLiquidEMC;
import projecte.tile.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class PEBlocks {
	public static Block energyCollectorMK1;
	public static Block energyCollectorMK2;
	public static Block energyCollectorMK3;
	public static Block CollectorMK1;
	public static Block Converter;


	public static Block CollectorCore;
	public static Block relayMK1;
	public static Block relayMK2;
	public static Block relayMK3;
	public static Block energyCondenser;
	public static Block netherStar;
	public static Block blockFluidEMC;

	public static void registerBlocks() {
		energyCollectorMK1 = new BlockEnergyCollectorMK1();
		GameRegistry.registerBlock(energyCollectorMK1, energyCollectorMK1.getUnlocalizedName());
		
		energyCollectorMK2 = new BlockEnergyCollectorMK2();
		GameRegistry.registerBlock(energyCollectorMK2, energyCollectorMK2.getUnlocalizedName());
		
		energyCollectorMK3 = new BlockEnergyCollectorMK3();
		GameRegistry.registerBlock(energyCollectorMK3, energyCollectorMK3.getUnlocalizedName());
		
		CollectorCore = new BlockCollectorCore();
		//GameRegistry.registerBlock(CollectorCore, CollectorCore.getUnlocalizedName());
		
		CollectorMK1 = new BlockCollectorMK1();
		//GameRegistry.registerBlock(CollectorMK1, CollectorMK1.getUnlocalizedName());
		
		Converter = new BlockConverter();
		GameRegistry.registerBlock(Converter, Converter.getUnlocalizedName());
		
		relayMK1 = new BlockRelayMK1();
		//GameRegistry.registerBlock(relayMK1, "RelayMK1");
		
		relayMK2 = new BlockRelayMK2();
		//GameRegistry.registerBlock(relayMK2, "RelayMK2");
		
		relayMK3 = new BlockRelayMK3();
		//GameRegistry.registerBlock(relayMK3, "RelayMK3");
		
		energyCondenser = new BlockEnergyCondenser();
		GameRegistry.registerBlock(energyCondenser, "EnergyCondensor");
		
		netherStar = new BlockNetherStar();
		GameRegistry.registerBlock(netherStar, netherStar.getUnlocalizedName());
		
		blockFluidEMC = new BlockLiquidEMC();
		GameRegistry.registerBlock(blockFluidEMC, "LiquidEMC");
		
	}
	
	public static void registerTiles(){
		GameRegistry.registerTileEntity(TileEnergyCollectorMK1.class, energyCollectorMK1.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEnergyCollectorMK2.class, energyCollectorMK2.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileEnergyCollectorMK3.class, energyCollectorMK3.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileRelayMK1.class, relayMK1.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileRelayMK2.class, relayMK2.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileRelayMK3.class, relayMK3.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileCondenser.class, energyCondenser.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileCollectorCore.class, CollectorCore.getUnlocalizedName());
		GameRegistry.registerTileEntity(TileConverter.class, Converter.getUnlocalizedName());
		

	}

}
