package backpack.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemBackpackBase extends Item {

    public ItemBackpackBase() {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    /**
     * Returns the sub items.
     * 
     * @param itemId
     *            the id of the item
     * @param tab
     *            A creative tab.
     * @param A
     *            List which stores the sub items.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemId, CreativeTabs tab, List subItems) {
        if(itemId == Items.backpack.itemID) {
            for(int i = 0; i < 17; i++) {
                subItems.add(new ItemStack(itemId, 1, i));
            }
            for(int i = 32; i < 49; i++) {
                subItems.add(new ItemStack(itemId, 1, i));
            }
            subItems.add(new ItemStack(itemId, 1, ItemInfo.ENDERBACKPACK));
        } else if(itemId == Items.workbenchBackpack.itemID) {
            subItems.add(new ItemStack(itemId, 1, 18));
            subItems.add(new ItemStack(itemId, 1, 50));
        }
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     * 
     * @param stack
     *            The itemstack which is used
     * @param player
     *            The player who used the item
     * @param worldObj
     *            The world in which the click has occured
     * @param x
     *            The x coord of the clicked block
     * @param y
     *            The y coord of the clicked block
     * @param z
     *            The z coord of the clicked block
     * @param side
     *            The side of the block that was clicked
     * @param hitX
     *            The x position on the block which got clicked
     * @param hitY
     *            The y position on the block which got clicked
     * @param hitz
     *            The z position on the block which got clicked
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World worldObj, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TileEntity te = worldObj.getBlockTileEntity(x, y, z);
        if(te != null && (te instanceof IInventory || te instanceof TileEntityEnderChest)) {
            boolean openGui = false;
            if(te instanceof TileEntityChest) {
                openGui = true;
            }
            if(te instanceof TileEntityEnderChest && !BackpackUtil.isEnderBackpack(stack)) {
                openGui = true;
            }
            if(te instanceof TileEntityFurnace) {
                openGui = true;
            }
            if(te instanceof TileEntityHopper) {
                openGui = true;
            }
            if(te instanceof TileEntityBrewingStand) {
                openGui = true;
            }
            if(te instanceof TileEntityDispenser) {
                openGui = true;
            }
            if(te instanceof TileEntityDropper) {
                openGui = true;
            }

            if(te.getClass().getSimpleName().equals("TileEntityDirtChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntityCopperChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntityIronChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntitySilverChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntityGoldChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntityDiamondChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntityCrystalChest")) {
                openGui = true;
            }
            if(te.getClass().getSimpleName().equals("TileEntityObsidianChest")) {
                openGui = true;
            }

            if(openGui) {
                player.openGui(Backpack.instance, Constants.GUI_ID_COMBINED, worldObj, x, y, z);
                return true;
            }
        }
        return false;
    }

    /**
     * Handles what should be done on right clicking the item.
     * 
     * @param itemStack
     *            The ItemStack which is right clicked.
     * @param world
     *            The world in which the player is.
     * @param player
     *            The player who right clicked the item.
     * @param Returns
     *            the ItemStack after the process.
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        // if world.isRemote than we are on the client side
        if(world.isRemote) {
            // display rename GUI if player is sneaking
            if(player.isSneaking() && !BackpackUtil.isEnderBackpack(itemStack)) {
                player.openGui(Backpack.instance, Constants.GUI_ID_RENAME_BACKPACK, world, 0, 0, 0);
            }
            return itemStack;
        }

        // when the player is not sneaking
        if(!player.isSneaking() && !ConfigurationBackpack.OPEN_ONLY_WORN_BACKPACK) {
            if(itemStack.itemID == Items.backpack.itemID) {
                player.openGui(Backpack.instance, Constants.GUI_ID_BACKPACK, world, 0, 0, 0);
            } else if(itemStack.itemID == Items.workbenchBackpack.itemID) {
                player.openGui(Backpack.instance, Constants.GUI_ID_WORKBENCH_BACKPACK, world, 0, 0, 0);
            }
        }
        return itemStack;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an
     * ItemStack so different stacks can have different names based on their
     * damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName();

        int damage = itemStack.getItemDamage();
        if(damage >= 0 && damage < 16) {
            name += "." + ItemInfo.BACKPACK_COLORS[damage];
        }
        if(damage >= 32 && damage < 48) {
            name += ".big_" + ItemInfo.BACKPACK_COLORS[damage - 32];
        }
        if(damage == 48 || damage == 50) {
            name += ".big";
        }
        if(damage == ItemInfo.ENDERBACKPACK) {
            name += "." + ItemInfo.BACKPACK_COLORS[16];
        }
        return name;
    }

    /**
     * Returns the item name to display in the tooltip.
     * 
     * @param itemstack
     *            The ItemStack to use for check.
     * @return The name of the backpack for the tooltip.
     */
    @Override
    public String getItemDisplayName(ItemStack itemstack) {
        String name = "";
        // it ItemStack has a NBTTagCompound load name from it.
        if(NBTUtil.hasTag(itemstack, "Name")) {
            name = NBTUtil.getString(itemstack, "Name");
        } else {
            name = getUnlocalizedNameInefficiently(itemstack) + ".name";
        }
        return StatCollector.translateToLocal(name);
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return false;
    }
}