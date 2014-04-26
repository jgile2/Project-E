package projecte.items;

import java.util.List;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;

public class ItemAlchemyBag extends Item {

	public ItemAlchemyBag() {
		super();
		this.setUnlocalizedName(ModInfo.MOD_ID + ":alchemyBag");
		this.setCreativeTab(ProjectE.tab);
		maxStackSize = 1;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":alchemybag_white");
	}

	   @Override
	    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	    {
	        if (!world.isRemote)
	        {
	            FMLNetworkHandler.openGui(entityPlayer, ProjectE.inst, 100, world, (int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ);
	        }

	        return itemStack;
	    }

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
	}

	/**
	 * Called when item is crafted/smelted. Used only by maps so far.
	 */
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
		itemstack.stackTagCompound = new NBTTagCompound();
		itemstack.stackTagCompound.setString("owner", player.getDisplayName());
		itemstack.stackTagCompound.setInteger("code", (int) (Math.random() * Integer.MAX_VALUE));
	}

	/**
	 * called when the player releases the use item button. Args: itemstack,
	 * world, entityplayer, itemInUseCount
	 */
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
	}

	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		if (itemstack.stackTagCompound != null) {
			String owner = itemstack.stackTagCompound.getString("owner");
			int code = itemstack.stackTagCompound.getInteger("code");
			list.add("owner: " + owner);
			if (owner.equals(player.getDisplayName())) {
				list.add(EnumChatFormatting.GREEN + "code: " + code);
			} else {
				list.add(EnumChatFormatting.RED + "code: " + EnumChatFormatting.OBFUSCATED + code);
			}
		}else{
			list.add("nbt is null");
		}
	}

}
