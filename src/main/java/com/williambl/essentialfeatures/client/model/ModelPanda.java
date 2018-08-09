package com.williambl.essentialfeatures.client.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;

public class ModelPanda extends ModelQuadruped {
    public ModelPanda() {
        super(12, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        float f = 0.5F;
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
    }
    /*
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
        EntityPanda entitypanda = (EntityPanda)entitylivingbaseIn;

        if (entitypanda.isSitting())
        {
            body.setRotationPoint(0.0F, 17.0F, 2.0F);
            this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
            this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
            this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
            this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
            this.leg1.rotateAngleX = 90F;
            this.leg2.rotateAngleX = 90F;
            this.leg3.rotateAngleX = 90F;
            this.leg4.rotateAngleX = 90F;
        }
        
        else
        {
            this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
            this.body.rotateAngleX = ((float)Math.PI / 2F);
            this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
            this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
            this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
            this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
            this.leg1.rotateAngleX = 0F;
            this.leg2.rotateAngleX = 0F;
            this.leg3.rotateAngleX = 0F;
            this.leg4.rotateAngleX = 0F;
        }
    }
    */

    public ModelPanda(float scale) {
        super(6, scale);
        this.childYOffset = 4.0F;
    }
}