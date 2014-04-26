package projecte.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventCloakRenderer {
	@SideOnly(Side.CLIENT)
	public static void addDevCapes() {
    	String capeURL = "https://dl.dropboxusercontent.com/s/sns1n9za10xoutx/Gigcape.png";
    	String[] owners = {"jgile2"};  	
    	ThreadDownloadImageData image = new ThreadDownloadImageData(capeURL, null, null);
    	
    	for(String username : owners)
    	{
    		Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + username), (ITextureObject)image);
    	}
    	
    }
}
