package projecte.packet;

import projecte.testing.TileHollowMultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class PacketCollector extends PacketBase {
	int storedEmc;
	TileHollowMultiBlock tile;
	int masterX;
	int masterY;
	int masterZ;

	public PacketCollector() {
		// TODO Auto-generated constructor stub
	}

	public PacketCollector(int storedEMC, World world, int masterX, int masterY, int masterZ) {
		storedEmc = storedEMC;
		this.masterX = masterX;
		this.masterY = masterY;
		this.masterZ = masterZ;
		// tile = (TileHollowMultiBlock) world.getTileEntity(masterX, masterY,
		// masterZ);

	}

	@Override
	public void encode(ByteArrayDataOutput output) {
		output.writeInt(storedEmc);
		output.writeInt(masterX);
		output.writeInt(masterY);
		output.writeInt(masterZ);

	}

	@Override
	public void decode(ByteArrayDataInput input) {
		storedEmc = input.readInt();
		masterX = input.readInt();
		masterY = input.readInt();
		masterZ = input.readInt();

		//System.out.println(storedEmc);

	}

	@Override
	public void actionClient(World world, EntityPlayer player) {
		// System.out.println(masterX);
		tile = (TileHollowMultiBlock) world.getTileEntity(masterX, masterY, masterZ);

		if (tile == null) {
			System.out.println("tile is null");
		}
		if (tile != null) {
			tile.setEmcStored(storedEmc);
		}

	}

	@Override
	public void actionServer(World world, EntityPlayer player) {
		// TODO Auto-generated method stub

	}

}
