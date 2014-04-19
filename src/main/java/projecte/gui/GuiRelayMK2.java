package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerRelayMK2;
import projecte.tile.TileRelayMK2;

public class GuiRelayMK2 extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/relayMK2.png");

	public TileRelayMK2 tile;

	public GuiRelayMK2(InventoryPlayer inventoryPlayer, TileRelayMK2 entity) {
		super(new ContainerRelayMK2(inventoryPlayer, entity));
		this.tile = entity;
		this.xSize = 194;
		this.ySize = 183;
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
