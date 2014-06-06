package projecte.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketConverter extends PacketBase{
	int emc;
	public PacketConverter(int emcValue){
		this.emc = emcValue;
	}

	@Override
	public void encode(ByteArrayDataOutput output) {
		output.writeInt(emc);
		
	}

	@Override
	public void decode(ByteArrayDataInput input) {
		emc = input.readInt();
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionServer(World world, EntityPlayerMP player) {
		// TODO Auto-generated method stub
		
	}

}
