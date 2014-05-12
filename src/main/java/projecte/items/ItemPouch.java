package projecte.items;

import java.util.Arrays;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import org.apache.logging.log4j.LogManager;

import projecte.ModInfo;
import projecte.ProjectE;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemPouch extends Item
{
	public ItemPouch(){
		
		this.setCreativeTab(ProjectE.tab);
		this.setUnlocalizedName(ModInfo.MOD_ID+".pouch");
		this.setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":alchemybag_white");
	}
	
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.openGui(ProjectE.inst, 5, world, player.inventory.currentItem, 0, 0);
        return super.onItemRightClick(par1ItemStack, world, player);
    }
    

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        LogManager.getLogger().info("clicking -> "+par1ItemStack.stackTagCompound);
        return false;
    }
    
    @SubscribeEvent
    public void onClick(PlayerInteractEvent event)
    {
        if (event.action != Action.LEFT_CLICK_BLOCK)
            return;
        
        if (event.entityPlayer.inventory.getCurrentItem() != null)
        {
            LogManager.getLogger().info("CHECKING  side={}  stacks={}", FMLCommonHandler.instance().getEffectiveSide(), event.entityPlayer.inventory.getCurrentItem().stackTagCompound);
            event.setCanceled(true);
        }
        
    }
    
    

    public static final int    INV_SIZE  = 27;
    public static final int    ROWS      = 3;
    public static final String ITEMSTACK = "Items";
    public static final String SLOT      = "Slot";

    public static ItemStack[] readFromStack(ItemStack stack)
    {
        NBTTagCompound compound = stack.stackTagCompound;
        ItemStack[] stacks = new ItemStack[INV_SIZE];

        // empty
        if (compound == null)
        {
            stack.stackTagCompound = new NBTTagCompound();
            LogManager.getLogger().info("compound is null");
            return stacks;
        }
        else
        {
            LogManager.getLogger().info("Reading..  side={}  stacks={}", FMLCommonHandler.instance().getEffectiveSide(), compound);
        }

        NBTTagList nbttaglist = compound.getTagList(ITEMSTACK, 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte(SLOT) & 255;

            if (j >= 0 && j < INV_SIZE)
            {
                stacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        LogManager.getLogger().info("Read..  side={}  stacks={}", FMLCommonHandler.instance().getEffectiveSide(), Arrays.toString(stacks));

        return stacks;
    }
    
    public static void writeToStack(ItemStack stack, ItemStack[] stacks)
    {
        NBTTagCompound compound = stack.stackTagCompound;

        // empty
        if (compound == null)
        {
            LogManager.getLogger().info("compound is null");
            compound = stack.stackTagCompound = new NBTTagCompound();
        }
        else
        {
            LogManager.getLogger().info("writing..  side={}  stacks={}", FMLCommonHandler.instance().getEffectiveSide(), Arrays.toString(stacks));
        }
        

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < INV_SIZE; ++i)
        {
            if (stacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte(SLOT, (byte)i);
                stacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        
        compound.setTag(ITEMSTACK, nbttaglist);
        stack.stackTagCompound = compound;
        
        LogManager.getLogger().info("Wrote..  side={}  stacks={}", FMLCommonHandler.instance().getEffectiveSide(), stack.stackTagCompound);
    }

}
