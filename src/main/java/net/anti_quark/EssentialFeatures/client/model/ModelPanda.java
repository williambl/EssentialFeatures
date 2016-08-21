package net.anti_quark.EssentialFeatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.anti_quark.EssentialFeatures.common.entity.EntityPanda;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelPanda extends ModelBase
{
	  //fields
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Leg2;
    ModelRenderer Leg1;
    
    float legHeight = 13;
  
  public ModelPanda()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-2.5F, -2.5F, -8F, 5, 5, 5);
      Head.setRotationPoint(0F, 1F, -5F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(-5F, -5F, -7.5F, 10, 10, 8);
      Body.setRotationPoint(0F, 0F, 0F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      Leg3 = new ModelRenderer(this, 0, 0);
      Leg3.addBox(-1.5F, legHeight, -1.5F, 3, 6, 3);
      Leg3.setRotationPoint(2.5F, 5F, 3F);
      Leg3.setTextureSize(64, 32);
      Leg3.mirror = true;
      Leg4 = new ModelRenderer(this, 0, 0);
      Leg4.addBox(-1.5F, legHeight, -1.5F, 3, 6, 3);
      Leg4.setRotationPoint(-2.5F, 5F, 3F);
      Leg4.setTextureSize(64, 32);
      Leg4.mirror = true;
      Leg2 = new ModelRenderer(this, 0, 0);
      Leg2.addBox(-1.5F, legHeight, -1.5F, 3, 6, 3);
      Leg2.setRotationPoint(-2.5F, 5F, -3F);
      Leg2.setTextureSize(64, 32);
      Leg2.mirror = true;
      Leg1 = new ModelRenderer(this, 0, 0);
      Leg1.addBox(-1.5F, legHeight, -1.5F, 3, 6, 3);
      Leg1.setRotationPoint(2.5F, 5F, -3F);
      Leg1.setTextureSize(64, 32);
      Leg1.mirror = true;
  }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.Head.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.Body.render(scale);
            this.Leg1.render(scale);
            this.Leg2.render(scale);
            this.Leg3.render(scale);
            this.Leg4.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.Head.renderWithRotation(scale);
            this.Body.render(scale);
            this.Leg1.render(scale);
            this.Leg2.render(scale);
            this.Leg3.render(scale);
            this.Leg4.render(scale);
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
            this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.Body.rotateAngleX = ((float)Math.PI / 4F);
            this.Leg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
            this.Leg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.Leg2.setRotationPoint(0.5F, 22.0F, 2.0F);
            this.Leg2.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.Leg3.rotateAngleX = 5.811947F;
            this.Leg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.Leg4.rotateAngleX = 5.811947F;
            this.Leg4.setRotationPoint(0.51F, 17.0F, -4.0F);
        }
        else
        {
            Body.setRotationPoint(0F, 0F, 0F);
            this.Body.rotateAngleX = ((float)Math.PI / 2F);
            this.Leg1.setRotationPoint(2.5F, 5F, -3F);
            this.Leg2.setRotationPoint(-2.5F, 5F, -3F);
            this.Leg3.setRotationPoint(2.5F, 5F, 3F);
            this.Leg4.setRotationPoint(-2.5F, 5F, 3F);
            this.Leg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
            this.Leg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.Leg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.Leg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.Head.rotateAngleX = headPitch * 0.017453292F;
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
    }
}