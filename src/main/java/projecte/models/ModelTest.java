package projecte.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTest extends ModelBiped {
	
	public ModelTest() {
		ModelQuadruped(12, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
		this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
		this.head.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
		this.head.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
		this.body = new ModelRenderer(this, 18, 4);
		this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
		this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.body.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
		--this.leg1.rotationPointX;
		++this.leg2.rotationPointX;
		this.leg1.rotationPointZ += 0.0F;
		this.leg2.rotationPointZ += 0.0F;
		--this.leg3.rotationPointX;
		++this.leg4.rotationPointX;
		--this.leg3.rotationPointZ;
		--this.leg4.rotationPointZ;
		this.field_78151_h += 2.0F;
	}
	
	 public ModelRenderer head = new ModelRenderer(this, 0, 0);
	    public ModelRenderer body;
	    public ModelRenderer leg1;
	    public ModelRenderer leg2;
	    public ModelRenderer leg3;
	    public ModelRenderer leg4;
	    protected float field_78145_g = 8.0F;
	    protected float field_78151_h = 4.0F;
	    private static final String __OBFID = "CL_00000851";

	    public void ModelQuadruped(int p_i1154_1_, float p_i1154_2_)
	    {
	        this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, p_i1154_2_);
	        this.head.setRotationPoint(0.0F, (float)(18 - p_i1154_1_), -6.0F);
	        this.body = new ModelRenderer(this, 28, 8);
	        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, p_i1154_2_);
	        this.body.setRotationPoint(0.0F, (float)(17 - p_i1154_1_), 2.0F);
	        this.leg1 = new ModelRenderer(this, 0, 16);
	        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
	        this.leg1.setRotationPoint(-3.0F, (float)(24 - p_i1154_1_), 7.0F);
	        this.leg2 = new ModelRenderer(this, 0, 16);
	        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
	        this.leg2.setRotationPoint(3.0F, (float)(24 - p_i1154_1_), 7.0F);
	        this.leg3 = new ModelRenderer(this, 0, 16);
	        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
	        this.leg3.setRotationPoint(-3.0F, (float)(24 - p_i1154_1_), -5.0F);
	        this.leg4 = new ModelRenderer(this, 0, 16);
	        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
	        this.leg4.setRotationPoint(3.0F, (float)(24 - p_i1154_1_), -5.0F);
	    }
}