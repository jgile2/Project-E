package jgile2.mods.projecte;

import jgile2.mods.projecte.items.testTool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
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
	}
	
	@EventHandler 
	public void init(FMLInitializationEvent event){
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		GameRegistry.registerItem(testItem, "Test Item");
		
	}

}
