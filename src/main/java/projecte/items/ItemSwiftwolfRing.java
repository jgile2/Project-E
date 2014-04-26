package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;

public class ItemSwiftwolfRing extends Item {
	public boolean enabled = false;
	public IIcon active;
	public IIcon inactive;

	public ItemSwiftwolfRing(boolean enabled) {
		super();
		this.setUnlocalizedName(ModInfo.MOD_ID + ".FlyingRing");
		this.setCreativeTab(ProjectE.tab);
	}

	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":FlyingRing");
		active = iconRegister.registerIcon(ModInfo.MOD_ID + ":FlyingRing_active");
		inactive = iconRegister.registerIcon(ModInfo.MOD_ID + ":FlyingRing");

	}
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int par4, boolean par5) {
		// TODO Auto-generated method stub
		EntityPlayer player = (EntityPlayer)entity;
		if (enabled) {
			player.capabilities.allowFlying=true;
		} else if (!enabled) {
			player.capabilities.allowFlying=false;
			player.capabilities.isFlying=false;

		}
		super.onUpdate(is, world, entity, par4, par5);
	}


	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (enabled) {
				player.addChatMessage(new ChatComponentText(Boolean.toString(enabled)));
				enabled = false;
				itemIcon = inactive;
				
				System.out.println("flying is " + player.capabilities.allowFlying);

			} else if (!enabled) {
				player.addChatMessage(new ChatComponentText(Boolean.toString(enabled)));
				enabled = true;
				itemIcon = active;
				
				System.out.println("flying is " + player.capabilities.allowFlying);
			}
		}
		return super.onItemRightClick(is, world, player);
	}
}
