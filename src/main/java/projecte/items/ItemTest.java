package projecte.items;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;

public class ItemTest extends Item {

	public ItemTest() {
		super();
		this.setUnlocalizedName(ModInfo.MOD_ID + ".Test");
		this.setCreativeTab(ProjectE.tab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		
		NBTTagCompound nbt = itemstack.stackTagCompound;
		if(nbt ==null){
			nbt = new NBTTagCompound();
			nbt.setInteger("Stuff", 1);
			System.out.println("NBT is null");
			itemstack.setTagCompound(nbt);
			
		}	
		System.out.println(FMLCommonHandler.instance().getEffectiveSide());
		int num = nbt.getInteger("Stuff");
		player.addChatMessage(new ChatComponentText(Integer.toString(num)));	
		nbt.setInteger("Stuff", num+1);
		return itemstack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}
}
