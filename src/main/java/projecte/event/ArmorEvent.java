package projecte.event;

import projecte.items.armor.PEArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ArmorEvent {

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent tick) {
        EntityPlayer player = tick.player;
        ItemStack chestPlate = player.getCurrentArmor(2);
        // System.out.println(player.capabilities.allowFlying);
        if (chestPlate != null) {
            if (chestPlate.getItem() != PEArmor.chestplateDark) {

                player.capabilities.allowFlying = false;
                player.capabilities.isFlying=false;

            }
        } else if (chestPlate == null) {
            player.capabilities.allowEdit = false;
            player.capabilities.isFlying=false;
        }
    }
}