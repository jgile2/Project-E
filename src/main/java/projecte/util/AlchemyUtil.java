package projecte.util;

import java.util.UUID;

import projecte.ModInfo;

import net.minecraft.item.ItemStack;
import codechicken.nei.api.ItemInfo;

public class AlchemyUtil {
	public static boolean UUIDEquals(ItemStack suspicious, ItemStack original) {
        if(suspicious != null && original != null) {
            if(NBTUtil.hasTag(suspicious, ModInfo.UID) && NBTUtil.hasTag(original, ModInfo.UID)) {
                try {
                    UUID UIDsuspicious = UUID.fromString(NBTUtil.getString(suspicious, ModInfo.UID));
                    UUID UIDoriginal = UUID.fromString(NBTUtil.getString(original, ModInfo.UID));
                    return UIDsuspicious.equals(UIDoriginal);
                }
                catch (IllegalArgumentException e) {
                    return false;
                }
            }
        }
        return false;
    }

}
