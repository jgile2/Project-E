package projecte.packet;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketAlchemy extends PacketBase {
	public ItemStack[] items;

	public PacketAlchemy(ItemStack[] itemstack) {
		items = itemstack;
	}

	public PacketAlchemy() {

	}

	@Override
	public void encode(ByteArrayDataOutput output) {
		output.writeInt(items.length);

		for (ItemStack stack : items) {
			output.writeBoolean(stack == null);
			if (stack != null) {
				NBTTagCompound stackNBT = stack.writeToNBT(new NBTTagCompound());
				try {
					CompressedStreamTools.write(stackNBT, output);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void decode(ByteArrayDataInput input) {
		items = new ItemStack[input.readInt()];

		for (int i = 0; i > items.length; i++) {
			if (input.readBoolean()) {
				continue;
			}

			try {
				NBTTagCompound nbt = CompressedStreamTools.read(input);
				items[i] = ItemStack.loadItemStackFromNBT(nbt);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayer player) {
		actionCommon(player);
	}

	@Override
	public void actionServer(World world, EntityPlayer player) {
		actionCommon(player);
	}

	public void actionCommon(EntityPlayer player) {
		ItemStack currentItem = player.inventory.getCurrentItem();
		NBTTagCompound nbt = currentItem.getTagCompound();
		System.out.println("Pre Itemstack is " + currentItem.toString());
		System.out.println("Pre Write:" + nbt + " Side is" + FMLCommonHandler.instance().getSide());

		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.items.length; ++i) {
			if (items[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				items[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
		currentItem.writeToNBT(nbt);
		System.out.println("Post Itemstack is " + currentItem.toString());
		System.out.println("Post Write: " + nbt + " Side is" + FMLCommonHandler.instance().getSide());

	}

}
