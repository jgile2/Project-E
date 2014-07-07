package projecte.items.armor;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class PEArmor {
    
    public static ItemArmor.ArmorMaterial armorDark;
    
    public static Item helmetDark;
    public static Item chestplateDark;
    public static Item legsDark;
    public static Item bootsDark;

    public static void registerArmor(){
        
        armorDark = EnumHelper.addArmorMaterial("dark", 50, new int[]{10,15,12,10}, 30);
        
        helmetDark = new ItemDarkArmor(armorDark, 5,0);
        GameRegistry.registerItem(helmetDark, helmetDark.getUnlocalizedName());
        
        chestplateDark = new ItemDarkArmor(armorDark, 5,1);
        GameRegistry.registerItem(chestplateDark, chestplateDark.getUnlocalizedName());

        legsDark = new ItemDarkArmor(armorDark, 5,2);
        GameRegistry.registerItem(legsDark, legsDark.getUnlocalizedName());

        bootsDark = new ItemDarkArmor(armorDark, 5,3);
        GameRegistry.registerItem(bootsDark, bootsDark.getUnlocalizedName());

    }
    @SideOnly(Side.CLIENT)
    public static void registerRenderer(){
        RenderingRegistry.addNewArmourRendererPrefix("5");
    }
}
