package projecte.testing;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHollow extends BlockContainer {
	public static IIcon topIcon, bottomIcon, sideIcon, frontIcon;

	public BlockHollow() {
		super(Material.rock);
		this.setBlockName(ModInfo.MOD_ID + ".hollow");
		this.setCreativeTab(ProjectE.tab);
		this.setHardness(2F);
		this.setStepSound(Block.soundTypeStone);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileHollowMultiBlock();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileHollowMultiBlock tile = (TileHollowMultiBlock) world.getTileEntity(x, y, z);
		int masterX = tile.getMasterX();
		int masterY = tile.getMasterY();
		int masterZ = tile.getMasterZ();
		System.out.println("Side is: " + FMLCommonHandler.instance().getEffectiveSide() + ", Has master: " + tile.hasMaster());
		if (!world.isRemote) {
			if (tile.hasMaster()) {
				FMLNetworkHandler.openGui(player, ProjectE.inst, 1, world, masterX, masterY, masterZ);

				return true;
			}
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(ModInfo.MOD_ID + ":energyCollector_side");
		topIcon = icon.registerIcon(ModInfo.MOD_ID + ":energyCollectorMK1_top");
		sideIcon = icon.registerIcon(ModInfo.MOD_ID + ":energyCollector_side");
		frontIcon = icon.registerIcon(ModInfo.MOD_ID + ":energyCollector_front");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta == 0 && side == 3) {
			return frontIcon;
		} else if (side == meta) {
			return frontIcon;
		} else if (side == 1) {
			return topIcon;
		} else {
			return sideIcon;
		}
		// return meta==0 && side==3?this.frontIcon:(side == meta ?
		// this.frontIcon : this.blockIcon);

	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);

	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block l = world.getBlock(x, y, z - 1);
			Block il = world.getBlock(x, y, z + 1);
			Block jl = world.getBlock(x - 1, y, z);
			Block kl = world.getBlock(x + 1, y, z);
			byte b0 = 3;

			if (l.func_149730_j() && !il.func_149730_j()) {
				b0 = 3;
			}

			if (il.func_149730_j() && !l.func_149730_j()) {
				b0 = 2;
			}

			if (kl.func_149730_j() && !jl.func_149730_j()) {
				b0 = 5;
			}

			if (jl.func_149730_j() && !kl.func_149730_j()) {
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);

		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

	}

}
