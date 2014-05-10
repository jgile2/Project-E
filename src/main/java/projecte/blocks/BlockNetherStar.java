package projecte.blocks;

import java.io.IOException;

import projecte.JsonVersion;
import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockNetherStar extends Block{

	protected BlockNetherStar() {
		super(Material.iron);
		this.setBlockName(ModInfo.MOD_ID + ".netherStar");
		this.setCreativeTab(ProjectE.tab);
		this.setHardness(2F);
		this.setBlockTextureName(ModInfo.MOD_ID+":blockNetherStar");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		String[] versionCh = ModInfo.MOD_VERSION.split(".");
		player.addChatMessage(new ChatComponentText(versionCh[1]));
		JsonVersion json = null;
		
		try {
			json = new JsonVersion();
			player.addChatMessage(new ChatComponentText(json.newVersion));
			
			if(Integer.parseInt(json.newVersion)>Integer.parseInt(versionCh[1])){
				//player.addChatComponentMessage(new ChatComponentText("Time to update, the new version is: "+json.newVersion));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
