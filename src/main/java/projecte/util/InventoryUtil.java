package projecte.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryUtil {
	 public static void readInventory(ItemStack[] inventory, String name, ItemStack backpack, boolean clearInventory) {
	        if(clearInventory) {
	            for(int i = 0; i < inventory.length; i++) {
	                inventory[i] = null;
	            }
	        }
	        NBTTagList itemList = NBTUtil.getCompoundTag(backpack, name).getTagList("Items",inventory.length);
	        for(int i = 0; i < itemList.tagCount(); i++) {
	            NBTTagCompound slotEntry = itemList.getCompoundTagAt(i);
	            int slot = slotEntry.getByte("Slot") & 0xff;

	            if(slot >= 0 && slot < inventory.length) {
	                inventory[slot] = ItemStack.loadItemStackFromNBT(slotEntry);
	            }
	        }
	    }

}
