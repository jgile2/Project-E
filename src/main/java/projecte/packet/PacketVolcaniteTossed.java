package projecte.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketVolcaniteTossed extends PacketBase{
	private int entityId;
	public PacketVolcaniteTossed(int id){
		entityId= id;
	}

	@Override
	public void encode(ByteArrayDataOutput output) {
		output.writeInt(entityId);
		
	}

	@Override
	public void decode(ByteArrayDataInput input) {
		input.readInt();
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionServer(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
