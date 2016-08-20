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

@SideOnly(Side.CLIENT)
public class ModelPanda extends ModelBase
{
    /** main box for the panda head */
    public ModelRenderer pandaHeadMain;
    /** The panda's body */
    public ModelRenderer pandaBody;
    /** Panda'se first leg */
    public ModelRenderer pandaLeg1;
    /** Panda's second leg */
    public ModelRenderer pandaLeg2;
    /** Panda's third leg */
    public ModelRenderer pandaLeg3;
    /** Panda's fourth leg */
    public ModelRenderer pandaLeg4;
    /** The panda's tail */
    ModelRenderer pandaTail;
    /** The panda's mane */
    ModelRenderer pandaMane;

    public ModelPanda()
    {
        float f = 0.0F;
        float f1 = 13.5F;
        this.pandaHeadMain = new ModelRenderer(this, 0, 0);
        this.pandaHeadMain.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.pandaHeadMain.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.pandaBody = new ModelRenderer(this, 18, 14);
        this.pandaBody.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F);
        this.pandaBody.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.pandaMane = new ModelRenderer(this, 21, 0);
        this.pandaMane.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F);
        this.pandaMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.pandaLeg1 = new ModelRenderer(this, 0, 18);
        this.pandaLeg1.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.pandaLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.pandaLeg2 = new ModelRenderer(this, 0, 18);
        this.pandaLeg2.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.pandaLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.pandaLeg3 = new ModelRenderer(this, 0, 18);
        this.pandaLeg3.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.pandaLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.pandaLeg4 = new ModelRenderer(this, 0, 18);
        this.pandaLeg4.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.pandaLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.pandaTail = new ModelRenderer(this, 9, 18);
        this.pandaTail.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.pandaTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.pandaHeadMain.setTextureOffset(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.pandaHeadMain.setTextureOffset(16, 14).addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.pandaHeadMain.setTextureOffset(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
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
            this.pandaHeadMain.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.pandaBody.render(scale);
            this.pandaLeg1.render(scale);
            this.pandaLeg2.render(scale);
            this.pandaLeg3.render(scale);
            this.pandaLeg4.render(scale);
            this.pandaTail.renderWithRotation(scale);
            this.pandaMane.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.pandaHeadMain.renderWithRotation(scale);
            this.pandaBody.render(scale);
            this.pandaLeg1.render(scale);
            this.pandaLeg2.render(scale);
            this.pandaLeg3.render(scale);
            this.pandaLeg4.render(scale);
            this.pandaTail.renderWithRotation(scale);
            this.pandaMane.render(scale);
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
    {
        EntityPanda entitypanda = (EntityPanda)entitylivingbaseIn;

        this.pandaTail.rotateAngleY = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;

        if (entitypanda.isSitting())
        {
            this.pandaMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.pandaMane.rotateAngleX = ((float)Math.PI * 2F / 5F);
            this.pandaMane.rotateAngleY = 0.0F;
            this.pandaBody.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.pandaBody.rotateAngleX = ((float)Math.PI / 4F);
            this.pandaTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.pandaLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
            this.pandaLeg1.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.pandaLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
            this.pandaLeg2.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.pandaLeg3.rotateAngleX = 5.811947F;
            this.pandaLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.pandaLeg4.rotateAngleX = 5.811947F;
            this.pandaLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);
        }
        else
        {
            this.pandaBody.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.pandaBody.rotateAngleX = ((float)Math.PI / 2F);
            this.pandaMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.pandaMane.rotateAngleX = this.pandaBody.rotateAngleX;
            this.pandaTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.pandaLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.pandaLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.pandaLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.pandaLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.pandaLeg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
            this.pandaLeg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.pandaLeg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_78086_3_;
            this.pandaLeg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
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
        this.pandaHeadMain.rotateAngleX = headPitch * 0.017453292F;
        this.pandaHeadMain.rotateAngleY = netHeadYaw * 0.017453292F;
        this.pandaTail.rotateAngleX = ageInTicks;
    }
}