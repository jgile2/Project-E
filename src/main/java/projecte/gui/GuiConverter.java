package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerConverter;
import projecte.container.ContainerCustomiser;
import projecte.tile.TileConverter;
import projecte.tile.TileCustomiser;

public class GuiConverter extends GuiContainer{

	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/customiser.png");
	public TileConverter tile;
//	public static final ResourceLocation fluid = new ResourceLocation(PEBlocks.blockFluidEMC.get);


	public GuiConverter(EntityPlayer player, TileConverter entity) {
		super(new ContainerConverter(player, entity));
		this.tile = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
	    fontRendererObj.drawString(tile.getEmcStored() + "EMC", 65, 11, 4210752);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}
}
