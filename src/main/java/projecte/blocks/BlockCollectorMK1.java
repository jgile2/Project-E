package projecte.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.tile.TileCollectorCore;
import projecte.tile.TileEnergyCollectorMK1;
import projecte.tile.TileEnergyCollectorMK3;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCollectorMK1 extends BlockContainer {

	protected BlockCollectorMK1() {
		super(Material.iron);
		this.setBlockName(ModInfo.MOD_ID + ".CollectorMK1");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int[] core = findCore(world, x, y, z);
		System.out.println(core);
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, ProjectE.inst, 2, world, core[0], core[1], core[2]);

		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileCollectorCore();
	}

	public int[] findCore(World world, int xCoord, int yCoord, int zCoord) {
		int x = xCoord;
		int y = yCoord;
		int z = zCoord;

		boolean addX = true;
		boolean addY = false;
		boolean addZ = false;
		// System.out.println("first");
		if (world.getBlock(x, y + 1, z) != PEBlocks.CollectorCore) {

			if (world.getBlock(x, y, z) != PEBlocks.CollectorMK1) {

				if (world.getBlock(x, y, z + 1) != PEBlocks.CollectorMK1) {
					if (world.getBlock(x, y, z - 1) != PEBlocks.CollectorMK1) {

					} else {
						System.out.println("fourth");

						int[] core = { x, y, z - 1 };
						return core;
					}
				} else {
					System.out.println("third");

					int[] core = { x, y, z + 1 };
					return core;
				}
			} else {
				System.out.println("second");

				int[] core = { x, y - 1, z };
				return core;
			}
		} else {
			System.out.println("first");

			int[] core = { x, y + 1, z };
			return core;
		}

		return null;
	}

}
