package projecte.proxy;

import projecte.render.RenderCondenser;
import projecte.tile.TileCondenser;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileCondenser.class, new RenderCondenser());
	}

}
