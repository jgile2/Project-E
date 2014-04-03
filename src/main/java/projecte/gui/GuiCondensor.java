package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerCondensor;
import projecte.tile.TileCondenser;

public class GuiCondensor extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/energyCondensor.png");

	public TileCondenser tile;

	public GuiCondensor(InventoryPlayer inventoryPlayer, TileCondenser entity) {
		super(new ContainerCondensor(inventoryPlayer, entity));
		this.tile = entity;
		this.xSize = 256;
		this.ySize = 234;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
		/*
		 * String name = StatCollector
		 * .translateToLocal("container.EnergyCollectMK1");
		 */
		
		fontRendererObj.drawString(tile.getStoredEmc() + "EMC", 140, 10, 4210752);

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
		
		double w = 48;
		w *= tile.getStoredEmc();
		w /= tile.getMaxStoredEmc();
		drawTexturedModalRect(guiLeft + 64, guiTop + 18, 0, 166, (int) w, 10);

		
	}

}
