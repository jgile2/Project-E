package projecte;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.RecipeSorter;
import projecte.api.emc.EmcRegistry;
import projecte.blocks.PEBlocks;
import projecte.compat.nei.NEIIntergration;
import projecte.crafting.Info;
import projecte.crafting.PhilosopherStoneCraftingHandler;
import projecte.crafting.ShapedPERecipe;
import projecte.event.ArmorEvent;
import projecte.event.CraftingEvent;
import projecte.event.EventCloakRenderer;
import projecte.event.JoinWorld;
import projecte.event.VolcaniteTossEvent;
import projecte.gui.GuiHandler;
import projecte.handlers.FurnaceFuelHandler;
import projecte.handlers.TooltipHandler;
import projecte.handlers.TooltipHandlerNEI;
import projecte.items.armor.PEArmor;
import projecte.packet.PacketManager;
import projecte.proxy.CommonProxy;
import projecte.util.CreativeTab;
import projecte.util.OredictUtil;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.nei.guihook.GuiContainerManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, useMetadata = false)
public class ProjectE {
    
    public static CreativeTabs tab = new CreativeTab(ModInfo.MOD_ID);
    
    @Instance(ModInfo.MOD_ID)
    public static ProjectE     inst;
    
    @SidedProxy(clientSide = "projecte.proxy.ClientProxy", serverSide = "projecte.proxy.CommonProxy", modId = ModInfo.MOD_ID)
    public static CommonProxy  proxy;
    
    public static Logger       log = Logger.getLogger(ModInfo.MOD_NAME);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    
        RecipeSorter.register("ShapedPE", ShapedPERecipe.class, RecipeSorter.Category.SHAPED, "");
        NEIIntergration nei = new NEIIntergration();
        nei.loadConfig();
        /* Register vanilla oredict names */
        OredictUtil.registerVanillaOredict();
        /* Register items and blocks and fluids */
        proxy.registerFluids();
        proxy.registerBlocks();
        proxy.registerItems();
        proxy.registerRenders();
        PEArmor.registerArmor();
//        PEArmor.registerRenderer();
        /* Register channels */
        PacketManager.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    
        PacketManager.init();
        BlockMicroMaterial.createAndRegister(PEBlocks.netherStar, 1);

        /* Register events */
        FMLCommonHandler.instance().bus().register(new CraftingEvent());
        FMLCommonHandler.instance().bus().register(new ArmorEvent());
        //FMLCommonHandler.instance().bus().register(new JoinWorld());
        /* Register handlers */
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            
            MinecraftForge.EVENT_BUS.register(new TooltipHandler());
            EventCloakRenderer.addCapes();
            
            // If NEI is loaded, it will use NEI's system instead
            if (Loader.isModLoaded("NotEnoughItems")) GuiContainerManager.addTooltipHandler(new TooltipHandlerNEI());
            else FMLCommonHandler.instance().bus().register(new TooltipHandler());
            
            /*
             * Disabled due to a different event MinecraftForge.EVENT_BUS.register(new TooltipHandler());
             */
            EventCloakRenderer.addCapes();
            
        }
        
        MinecraftForge.EVENT_BUS.register(new VolcaniteTossEvent());
        // FMLCommonHandler.instance().bus().register(new VolcaniteTossEvent());
        // MinecraftForge.EVENT_BUS.register(new KeyHandler());
        
        GameRegistry.registerFuelHandler(new FurnaceFuelHandler());
        GameRegistry.addRecipe(PhilosopherStoneCraftingHandler.inst);
        /* Register recipes */
        proxy.addRecipes();
        Info.addInfo();
        proxy.registerTiles();
        /* Register GUI handler */
        NetworkRegistry.INSTANCE.registerGuiHandler(inst, new GuiHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    
        /* Register default EMC values */
        EmcRegistry.inst().registerDefault();
    }
    
    @EventHandler
    public void onFinishLoading(FMLLoadCompleteEvent event) {
    
        EmcRegistry.inst().postInit();
    }
    
}
