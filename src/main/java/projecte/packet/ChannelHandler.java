package projecte.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import projecte.ProjectE;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<IPacket> {
	public ChannelHandler() {
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, IPacket packet,
			ByteBuf data) throws Exception {
		byte[] bytes = CompressedStreamTools.compress(packet.writeNBT());
		data.writeBytes(bytes);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data,
			IPacket packet) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
				data.array()));

		try {
			NBTTagCompound tag = CompressedStreamTools.read(dis);
			packet.readNBT(tag);
		} catch (IOException e) {
		}

		packet.handle();
	}

	public void send(IPacket packet) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			ProjectE.inst.channels.get(Side.CLIENT)
					.attr(FMLOutboundHandler.FML_MESSAGETARGET)
					.set(FMLOutboundHandler.OutboundTarget.TOSERVER);
			ProjectE.inst.channels.get(Side.CLIENT).writeOutbound(packet);
		} else {
			ProjectE.inst.channels.get(Side.SERVER)
					.attr(FMLOutboundHandler.FML_MESSAGETARGET)
					.set(FMLOutboundHandler.OutboundTarget.ALL);
			ProjectE.inst.channels.get(Side.SERVER).writeOutbound(packet);
		}
	}
}