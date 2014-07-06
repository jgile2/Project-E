package projecte.blocks;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.tile.TileCustomiser;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCustomiser extends BlockContainer {

    protected BlockCustomiser() {
        super(Material.iron);
        this.setBlockName(ModInfo.MOD_ID + ".customiser");
        this.setCreativeTab(ProjectE.tab);
        this.setHardness(2F);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(ModInfo.MOD_ID + ":darkMatter");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entity, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(entity, ProjectE.inst, 10000, world, x, y, z);

        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        // TODO Auto-generated method stub
        return new TileCustomiser();
    }


}
