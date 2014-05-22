package projecte.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventCloakRenderer {
	@SideOnly(Side.CLIENT)
//	public static void addDevCapes() {
//    	String capeURL = "https://dl.dropboxusercontent.com/u/23657067/mc%20mods/project%20e/jgile2.png";
//    	String[] owners = {"jgile2"};  	
//    	ThreadDownloadImageData image = new ThreadDownloadImageData(capeURL, null, null);
//    	
//    	for(String username : owners)
//    	{
//    		Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + username), (ITextureObject)image);
//    	}
//    	
//    }
	
	public static void addCapes(){
		 try
		    {
		      URL url = new URL("https://dl.dropboxusercontent.com/u/23657067/mc%20mods/project%20e/capes.txt");
		      URLConnection con = url.openConnection();
		      con.setConnectTimeout(1000);
		      con.setReadTimeout(1000);
		      InputStream io = con.getInputStream();
		      BufferedReader br = new BufferedReader(new InputStreamReader(io));

		      int linetracker = 1;
		      String str;
		      while ((str = br.readLine()) != null) {
		        if (!str.startsWith("--")) {
		          if (str.contains(":")) {
		            String nick = str.substring(0, str.indexOf(":"));
		            String link = str.substring(str.indexOf(":") + 1);
		        	ThreadDownloadImageData image = new ThreadDownloadImageData(link, null, null);

		            Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/"+nick), (ITextureObject)image);
		          } else {
		            System.err.println("[Project E]Someone made a stuff up somewhere, Syntax error on line " + linetracker + ": " + str);
		          }

		        }

		        linetracker++;
		      }

		      br.close();
		    } catch (MalformedURLException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
}
