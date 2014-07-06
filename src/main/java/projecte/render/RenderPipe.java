package projecte.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import projecte.ModInfo;
import projecte.blocks.PEBlocks;
import projecte.models.ModelPipe;
import projecte.tile.TilePipe;

public class RenderPipe extends TileEntitySpecialRenderer {
    
    private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/pipe.png");
    public ModelPipe                      model   = new ModelPipe();
    
    public RenderPipe() {
    
        // TODO Auto-generated constructor stub
    }
    
    private static RenderBlocks rb = new RenderBlocks();
    
    public void renderTileEntityAt(TilePipe te, double x, double y, double z, float frame) {
    
        rb.blockAccess = te.getWorldObj();
        
        
        
        bindTexture(texture);
        
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);
        
        GL11.glScalef(0.1F, 0.1F, 0.1F);
        
        // model.renderAll();
        model.base.render(0.625F);
        World world = te.getWorldObj();
        int xCoord = te.xCoord;
        int yCoord = te.yCoord;
        int zCoord = te.zCoord;
        // System.out.println(xCoord+", "+yCoord+", "+zCoord);
        Block block = world.getBlock(xCoord, yCoord + 1, zCoord);
        TileEntity blocktile = world.getTileEntity(xCoord, yCoord + 1, zCoord);
        if (block == PEBlocks.pipe || blocktile instanceof IFluidHandler) {
            model.down.render(0.625F);
            
        }
        Block block1 = world.getBlock(xCoord, yCoord - 1, zCoord);
        TileEntity blocktile1 = world.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (block1 == PEBlocks.pipe || blocktile1 instanceof IFluidHandler) {
            model.up.render(0.625F);
        }
        
        Block block2 = world.getBlock(xCoord + 1, yCoord, zCoord);
        TileEntity blocktile2 = world.getTileEntity(xCoord + 1, yCoord, zCoord);
        if (block2 == PEBlocks.pipe || blocktile2 instanceof IFluidHandler) {
            model.east.render(0.625F);
        }
        
        Block block3 = world.getBlock(xCoord - 1, yCoord, zCoord);
        TileEntity blocktile3 = world.getTileEntity(xCoord - 1, yCoord, zCoord);
        if (block3 == PEBlocks.pipe || blocktile3 instanceof IFluidHandler) {
            model.west.render(0.625F);
        }
        
        Block block4 = world.getBlock(xCoord, yCoord, zCoord + 1);
        TileEntity blocktile4 = world.getTileEntity(xCoord, yCoord, zCoord + 1);
        
        if (block4 == PEBlocks.pipe || blocktile4 instanceof IFluidHandler) {
            model.north.render(0.625F);
        }
        
        Block block5 = world.getBlock(xCoord, yCoord, zCoord - 1);
        TileEntity blocktile5 = world.getTileEntity(xCoord, yCoord, zCoord - 1);
        if (block5 == PEBlocks.pipe || blocktile5 instanceof IFluidHandler) {
            model.south.render(0.625F);
        }
        
        // model.east.render(0.625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float frame) {
    
        this.renderTileEntityAt((TilePipe) te, x, y, z, frame);
    }
    
}
