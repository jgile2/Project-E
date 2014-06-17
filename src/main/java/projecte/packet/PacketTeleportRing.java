package projecte.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketTeleportRing extends PacketBase{
	
	private ItemStack itemstack;


	public PacketTeleportRing() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void encode(ByteArrayDataOutput output) {
	
		
	}

	@Override
	public void decode(ByteArrayDataInput input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionServer(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	
	public void actionBoth(EntityPlayer player){
		System.out.println("being both");

		int x = (int) player.posX;
		int y = (int) player.posY;
		int z = (int) player.posZ;
//		InventoryPlayer inventory = player.inventory;
//		InventoryPlayer inventoryMP = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(player.getDisplayName()).inventory;
		ItemStack item = player.getCurrentEquippedItem();
		NBTTagCompound nbt = item.getTagCompound();
		nbt.setInteger("x", x);
		nbt.setInteger("y", y);
		nbt.setInteger("z", z);
		player.addChatMessage(new ChatComponentText("Binding Location to X: " + x + ", Y: " + y + ", Z: " + z));
		itemstack.setTagCompound(nbt);
	}

}
