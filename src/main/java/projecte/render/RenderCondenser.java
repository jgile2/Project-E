package projecte.render;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import projecte.ModInfo;
import projecte.tile.TileCondenser;

public class RenderCondenser extends TileEntitySpecialRenderer {
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/condenser.png");
	public ModelChest model = new ModelChest();

	public RenderCondenser() {
	}
	public void renderTileEntityAt(TileCondenser te, double x, double y, double z, float frame) {
		
		
		this.bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		int meta = te.getBlockMetadata();

		if (meta == 2) {
			short1 = 180;
		}

		if (meta == 3) {
			short1 = 0;
		}

		if (meta == 4) {
			short1 = 90;
		}

		if (meta == 5) {
			short1 = -90;
		}

		GL11.glRotatef((float) short1, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = te.prevLidAngle+ (te.lidAngle - te.prevLidAngle) * frame;
		model.chestLid.rotateAngleX = -(f1 * (float) Math.PI / 2.0F);
		model.renderAll();
		model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float frame) {
		this.renderTileEntityAt((TileCondenser) te, x, y, z, frame);
	}
}
