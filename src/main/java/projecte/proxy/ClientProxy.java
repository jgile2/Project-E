package projecte.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import projecte.blocks.PEBlocks;
import projecte.render.ItemRenderCondensor;
import projecte.render.RenderCondenser;
import projecte.tile.TileCondenser;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileCondenser.class, new RenderCondenser());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PEBlocks.energyCondenser), new ItemRenderCondensor());

	}

}
