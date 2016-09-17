package net.anti_quark.EssentialFeatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.anti_quark.EssentialFeatures.common.entity.EntityPanda;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelPanda extends ModelQuadruped
{
	/*
	 * leg 1 is back right.
	 * leg 2 is back left.
	 * leg 3 is front right.
	 * leg 4 is front left.
	 */
	
      
  public ModelPanda()
  {    
      super(12, 0.0F);
      this.head = new ModelRenderer(this, 0, 0);
      this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
      this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
      this.body = new ModelRenderer(this, 18, 4);
      this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
      this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
      --this.leg1.rotationPointX;
      ++this.leg2.rotationPointX;
      this.leg1.rotationPointZ += 0.0F;
      this.leg2.rotationPointZ += 0.0F;
      --this.leg3.rotationPointX;
      ++this.leg4.rotationPointX;
      --this.leg3.rotationPointZ;
      --this.leg4.rotationPointZ;
      this.childZOffset += 2.0F;
  }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scale)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.head.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.renderWithRotation(scale);
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
     
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
    {
        EntityPanda entitypanda = (EntityPanda)entitylivingbaseIn;

        if (entitypanda.isSitting())
        {
            body.setRotationPoint(0.0F, 17.0F, 2.0F);
            this.leg1.setRotationPoint(-3.0F, 12F, 7.0F);
            this.leg2.setRotationPoint(3.0F, 12F, 7.0F);
            this.leg3.setRotationPoint(-3.0F, 12F, -5.0F);
            this.leg4.setRotationPoint(3.0F, 12F, -5.0F);
            this.leg1.rotateAngleZ = 90F;
            this.leg2.rotateAngleZ = 90F;
            this.leg3.rotateAngleZ = 90F;
            this.leg4.rotateAngleZ = 90F;
        }
        
        else
        {
            body.setRotationPoint(0.0F, 5.0F, 2.0F);
            this.body.rotateAngleX = ((float)Math.PI / 2F);
            this.leg1.setRotationPoint(-3.0F, 12F, 7.0F);
            this.leg2.setRotationPoint(3.0F, 12F, 7.0F);
            this.leg3.setRotationPoint(-3.0F, 12F, -5.0F);
            this.leg4.setRotationPoint(3.0F, 12F, -5.0F);
            this.leg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
            this.leg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.leg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.leg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
        }
        
    }  
}