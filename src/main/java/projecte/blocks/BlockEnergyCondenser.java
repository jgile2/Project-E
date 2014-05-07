package projecte.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.tile.TileCondenser;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnergyCondenser extends BlockContainer {
	private Random rand = new Random();

	protected BlockEnergyCondenser() {
		super(Material.iron);
		this.setBlockName(ModInfo.MOD_ID + ".energyCondensor");
		this.setCreativeTab(ProjectE.tab);
		this.setHardness(2F);
		this.setStepSound(Block.soundTypeStone);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(ModInfo.MOD_ID + ":energyCondensor_side");
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderBlockAsNormal() {
		return false;

	}

	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		if (p_149719_1_.getBlock(p_149719_2_, p_149719_3_, p_149719_4_ - 1) == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		} else if (p_149719_1_.getBlock(p_149719_2_, p_149719_3_, p_149719_4_ + 1) == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		} else if (p_149719_1_.getBlock(p_149719_2_ - 1, p_149719_3_, p_149719_4_) == this) {
			this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		} else if (p_149719_1_.getBlock(p_149719_2_ + 1, p_149719_3_, p_149719_4_) == this) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		} else {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		Block block = p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_ - 1);
		Block block1 = p_149689_1_.getBlock(p_149689_2_, p_149689_3_, p_149689_4_ + 1);
		Block block2 = p_149689_1_.getBlock(p_149689_2_ - 1, p_149689_3_, p_149689_4_);
		Block block3 = p_149689_1_.getBlock(p_149689_2_ + 1, p_149689_3_, p_149689_4_);
		byte b0 = 0;
		int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			b0 = 2;
		}

		if (l == 1) {
			b0 = 5;
		}

		if (l == 2) {
			b0 = 3;
		}

		if (l == 3) {
			b0 = 4;
		}

		if (block != this && block1 != this && block2 != this && block3 != this) {
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);
		} else {
			if ((block == this || block1 == this) && (b0 == 4 || b0 == 5)) {
				if (block == this) {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_ - 1, b0, 3);
				} else {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_ + 1, b0, 3);
				}

				p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);
			}

			if ((block2 == this || block3 == this) && (b0 == 2 || b0 == 3)) {
				if (block2 == this) {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_ - 1, p_149689_3_, p_149689_4_, b0, 3);
				} else {
					p_149689_1_.setBlockMetadataWithNotify(p_149689_2_ + 1, p_149689_3_, p_149689_4_, b0, 3);
				}

				p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);
			}
		}
	}

	public void setDefaultDirection(World p_149954_1_, int p_149954_2_, int p_149954_3_, int p_149954_4_) {
		if (!p_149954_1_.isRemote) {
			Block block = p_149954_1_.getBlock(p_149954_2_, p_149954_3_, p_149954_4_ - 1);
			Block block1 = p_149954_1_.getBlock(p_149954_2_, p_149954_3_, p_149954_4_ + 1);
			Block block2 = p_149954_1_.getBlock(p_149954_2_ - 1, p_149954_3_, p_149954_4_);
			Block block3 = p_149954_1_.getBlock(p_149954_2_ + 1, p_149954_3_, p_149954_4_);
			int l;
			Block block4;
			int i1;
			Block block5;
			byte b0;
			int j1;

			if (block != this && block1 != this) {
				if (block2 != this && block3 != this) {
					b0 = 3;

					if (block.func_149730_j() && !block1.func_149730_j()) {
						b0 = 3;
					}

					if (block1.func_149730_j() && !block.func_149730_j()) {
						b0 = 2;
					}

					if (block2.func_149730_j() && !block3.func_149730_j()) {
						b0 = 5;
					}

					if (block3.func_149730_j() && !block2.func_149730_j()) {
						b0 = 4;
					}
				} else {
					l = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
					block4 = p_149954_1_.getBlock(l, p_149954_3_, p_149954_4_ - 1);
					i1 = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
					block5 = p_149954_1_.getBlock(i1, p_149954_3_, p_149954_4_ + 1);
					b0 = 3;

					if (block2 == this) {
						j1 = p_149954_1_.getBlockMetadata(p_149954_2_ - 1, p_149954_3_, p_149954_4_);
					} else {
						j1 = p_149954_1_.getBlockMetadata(p_149954_2_ + 1, p_149954_3_, p_149954_4_);
					}

					if (j1 == 2) {
						b0 = 2;
					}

					if ((block.func_149730_j() || block4.func_149730_j()) && !block1.func_149730_j() && !block5.func_149730_j()) {
						b0 = 3;
					}

					if ((block1.func_149730_j() || block5.func_149730_j()) && !block.func_149730_j() && !block4.func_149730_j()) {
						b0 = 2;
					}
				}
			} else {
				l = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
				block4 = p_149954_1_.getBlock(p_149954_2_ - 1, p_149954_3_, l);
				i1 = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
				block5 = p_149954_1_.getBlock(p_149954_2_ + 1, p_149954_3_, i1);
				b0 = 5;

				if (block == this) {
					j1 = p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_ - 1);
				} else {
					j1 = p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_ + 1);
				}

				if (j1 == 4) {
					b0 = 4;
				}

				if ((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j() && !block5.func_149730_j()) {
					b0 = 5;
				}

				if ((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j() && !block4.func_149730_j()) {
					b0 = 4;
				}
			}

			p_149954_1_.setBlockMetadataWithNotify(p_149954_2_, p_149954_3_, p_149954_4_, b0, 3);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
		TileCondenser TileCondenser = (TileCondenser) p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

		if (TileCondenser != null) {
			TileCondenser.updateContainingBlockInfo();
		}
	}

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		TileCondenser TileCondenser = (TileCondenser) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

		if (TileCondenser != null) {
			for (int i1 = 0; i1 < TileCondenser.getSizeInventory(); ++i1) {
				ItemStack itemstack = TileCondenser.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_.spawnEntityInWorld(entityitem)) {
						int j1 = this.rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(p_149749_1_, (double) ((float) p_149749_2_ + f), (double) ((float) p_149749_3_ + f1), (double) ((float) p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.rand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.rand.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
		}

		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entity, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(entity, ProjectE.inst, 2, world, x, y, z);

		}

		return true;
	}

	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCondenser();
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World w, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory((IInventory) w.getTileEntity(x, y, z));
	}

	@Override
	public int getRenderType() {
		return -1;
	}

}
