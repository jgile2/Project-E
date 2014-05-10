package projecte.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.models.ModelCondensor;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemRenderCondensor implements IItemRenderer {

	private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/condenser.png");
	public ModelCondensor model = new ModelCondensor();


	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		//renderCondensor(0.0F, 0.075F, 0.0F);
		//System.out.println("loading render");

		switch(type){
        case ENTITY: {
        	//GL11.glRotatef(90, 0, 0, 1);
        	renderCondensor(-0.5F, 1F, -0.5F);
            return;
        }
        case EQUIPPED: {
            //GL11.glRotated(180, 0, 1, 0);
            //GL11.glTranslatef(0.5F, 0.0F, 0.0F);
        	renderCondensor(0.5F, 1.5F, 0.5F);
            return;
        }
        case EQUIPPED_FIRST_PERSON: {
            //GL11.glRotated(180, 0, 1, 0);
        	renderCondensor(0.5F, 1.5F, 0.4F);
            return;
        }
        case INVENTORY: {
        	renderCondensor(-0.01F, 1F, 0.0F);
            return;
        }
        default:
            return;
    }

		//TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileCondenser(), 0.0D, 0.0D, 0.0D, 0.0);
	}

	private void renderCondensor(float x, float y, float z) {

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.texture);
		GL11.glPushMatrix(); // start
		GL11.glScalef(1.0F, 1F, 1.1F);
		GL11.glTranslatef(x, y, z); // size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		model.renderAll();
		GL11.glPopMatrix(); // end
	}

}