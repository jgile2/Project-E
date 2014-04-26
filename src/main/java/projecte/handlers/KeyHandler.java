package projecte.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import org.lwjgl.input.Keyboard;

import projecte.ModInfo;
import projecte.items.PEItems;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KeyHandler {

	@SideOnly(Side.CLIENT)
	private static KeyBinding charge = new KeyBinding("Charge", Keyboard.KEY_H, ModInfo.MOD_ID);

	public KeyHandler() {
		ClientRegistry.registerKeyBinding(charge);
		System.out.println("calling key class");

	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void tick(KeyInputEvent ev) {
		if (charge.isPressed() && !Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			System.out.println("the charge key is pressed");

			InventoryPlayer inventory = Minecraft.getMinecraft().thePlayer.inventory;
			InventoryPlayer inventoryMP = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(Minecraft.getMinecraft().thePlayer.getDisplayName()).inventory;
			Item item = PEItems.DestructionCatalyst;
			System.out.println("current item: " + inventory.getCurrentItem());
			if (inventory.getCurrentItem().getItem() == item) {
				
				System.out.println("hand has the correct item");
				ItemStack currentItem = inventory.getCurrentItem();
				ItemStack currentItemMP =inventoryMP.getCurrentItem();

				int currentCharge = currentItem.getItemDamage();
				System.out.println("before" + currentCharge);
				if (currentCharge >=2) {
					int nextCharge = currentCharge - 1;
					
					currentItem.setItemDamage(nextCharge);
					currentItemMP.setItemDamage(nextCharge);
					System.out.println("after" + nextCharge);
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && charge.getIsKeyPressed()) {
			System.out.println("Shift key is pressed");

			System.out.println("the charge key and the shift key is pressed");

			InventoryPlayer inventory = Minecraft.getMinecraft().thePlayer.inventory;
			InventoryPlayer inventoryMP = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(Minecraft.getMinecraft().thePlayer.getDisplayName()).inventory;

			Item item = PEItems.DestructionCatalyst;
			if (inventory.getCurrentItem().getItem() == item) {
				System.out.println("hand has the correct item");
				ItemStack currentItem = inventory.getCurrentItem();
				ItemStack currentItemMP = inventoryMP.getCurrentItem();
				int currentCharge = currentItem.getItemDamage();
				System.out.println("before" + currentCharge);
				if (currentCharge != currentItem.getMaxDamage()) {
					int nextCharge = currentCharge + 1;

					currentItem.setItemDamage(nextCharge);
					currentItemMP.setItemDamage(nextCharge);
					System.out.println("after" + nextCharge);
				}
			}

		}
	}

}
