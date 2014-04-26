package projecte.items;

import java.lang.reflect.Field;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import projecte.ModInfo;
import projecte.packet.PacketAlchemy;
import projecte.packet.PacketManager;
import projecte.util.NBTUtil;

public class DataItemAlchemyBag extends InventoryBasic {

	// the title of the backpack
	protected String inventoryTitle;

	// an instance of the player to get the inventory
	protected EntityPlayer playerEntity;
	// the original ItemStack to compare with the player inventory
	protected ItemStack originalIS;

	// if class is reading from NBT tag
	protected boolean reading = false;

	// private ItemStack[] items = new ItemStack[104];

	/**
	 * Takes a player and an ItemStack.
	 * 
	 * @param player
	 *            The player which has the backpack.
	 * @param is
	 *            The ItemStack which holds the backpack.
	 */
	public DataItemAlchemyBag(EntityPlayer player, ItemStack is) {
		super("", false, 104);

		playerEntity = player;
		originalIS = is.copy();

		// check if inventory exists if not create one
		if (!hasInventory()) {
			createInventory();
			is.setTagCompound(originalIS.getTagCompound());
		}

		loadInventory();
	}

	/**
	 * Is called whenever something is changed in the inventory.
	 */

	/**
	 * This method is called when the chest opens the inventory. It loads the
	 * content of the inventory and its title.
	 */
	@Override
	public void openInventory() {
		loadInventory();
	}

	/**
	 * This method is called when the chest closes the inventory. It then throws
	 * out every backpack which is inside the backpack and saves the inventory.
	 */
	@Override
	public void closeInventory() {
		saveInventory();
	}

	/**
	 * Returns the name of the inventory.
	 */
	@Override
	public String getInventoryName() {
		return inventoryTitle;
	}

	// ***** custom methods which are not in IInventory *****
	/**
	 * Returns if an Inventory is saved in the NBT.
	 * 
	 * @return True when the NBT is not null and the NBT has key "Inventory"
	 *         otherwise false.
	 */
	protected boolean hasInventory() {
		return NBTUtil.hasTag(originalIS, "Inventory");
	}

	/**
	 * Creates the Inventory Tag in the NBT with an empty inventory.
	 */
	protected void createInventory() {
		setInvName(originalIS.getDisplayName());
		writeToNBT();
	}

	/**
	 * Sets the name of the inventory.
	 * 
	 * @param name
	 *            The new name.
	 */
	public void setInvName(String name) {
		inventoryTitle = name;
	}

	/**
	 * If there is no inventory create one. Then load the content and title of
	 * the inventory from the NBT
	 */
	public void loadInventory() {
		reading = true;
		readFromNBT();
		reading = false;
	}

	/**
	 * Saves the actual content of the inventory to the NBT.
	 */
	public void saveInventory() {
		writeToNBT();

		InventoryPlayer inventory = playerEntity.inventory;
		// slot = playerEntity.inventory.currentItem;
		// for (int i = 0; i > inventory.getSizeInventory(); i++) {
		//
		// if (inventory.getStackInSlot(i).getItem() == PEItems.AlchemyBag) {
		// //slot = i;
		// break;
		// }

		// }

		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			try {
				PacketAlchemy packet = new PacketAlchemy((ItemStack[]) ObfuscationReflectionHelper.getPrivateValue(InventoryBasic.class, this, 2));
				PacketManager.sendToPlayer(packet,playerEntity);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes a NBT Node with inventory.
	 * 
	 * @param outerTag
	 *            The NBT Node to write to.
	 * @return The written NBT Node.
	 */
	protected void writeToNBT() {

		ItemStack currentItem = playerEntity.inventory.getCurrentItem();
		NBTTagCompound nbt = currentItem.getTagCompound();
		System.out.println("Pre Itemstack is " + currentItem.toString());
		System.out.println("Pre Write:" + nbt + " Side is" + FMLCommonHandler.instance().getSide());

		if (nbt == null) {
			nbt = new NBTTagCompound();

		}
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
		currentItem.writeToNBT(nbt);
		System.out.println("Post Itemstack is " + currentItem.toString());
		System.out.println("Post Write: " + nbt + " Side is" + FMLCommonHandler.instance().getSide());

	}

	/**
	 * Reads the inventory from a NBT Node.
	 * 
	 * @param outerTag
	 *            The NBT Node to read from.
	 */
	protected void readFromNBT() {

		ItemStack currentItem = playerEntity.inventory.getCurrentItem();
		NBTTagCompound nbt = currentItem.getTagCompound();
		System.out.println("Pre Itemstack is " + currentItem.toString());
		System.out.println("Pre Read:" + nbt + " Side is" + FMLCommonHandler.instance().getSide());

		if (nbt == null) {
			nbt = new NBTTagCompound();
			return;
		}
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		// items = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.getSizeInventory()) {
				this.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}
		System.out.println("Post Itemstack is " + currentItem.toString());
		System.out.println("Post Read:" + nbt + " Side is" + FMLCommonHandler.instance().getSide());

	}
}