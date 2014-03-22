package projecte;

import java.util.EnumMap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import projecte.api.emc.EmcValues;
import projecte.blocks.PEBlocks;
import projecte.crafting.PhilosopherStoneCraftingHandler;
import projecte.crafting.Recipes;
import projecte.event.CraftingEvent;
import projecte.gui.GuiHandler;
import projecte.handlers.FurnaceFuelHandler;
import projecte.handlers.TooltipHandler;
import projecte.items.PEItems;
import projecte.packet.ChannelHandler;
import projecte.util.CreativeTab;
import projecte.util.OredictUtil;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = "0.0.1", useMetadata = false)
public class ProjectE {

	public static CreativeTabs tab = new CreativeTab(ModInfo.MOD_ID);

	@Instance(ModInfo.MOD_ID)
	public static ProjectE inst;

	public EnumMap<Side, FMLEmbeddedChannel> channels;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		/* Register vanilla oredict names */
		OredictUtil.registerVanillaOredict();

		/* Register items and blocks */
		PEItems.registerItems();
		PEBlocks.registerBlocks();

		/* Register default EMC values */
		EmcValues.registerDefault();

		/* Register channels */
		channels = NetworkRegistry.INSTANCE.newChannel(ModInfo.MOD_ID, new ChannelHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		/* Register events */
		FMLCommonHandler.instance().bus().register(new CraftingEvent());

		/* Register handlers */
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			MinecraftForge.EVENT_BUS.register(new TooltipHandler());
		GameRegistry.registerFuelHandler(new FurnaceFuelHandler());
		GameRegistry.addRecipe(PhilosopherStoneCraftingHandler.inst);

		/* Register recipes */
		Recipes.registerShapedRecipes();
		Recipes.registerShapelessRecipes();

		/* Register GUI handler */
		NetworkRegistry.INSTANCE.registerGuiHandler(inst, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void onFinishLoading(FMLLoadCompleteEvent event) {
		// FIXME generate recipes
	}

}
