package jgile2.mods.projecte;

import java.util.ArrayList;
import java.util.List;

import jgile2.mods.projecte.handlers.FurnaceFuelHandler;
import jgile2.mods.projecte.items.PEItems;
import jgile2.mods.projecte.items.testTool;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(name = "Project E", version = "0.0.1", useMetadata = false, modid = "ProjectE")
public class ProjectE {
	
	public static Item testItem;
	public static CreativeTabs create = new CreativeTab("Project E");

	@Instance("ProjectE")
	public static ProjectE instance;
	
	 
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		testItem = new testTool();
		PEItems peItems = new PEItems();
		peItems.RegisterItems();
		GameRegistry.registerItem(testItem, "Test Item");
		//AddEMC emcAdd =new AddEMC(emc,Minecraft.getMinecraft().thePlayer,emcList,true);

		
		MinecraftForge.EVENT_BUS.register(new AddEMC());
		//MinecraftForge.EVENT_BUS.register(new CraftingEvent());
		FMLCommonHandler.instance().bus().register(new CraftingEvent());
	}
	
	@EventHandler 
	public void init(FMLInitializationEvent event){
		GameRegistry.registerFuelHandler(new FurnaceFuelHandler());
		Recipes r = new Recipes();
		r.AddShaped();
		r.AddShapeless();
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}

}
