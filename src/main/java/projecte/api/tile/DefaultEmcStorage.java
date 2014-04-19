package projecte.api.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class DefaultEmcStorage implements IEmcStorage {

	private int maxInput = 0, maxOutput = 0, stored = 0, maxStored = 0;

	public DefaultEmcStorage(int maxInput, int maxOutput, int maxStored) {
		this.maxInput = maxInput;
		this.maxOutput = maxOutput;
		this.maxStored = maxStored;
	}

	@Override
	public int getMaxStored() {
		return maxStored;
	}

	@Override
	public int getStored() {
		return stored;
	}

	@Override
	public int getMaxInput(ForgeDirection side) {
		return maxInput;
	}

	@Override
	public int input(int amount, ForgeDirection side, boolean doInput) {
		int available = maxStored - stored;
		int maxInput = getMaxInput(side);
		int added = Math.max(0, Math.min(Math.min(amount, available), maxInput));
		if (doInput)
			stored += added;
		return added;
	}

	@Override
	public int getMaxOutput(ForgeDirection side) {
		return maxOutput;
	}

	@Override
	public int output(int amount, ForgeDirection side, boolean doOutput) {
		int available = stored;
		int maxOutput = getMaxOutput(side);
		int outputted = Math.max(0, Math.min(Math.min(amount, available), maxOutput));
		if (doOutput)
			stored -= outputted;
		return outputted;
	}

	@Override
	public void setStored(int amount) {
		stored = Math.min(amount, maxStored);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("emcStored", stored);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		stored = tag.getInteger("emcStored");
	}

}
