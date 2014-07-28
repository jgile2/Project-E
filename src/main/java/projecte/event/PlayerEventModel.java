package projecte.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import projecte.models.ModelTest;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class PlayerEventModel {
    Minecraft mc = Minecraft.getMinecraft();
    private ModelTest hdr = new ModelTest();
    public RenderPlayer renderer;

	@SubscribeEvent
	public void playerRenderer(RenderPlayerEvent event){
		  float base = 0.0625f; //base value for models
	      renderer =event.renderer;
//          GL11.glPushMatrix(); //starts the rendering proce  
//          mc.renderEngine.bindTexture(new ResourceLocation("textures/entity/cow/cow.png"));
//          hdr.head.render(base);
//          hdr.body.render(base);
//          hdr.leg1.render(base);
//          hdr.leg2.render(base);
//          hdr.leg3.render(base);
//          hdr.leg4.render(base);
//          GL11.glPopMatrix();
	}
	
	@SubscribeEvent
	public void tick(TickEvent.RenderTickEvent event){
		renderer.modelBipedMain = hdr;
		System.out.println("renderign tick event");
	}

}
