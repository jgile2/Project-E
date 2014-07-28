package projecte.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.container.ContainerCollectorMulti;
import projecte.testing.TileHollowMultiBlock;

public class GuiCollectorMulti extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/energyCollectorMK1.png");

	public TileHollowMultiBlock tileMaster;

	public GuiCollectorMulti(InventoryPlayer inventoryPlayer, TileHollowMultiBlock entity) {
		super(new ContainerCollectorMulti(inventoryPlayer, entity));
		int masterX = entity.getMasterX();
		int masterY = entity.getMasterY();
		int masterZ = entity.getMasterZ();
		World world = entity.getWorldObj();

		this.tileMaster = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
		/*
		 * String name = StatCollector
		 * .translateToLocal("container.EnergyCollectMK1");
		 */

		fontRendererObj.drawString(tileMaster.getEmcStored() + "EMC", 65, 32, 4210752);

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
		w *= tileMaster.getEmcStored();
		w /= tileMaster.getMaxEmcStored();
		drawTexturedModalRect(guiLeft + 64, guiTop + 18, 0, 166, (int) w, 10);

		/*
		 * if (this.moneyMaker.isBurning()) { int k =
		 * this.moneyMaker.getBurnTimeRemainingScaled(12);
		 * drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - k, 176, 12 -
		 * k, 14, k + 2); } int k = this.moneyMaker.getCookProgessScaled(24);
		 * drawTexturedModalRect(guiLeft + 79, guiTop + 35, 176, 14, k + 1, 16);
		 */

		if (tileMaster.getSunStrength() > 0.3) {
			drawTexturedModalRect(guiLeft + 126, guiTop + 36, 177, 0, 14, 14);
		} else {
			drawTexturedModalRect(guiLeft + 126, guiTop + 36, 177 + 14, 0, 14, 14);
		}

	}

}
