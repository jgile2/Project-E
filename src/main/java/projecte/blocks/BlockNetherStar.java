package projecte.blocks;

import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;

import projecte.ModInfo;
import projecte.ProjectE;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockNetherStar extends Block {

    protected BlockNetherStar() {
        super(Material.iron);
        this.setBlockName(ModInfo.MOD_ID + ".netherStar");
        this.setCreativeTab(ProjectE.tab);
        this.setHardness(2F);
        this.setBlockTextureName(ModInfo.MOD_ID + ":blockNetherStar");
    }

    @Override
    public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_) {
        System.out.println(FMLCommonHandler.instance().getEffectiveSide());
    }

}
