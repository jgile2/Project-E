package projecte.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

public class ModUtils {

	public static ModContainer getActiveMod() {
		return Loader.instance().activeModContainer();
	}

}
