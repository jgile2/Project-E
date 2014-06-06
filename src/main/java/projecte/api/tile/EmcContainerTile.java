package projecte.api.tile;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import projecte.fluid.PEFluids;

public class EmcContainerTile extends TileEntity implements IFluidHandler {

	public FluidTank tank = new FluidTank(1000);

	protected int maxEmcInput = 0;
	protected int maxEmcOutput = 0;

	public void setMaxEmcInput(int max) {
		maxEmcInput = max;
	}

	public void setMaxEmcOutput(int max) {
		maxEmcOutput = max;
	}

	public void setMaxEmcStored(int max) {
		tank.setCapacity(max);
		setEmcStored(getEmcStored());
	}

	public int getMaxEmcInput() {
		return maxEmcInput;
	}

	public int getMaxEmcOutput() {
		return maxEmcOutput;
	}

	public int getMaxEmcStored() {
		return tank.getCapacity();
	}

	public int getEmcStored() {
		return tank.getFluidAmount();
	}
	
	public void setEmcStored(int amt){
		tank.setFluid(new FluidStack(PEFluids.liquidEMC, Math.min(amt, getMaxEmcStored())));
	
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource.getFluid() != PEFluids.liquidEMC)
			return 0;

		FluidStack res = resource.copy();
		res.amount = Math.min(res.amount, maxEmcInput);
		
		return tank.fill(res, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource.getFluid() != PEFluids.liquidEMC)
			return null;

		return tank.drain(Math.min(resource.amount, maxEmcOutput), doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(Math.min(maxDrain, maxEmcOutput), doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid == PEFluids.liquidEMC;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return fluid == PEFluids.liquidEMC;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}
	
	public void saveEmcToNBT(NBTTagCompound tag){
		tag.setInteger("stored_emc", tank.getFluidAmount());
	}
	
	public void readEmcFromNBT(NBTTagCompound tag){
		setEmcStored(tag.getInteger("stored_emc"));
	}

}
