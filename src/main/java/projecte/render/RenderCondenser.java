package projecte.render;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


import projecte.ModInfo;
import projecte.items.PEItems;
import projecte.models.ModelCondensor;
import projecte.tile.TileCondenser;

public class RenderCondenser extends TileEntitySpecialRenderer {
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/condenser.png");
	public ModelCondensor model = new ModelCondensor();

	public RenderCondenser() {
	}

	private static RenderBlocks rb = new RenderBlocks();

	public void renderTileEntityAt(TileCondenser te, double x, double y, double z, float frame) {

		rb.blockAccess = te.getWorldObj();

		this.bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x - 0.5F, (float) y + 1.5F, (float) z + 1.5F);

		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		int meta = te.getBlockMetadata();

		if (meta == 2 || meta == 3) {
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
		} else if (meta == 4 || meta == 5) {
			GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
			GL11.glTranslated(0, 0, 1);
		} else {
			GL11.glTranslated(1, 0, 1);
		}

		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f11 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * frame;
		float f22 = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * frame;

		// Rotation
		 {
		 model.TopLeft.rotateAngleZ = -(f11 * (float) Math.PI / 2.0F);
		 model.Latch.rotateAngleZ = -(f11 * (float) Math.PI / 2.0F);
		
		 model.TopRight.rotateAngleZ = +(f22 * (float) Math.PI / 2.0F);
		 }

		// Test rotation
//		{
//			model.TopLeft.rotateAngleZ = 5;
//			model.Latch.rotateAngleZ = 5;
//			model.TopRight.rotateAngleZ = -5;
//		}

		model.renderAll();
		model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPushMatrix();
		{
			GL11.glTranslated(x + 0.5, y, z + 0.5);

			// ItemStack is = new ItemStack(PEItems.philosophersStone);
			ItemStack is = te.getStackInSlot(92);
			
			if (is != null) {
				EntityItem entityitem = new EntityItem(te.getWorldObj(), 0.0D, 0.0D, 0.0D, is);
				entityitem.getEntityItem().stackSize = 1;
				entityitem.hoverStart = 0.0F;

				double divider = 12;
				double rotation = (System.currentTimeMillis() % (360 * divider)) / divider;
				GL11.glRotated(rotation, 0, 1, 0);
				
				RenderItem.renderInFrame = true;
				RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 1.0D, 0.0D, 1.0F, 0.0F);
				RenderItem.renderInFrame = false;
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float frame) {
		this.renderTileEntityAt((TileCondenser) te, x, y, z, frame);
	}
}
