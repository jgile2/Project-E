package projecte.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import projecte.blocks.PEBlocks;
import projecte.event.EventCloakRenderer;
import projecte.handlers.KeyHandler;
import projecte.render.ItemRenderCondensor;
import projecte.render.RenderCondenser;
import projecte.tile.TileCondenser;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileCondenser.class, new RenderCondenser());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PEBlocks.energyCondenser), new ItemRenderCondensor());
		MinecraftForge.EVENT_BUS.register(new EventCloakRenderer());

		FMLCommonHandler.instance().bus().register(new KeyHandler());

	}

}
