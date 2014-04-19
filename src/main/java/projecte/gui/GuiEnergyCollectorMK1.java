package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerEnergyCollectorMK1;
import projecte.tile.TileEnergyCollectorMK1;

public class GuiEnergyCollectorMK1 extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/energyCollectorMK1.png");

	public TileEnergyCollectorMK1 tile;

	public GuiEnergyCollectorMK1(InventoryPlayer inventoryPlayer, TileEnergyCollectorMK1 entity) {
		super(new ContainerEnergyCollectorMK1(inventoryPlayer, entity));
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
		
		fontRendererObj.drawString(tile.getStored() + "EMC", 65, 32, 4210752);

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
		w *= tile.getStored();
		w /= tile.getMaxStored();
		drawTexturedModalRect(guiLeft + 64, guiTop + 18, 0, 166, (int) w, 10);

		/*
		 * if (this.moneyMaker.isBurning()) { int k =
		 * this.moneyMaker.getBurnTimeRemainingScaled(12);
		 * drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - k, 176, 12 -
		 * k, 14, k + 2); } int k = this.moneyMaker.getCookProgessScaled(24);
		 * drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 14, k + 1, 16);
		 */
		if (tile.checkSun()) {
			if(tile.getSunStrength() > 0.3){
				drawTexturedModalRect(guiLeft + 126, guiTop + 36, 177, 0, 14, 14);
			}else{
				drawTexturedModalRect(guiLeft + 126, guiTop + 36, 177 + 14, 0, 14, 14);
			}
		}
	}

}
