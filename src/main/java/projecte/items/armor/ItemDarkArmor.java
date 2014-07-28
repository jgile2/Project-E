package projecte.items.armor;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemDarkArmor extends ItemArmor {

    @SideOnly(Side.CLIENT)
    public static IIcon boots, leggings, chestPlate, helmet;
    public int type;

    public ItemDarkArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
        super(armorMaterial, renderIndex, armorType);
        this.setMaxStackSize(1);
        this.setCreativeTab(ProjectE.tab);
        this.setUnlocalizedName(ModInfo.MOD_ID + ".darkArmor." + armorType);
        type = armorType;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack.getItem() == PEArmor.bootsDark || stack.getItem() == PEArmor.helmetDark || stack.getItem() == PEArmor.chestplateDark) {
            return ModInfo.MOD_ID + ":textures/armor/dark_1.png";
        }

        if (stack.getItem() == PEArmor.legsDark) {
            return ModInfo.MOD_ID + ":textures/armor/dark_2.png";
        }
        return null;
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
       // System.out.println(FMLCommonHandler.instance().getEffectiveSide());

        // TODO Auto-generated method stub
        if (itemStack.getItem() == PEArmor.chestplateDark) {
           // System.out.println("is chestplate");
            NBTTagCompound nbt = itemStack.getTagCompound();
            if (nbt == null) {
               // System.out.println("tick nbt is null");
                // nbt = new NBTTagCompound();
                // itemStack.setTagCompound(nbt);
            } else {
                boolean allowFlight = nbt.getBoolean("allowFlight");
               // System.out.println(allowFlight);

                if (allowFlight) {
                 //   System.out.println("is allowing flight");
                    // player.capabilities.allowFlying = true;
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        helmet = ir.registerIcon(ModInfo.MOD_ID + ":helmetDark");
        chestPlate = ir.registerIcon(ModInfo.MOD_ID + ":chestplateDark");
        leggings = ir.registerIcon(ModInfo.MOD_ID + ":legsDark");
        boots = ir.registerIcon(ModInfo.MOD_ID + ":bootsDark");
        switch (type) {
        case 0:
            this.itemIcon = helmet;
            break;
        case 1:
            this.itemIcon = chestPlate;
            break;
        case 2:
            this.itemIcon = leggings;
            break;
        case 3:
            this.itemIcon = boots;
            break;
        default:
            break;
        }

    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
       // System.out.println("armour type is: " + type);
        switch (type) {
        case 0:
            return helmet;
        case 1:
            return chestPlate;
        case 2:
            return leggings;
        case 3:
            return boots;
        default:
            return null;
        }
    }

    public static void writeToStack(ItemStack stack, boolean allowFlight) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            //System.out.println("write nbt is null");

            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        System.out.println("trying to set nbt");

        System.out.println(allowFlight);
        nbt.setBoolean("allowFlight", allowFlight);
        stack.setTagCompound(nbt);
    }

}
