package projecte.packet;

import org.lwjgl.input.Keyboard;

import projecte.handlers.KeyHandler;
import projecte.items.PEItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketKey extends PacketBase {
	// public InventoryPlayer inventory;
	private EntityPlayer user;
	private boolean up;

	public PacketKey() {

	}

	public PacketKey(boolean up) {
		System.out.println("being called");
		this.up = up;
		System.out.println(up);
	}

	@Override
	public void encode(ByteArrayDataOutput output) {
		output.writeBoolean(up);
	}

	@Override
	public void decode(ByteArrayDataInput input) {
		this.up = input.readBoolean();
	}

	@Override
	public void actionClient(World world, EntityPlayer player) {
		System.out.println("being client");

		actionBoth(player);
	}

	@Override
	public void actionServer(World world, EntityPlayer player) {
		System.out.println("being server");


		actionBoth(player);
	}

	public void actionBoth(EntityPlayer player) {
		System.out.println("being both");
		

		System.out.println(up);
		InventoryPlayer inventory = player.inventory;
		InventoryPlayer inventoryMP = MinecraftServer.getServer().getConfigurationManager().func_152612_a(player.getDisplayName()).inventory;

		if (up) {

			Item item = PEItems.DestructionCatalyst;
			// System.out.println("current item: " +
			// inventory.getCurrentItem());
			if (inventory != null) {
				if (inventory.getCurrentItem() != null) {
					if (inventory.getCurrentItem().getItem() == item) {

						// System.out.println("hand has the correct item");
						ItemStack currentItem = inventory.getCurrentItem();
						ItemStack currentItemMP = inventoryMP.getCurrentItem();

						int currentCharge = currentItem.getItemDamage();
						System.out.println("before" + currentCharge);
						if (currentCharge >= 2) {
							int nextCharge = currentCharge - 1;

							currentItem.setItemDamage(nextCharge);
							currentItemMP.setItemDamage(nextCharge);
							// currentItemMP.setItemDamage(nextCharge);
							System.out.println("after" + nextCharge);
						}
					}
				}
			}
		}
		if (!up) {
			// System.out.println("Shift key is pressed");

			// System.out.println("the charge key and the shift key is pressed");

			// user = Minecraft.getMinecraft().thePlayer;

			Item item = PEItems.DestructionCatalyst;
			if (inventory.getCurrentItem().getItem() == item) {
				// System.out.println("hand has the correct item");
				ItemStack currentItem = inventory.getCurrentItem();
				ItemStack currentItemMP = inventoryMP.getCurrentItem();
				int currentCharge = currentItem.getItemDamage();
				// System.out.println("before" + currentCharge);
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
