package projecte.fluid;

import net.minecraftforge.fluids.Fluid;
import projecte.ModInfo;

public class FluidEMC extends Fluid {
	public FluidEMC() {
		super(ModInfo.MOD_ID + ".fluidEMC");
		this.setLuminosity(10);
		this.setDensity(1000);
		this.setTemperature(300);
		this.setUnlocalizedName(ModInfo.MOD_ID + ".fluidEMC");

		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getColor() {
		return 0x7489FC;//0xRRGGBB
	}
}
