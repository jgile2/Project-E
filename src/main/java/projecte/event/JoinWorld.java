package projecte.event;

import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class JoinWorld {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {

      //  event.player.func_146105_b(new ChatComponentText(event.player.getDisplayName() + " is testing chat messages"));
        event.player.addChatComponentMessage(new ChatComponentText("Kill Neonbeta because no one likes him"));
    }
}
