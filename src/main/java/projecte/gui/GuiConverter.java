package projecte.gui;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.blocks.PEBlocks;
import projecte.container.ContainerConverter;
import projecte.tile.TileConverter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiConverter extends GuiContainer{

	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/converter.png");
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
		/*
		 * String name = StatCollector
		 * .translateToLocal("container.EnergyCollectMK1");
		 */
		
		//drawTexturedModelRectFromIcon(guiLeft-50, guiTop, PEBlocks.blockFluidEMC.getIcon(1, 0), 16, 50);

		fontRendererObj.drawString(tile.getEmcStored() + "EMC", 65, 11, 4210752);

		// this.fontRendererObj.drawString(name, this.xSize / 2 -
		// this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		// this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"),
		// 8, this.ySize - 96, 4210752);


	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}
}
