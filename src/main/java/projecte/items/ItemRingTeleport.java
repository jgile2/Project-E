package projecte.items;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemRingTeleport extends Item {
	private Random rand = new Random();
	public ItemRingTeleport() {
		this.setCreativeTab(ProjectE.tab);
		this.setUnlocalizedName(ModInfo.MOD_ID + ".teleport");

	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":teleport");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		int x = (int) player.posX;
		int y = (int) player.posY;
		int z = (int) player.posZ;
		NBTTagCompound nbt = itemstack.getTagCompound();

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			System.out.println("Shift is down");
			if (nbt != null) {
				nbt.setInteger("x", x);
				nbt.setInteger("y", y);
				nbt.setInteger("z", z);
				player.addChatMessage(new ChatComponentText("Binding Location to X: " + x + ", Y: " + y + ", Z: " + z));
				itemstack.setTagCompound(nbt);
			
				return itemstack;
			}
		}
		if (nbt != null) {
			int tagX = nbt.getInteger("x");
			int tagY = nbt.getInteger("y");
			int tagZ = nbt.getInteger("z");
			player.addChatMessage(new ChatComponentText("Teleporting Location to X: " + tagX + ", Y: " + tagY + ", Z: " + tagZ));
			world.playSound((double)tagX + 0.5D, (double)tagY + 0.5D, (double)tagZ + 0.5D, "fireworks.blast", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
			player.setPosition((double)tagX, (double)tagY, (double)tagZ);
		} else {
		}
		return itemstack;
	}
}
