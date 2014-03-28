package projecte.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class PEFluids {
	public static Fluid liquidEMC;

	public static void registerFluids() {
		liquidEMC = new Fluid("FluidEMC");
		FluidRegistry.registerFluid(liquidEMC);
		
	}
}
