package projecte.event;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
import projecte.packet.PacketVolcaniteTossed;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class VolcaniteTossEvent {
	EntityItem itemEntity;
	public LinkedList<EntityItem> list;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void toss(ItemTossEvent event) {
		list = new LinkedList<EntityItem>();
		itemEntity = event.entityItem;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		ItemStack itemStack = itemEntity.getEntityItem();

		if (itemStack.getItem() == PEItems.Volcanite) {
			player.addChatMessage(new ChatComponentText("Why would you chuck me you idiot"));
			// double xCoord = itemEntity.posX;
			// double yCoord = itemEntity.posY;
			// double zCoord = itemEntity.posZ;
			// double moveX = itemEntity.motionX;
			// double moveY = itemEntity.motionY;
			// double moveZ = itemEntity.motionZ;
			// if (itemEntity.onGround) {
			// System.out.println("Is on the groudn");
			// // boolean wet = itemEntity.isWet();
			// World world = MinecraftServer.getServer().getEntityWorld();
			// Block block = world.getBlock((int) xCoord, (int) yCoord, (int)
			// zCoord);
			// player.addChatMessage(new ChatComponentText("X: " + xCoord +
			// ", Y: " + yCoord + ", Z: " + zCoord));
			//
			//
			// }
			list.add(itemEntity);
			System.out.println(FMLCommonHandler.instance().getEffectiveSide());
			// PacketVolcaniteTossed packet = new
			// PacketVolcaniteTossed(itemEntity.getEntityId());
		}

	}

	@SubscribeEvent
	public void tick(TickEvent.WorldTickEvent tick) {
		if (itemEntity != null) {
			list.add(itemEntity);
		}
		if (tick.phase == TickEvent.Phase.END) {
			if (list != null) {
				System.out.println(list);
				for (EntityItem item : list) {
					double x = item.posX;
					double y = item.posY;
					double z = item.posZ;

					// System.out.println("x: "+x+" Y: "+y+" z: "+z);
					if (item.onGround) {
						System.out.println("Item is on ground");

					}
				}
			}
		}
	}
}
