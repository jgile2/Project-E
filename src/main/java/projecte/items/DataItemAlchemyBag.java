package projecte.items;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import projecte.ModInfo;
import projecte.packet.PacketAlchemy;
import projecte.util.AlchemyUtil;
import projecte.util.InventoryUtil;
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
	private static ItemStack[] items = new ItemStack[104];

    /**
     * Takes a player and an ItemStack.
     * 
     * @param player
     *            The player which has the backpack.
     * @param is
     *            The ItemStack which holds the backpack.
     */
    public DataItemAlchemyBag(EntityPlayer player, ItemStack is) {
        super("", false, items.length);

        playerEntity = player;
        originalIS = is.copy();

        // check if inventory exists if not create one
        if(!hasInventory()) {
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
        NBTUtil.setString(originalIS, ModInfo.UID, UUID.randomUUID().toString());
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
     * Searches the backpack in players inventory and saves NBT data in it.
     */
    protected void setNBT() {
        if(!NBTUtil.getBoolean(originalIS, ModInfo.BagOpen)) {
            ItemStack current = playerEntity.getCurrentEquippedItem();
            if(AlchemyUtil.UUIDEquals(current, originalIS)) {
                current.setTagCompound(originalIS.getTagCompound());
                return;
            }
            for(ItemStack itemStack : playerEntity.inventory.mainInventory) {
                if(itemStack != null && itemStack.getItem() instanceof ItemBackpackBase) {
                    if(AlchemyUtil.UUIDEquals(itemStack, originalIS)) {
                        itemStack.setTagCompound(originalIS.getTagCompound());
                        break;
                    }
                }
            }
        } 
        
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
        setNBT();
    }

    /**
     * Writes a NBT Node with inventory.
     * 
     * @param outerTag
     *            The NBT Node to write to.
     * @return The written NBT Node.
     */
    protected void writeToNBT() {
        NBTUtil.setString(originalIS, "Name", getInventoryName());

        NBTTagList itemList = new NBTTagList();
        for(int i = 0; i < getSizeInventory(); i++) {
            if(getStackInSlot(i) != null) {
                NBTTagCompound slotEntry = new NBTTagCompound();
                slotEntry.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(slotEntry);
                itemList.appendTag(slotEntry);
            }
        }
        // save content in Inventory->Items
        NBTTagCompound inventory = new NBTTagCompound();
        inventory.setTag("Items", itemList);
        NBTUtil.setCompoundTag(originalIS, "Inventory", inventory);
        PacketAlchemy packet = new PacketAlchemy();
        packet.writeNBT();
    }

    /**
     * Reads the inventory from a NBT Node.
     * 
     * @param outerTag
     *            The NBT Node to read from.
     */
    protected void readFromNBT() {
        // for backwards compatibility
        if(NBTUtil.hasTag(originalIS, "display")) {
            setInvName(NBTUtil.getCompoundTag(originalIS, "display").getString("Name"));
            NBTUtil.removeTag(originalIS, "display");
            NBTUtil.setString(originalIS, "Name", getInventoryName());
        } else {
            setInvName(NBTUtil.getString(originalIS, "Name"));
        }

        InventoryUtil.readInventory(items, "Inventory", originalIS,false);
    }
}