package projecte.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.container.ContainerPouch;
import projecte.items.InventoryPouch;
import projecte.items.ItemPouch;


public class GuiPouch extends GuiContainer
{
    private final InventoryPouch itemInv;
    private final InventoryPlayer playerInv;
    private static final ResourceLocation texture = new ResourceLocation("textures/gui/container/generic_54.png");
    
    public GuiPouch(InventoryPouch itemInv, InventoryPlayer playerInv)
    {
        super(new ContainerPouch(itemInv, playerInv));
        this.itemInv = itemInv;
        this.playerInv = playerInv;
        this.allowUserInput = false;
        this.ySize = (222-108) + 3 * 18;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(this.itemInv.hasCustomInventoryName() ? this.itemInv.getInventoryName() : I18n.format(this.itemInv.getInventoryName(), new Object[0]), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
        this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
        
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, ItemPouch.ROWS * 18 + 17);
        this.drawTexturedModalRect(k, l + ItemPouch.ROWS * 18 + 17, 0, 126, this.xSize, 96);
    }

}
