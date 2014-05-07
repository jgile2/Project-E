package projecte.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class PacketBase {
	public abstract void encode(ByteArrayDataOutput output);

	public abstract void decode(ByteArrayDataInput input);

	@SideOnly(Side.CLIENT)
	public abstract void actionClient(World world, EntityPlayer player);

	public abstract void actionServer(World world, EntityPlayerMP player);
}
