package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerRelayMK1;
import projecte.tile.TileRelayMK1;

public class GuiRelayMK1 extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/relayMK1.png");

	public TileRelayMK1 tile;

	public GuiRelayMK1(InventoryPlayer inventoryPlayer, TileRelayMK1 entity) {
		super(new ContainerRelayMK1(inventoryPlayer, entity));
		this.tile = entity;
		this.xSize = 176;
		this.ySize = 176;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		// String name =
		// StatCollector.translateToLocal("container.EnergyCollectMK1");

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
