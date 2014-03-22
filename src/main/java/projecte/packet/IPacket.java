package projecte.packet;

import net.minecraft.nbt.NBTTagCompound;

public interface IPacket {

	public NBTTagCompound writeNBT();
	
	public void readNBT(NBTTagCompound nbt);
	
	public void handle();
	
}
