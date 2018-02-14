package com.williambl.essentialfeatures.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPanda extends RenderLiving {

	private ResourceLocation pandaTexture;


	public RenderPanda(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
		setEntityTexture();
	}
	
    protected void setEntityTexture()
    {
        pandaTexture = new ResourceLocation("textures/entity/wolf/wol.png");

    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return pandaTexture;
	}

    
    

}
