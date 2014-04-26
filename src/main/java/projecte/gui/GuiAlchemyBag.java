package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerAlChest;

public class GuiAlchemyBag  extends GuiContainer
{
	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/alchest.png");

    public GuiAlchemyBag(EntityPlayer player)
    {
        super(new ContainerAlChest(player));
        xSize = 256;
        ySize = 231;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
      
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}