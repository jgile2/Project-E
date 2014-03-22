package jgile2.mods.projecte.items;

import cpw.mods.fml.common.eventhandler.Event.Result;
import jgile2.mods.projecte.ProjectE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class philosophersStone extends Item {

	public philosophersStone() {
	
		this.maxStackSize = 1;
		this.setUnlocalizedName("philosophersStone");
		this.setCreativeTab(ProjectE.create);
		//this.setContainerItem(this);
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("ProjectE:philosophersStone");
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		} else {
			if (par2EntityPlayer.isSneaking()) {
				UseHoeEvent event = new UseHoeEvent(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6);
				if (MinecraftForge.EVENT_BUS.post(event)) {

					return false;
				}
				System.out.println(event.getResult());
				if (event.getResult() == Result.ALLOW) {

					return true;
				}

				Block block = par3World.getBlock(par4, par5, par6);

				if (par7 != 0 && par3World.getBlock(par4, par5 + 1, par6).isAir(par3World, par4, par5 + 1, par6) && (block == Blocks.grass || block == Blocks.dirt)) {
					Block block1 = Blocks.farmland;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block1);
						par1ItemStack.damageItem(1, par2EntityPlayer);
						return true;
					}
				} else {
					return false;
				}

			} else {
				Block i1 = par3World.getBlock(par4, par5, par6);
				boolean air = par3World.isAirBlock(par4, par5 + 1, par6);

				if (i1 == Blocks.stone) {
					Block block = Blocks.dirt;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block);

						return true;
					}
				} else if (i1 == Blocks.dirt) {
					Block block = Blocks.grass;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block);

						return true;
					}
				} else if (i1 == Blocks.grass) {
					Block block = Blocks.sand;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block);

						return true;
					}
				} else if (i1 == Blocks.sand) {
					Block block = Blocks.sandstone;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block);

						return true;
					}
				} else if (i1 == Blocks.sandstone) {
					Block block = Blocks.cobblestone;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block);

						return true;
					}
				} else if (i1 == Blocks.cobblestone) {
					Block block = Blocks.stone;
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F), (double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

					if (par3World.isRemote) {
						return true;
					} else {
						par3World.setBlock(par4, par5, par6, block);

						return true;
					}
				} else {
					return false;
				}
			}
		}
	}

}
