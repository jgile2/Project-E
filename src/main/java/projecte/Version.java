package projecte;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import scala.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class Version {
	public String Urlscan;
	public String CurrentVersion;
	public Random rand = new Random();
	public int randInt =rand.nextInt(4);
	public String msg1 = "Update your fucken mod idiot, cheez";
	public String msg2 = "Sir/Madam Its that time of the month again, update";
	public String msg3 = "Message 3 update";
	public String msg4 = "Message 4 update";
	public String msg5 = "Message 5 update";


	@SubscribeEvent
	public void event(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		try {
			URL url = new URL("https://dl.dropboxusercontent.com/u/23657067/mc%20mods/version.txt");
			Scanner s = new Scanner(url.openStream());
			Urlscan = s.next();
			CurrentVersion = Urlscan.toString();
			// read from your scanner
		} catch (IOException ex) {
			// there was some connection problem, or the file did not exist on
			// the server,
			// or your URL was not in the right format.
			// think about what to do now, and put it here.
			ex.printStackTrace(); // for now, simply output it.
		}
		if(CurrentVersion!=ModInfo.MOD_VERSION){
			switch(randInt){
			case 0:
				player.addChatMessage(new ChatComponentText(msg1));
				player.addChatMessage(new ChatComponentText("The New Version is: "+CurrentVersion));
				break;
			case 1:
				player.addChatMessage(new ChatComponentText(msg2));
				player.addChatMessage(new ChatComponentText("The New Version is: "+CurrentVersion));
				break;
			case 2:
				player.addChatMessage(new ChatComponentText(msg3));
				player.addChatMessage(new ChatComponentText("The New Version is: "+CurrentVersion));
				break;
			case 3:
				player.addChatMessage(new ChatComponentText(msg4));
				player.addChatMessage(new ChatComponentText("The New Version is: "+CurrentVersion));
				break;
			case 4:
				player.addChatMessage(new ChatComponentText(msg5));
				player.addChatMessage(new ChatComponentText("The New Version is: "+CurrentVersion));
				break;
			
			}
			
		}
	}
}
