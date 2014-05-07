package projecte.event;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import projecte.items.PEItems;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VolcaniteTossEvent {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void toss(ItemEvent event) {
		EntityItem itemEntity = event.entityItem;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		ItemStack itemStack = itemEntity.getEntityItem();

		if (itemStack.getItem() == PEItems.Volcanite) {
			player.addChatMessage(new ChatComponentText("Why would you chuck me you idiot"));
			double xCoord = itemEntity.posX;
			double yCoord = itemEntity.posY;
			double zCoord = itemEntity.posZ;
			
			double moveX = itemEntity.motionX;
			double moveY = itemEntity.motionY;
			double moveZ = itemEntity.motionZ;
			
			// boolean wet = itemEntity.isWet();
			World world = MinecraftServer.getServer().getEntityWorld();
			Block block = world.getBlock((int) xCoord, (int) yCoord, (int) zCoord);
			player.addChatMessage(new ChatComponentText("X: " + xCoord + ", Y: " + yCoord + ", Z: " + zCoord));
			
			if (world.getBlock((int) xCoord, (int) yCoord, (int) zCoord) == Blocks.water) {
				player.addChatMessage(new ChatComponentText("I might turn into obsidian"));
				ItemStack obsidian = new ItemStack(Blocks.obsidian);
				EntityItem newItem = new EntityItem(world,xCoord,yCoord,zCoord,obsidian);
				world.spawnEntityInWorld(newItem);

			}
		}
	}
}
