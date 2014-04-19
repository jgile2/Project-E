package projecte.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.tile.TileRelayMK2;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRelayMK2 extends BlockContainer {
	private Random rand = new Random();

	protected BlockRelayMK2() {
		super(Material.iron);
		this.setBlockName(ModInfo.MOD_ID + ".relayMK2");
		this.setCreativeTab(ProjectE.tab);
		this.setHardness(2F);
		this.setStepSound(Block.soundTypeStone);
	}

	@SideOnly(Side.CLIENT)
	public static IIcon topIcon, bottomIcon, sideIcon, frontIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(ModInfo.MOD_ID + ":relay_side");
		topIcon = icon.registerIcon(ModInfo.MOD_ID + ":relay_topMK2");
		sideIcon = icon.registerIcon(ModInfo.MOD_ID + ":relay_side");
		frontIcon = icon.registerIcon(ModInfo.MOD_ID + ":relay_front");
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

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int oldMetadata) {

		TileRelayMK2 te = (TileRelayMK2) world.getTileEntity(x, y, z);

		if (te != null) {
			for (int i = 0; i < te.getSizeInventory(); i++) {
				ItemStack stack = te.getStackInSlot(i);

				if (stack != null) {
					float x1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float y1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					EntityItem item = new EntityItem(world, (double) ((float) x + x1), (double) ((float) y + y1), (double) ((float) z + f2), new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage()));
					if (stack.hasTagCompound()) {
						item.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
					}

					world.spawnEntityInWorld(item);
				}
			}
		}
		
		super.breakBlock(world, x, y, z, block, oldMetadata);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileRelayMK2();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entity, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(entity, ProjectE.inst, 2, world, x, y, z);

		}

		return true;
	}

}
