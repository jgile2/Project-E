package projecte.compat.nei;

import java.util.logging.Level;

import net.minecraft.util.StatCollector;

import projecte.ModInfo;
import projecte.ProjectE;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class NEIIntergration implements IConfigureNEI{
	public void loadConfig() {
		if ((!Loader.isModLoaded("NotEnoughItems")) || (FMLCommonHandler.instance().getSide().isServer())) {
			ProjectE.log.log(Level.INFO, "Did not load NEI compatibility because it can not be found");
			return;
		}
		try {
			//PhilosopherStoneRecipeHandler philosopherStone = new PhilosopherStoneRecipeHandler();
			API.registerRecipeHandler(new ShapedPERecipeHandler());
			API.registerUsageHandler(new ShapedPERecipeHandler());
			ProjectE.log.log(Level.INFO, "Loaded NEI compatibility.");
		} catch (Exception e) {
			ProjectE.log.log(Level.INFO, "Could not load NEI compatibility.");
		}
	}

	@Override
	public String getName() {
		return StatCollector.translateToLocal(ModInfo.MOD_ID + ".compat.nei.name");
	}

	@Override
	public String getVersion() {
		return ModInfo.MOD_VERSION;
	}

}
