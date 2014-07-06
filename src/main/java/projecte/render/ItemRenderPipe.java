package projecte.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import projecte.ModInfo;
import projecte.models.ModelPipe;

public class ItemRenderPipe implements IItemRenderer{
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/pipe.png");
	public ModelPipe model = new ModelPipe();
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
            renderPipe(1F, 15F, 1F,0.2F);
            return;
        }
        case EQUIPPED: {
            //GL11.glRotated(180, 0, 1, 0);
            //GL11.glTranslatef(0.5F, 0.0F, 0.0F);
            renderPipe(5F, 10F, 5F,0.1F);
            return;
        }
        case EQUIPPED_FIRST_PERSON: {
            GL11.glRotated(180, 0, 0, 0);
            renderPipe(10F, -10F, -7F,0.05F);
            return;
        }
        case INVENTORY: {
            GL11.glRotated(180, 0, 0, 0);
            renderPipe(-0F, 10F, 0.0F,0.15F);
            return;
        }
        default:
            return;
    }

		//TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileCondenser(), 0.0D, 0.0D, 0.0D, 0.0);
	}

	private void renderPipe(float x, float y, float z,float scale) {

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.texture);
		GL11.glPushMatrix(); // start
		GL11.glScalef(scale,scale,scale);
		GL11.glTranslatef(x, y, z); // size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		model.renderAll();
		GL11.glPopMatrix(); // end
	}

}
