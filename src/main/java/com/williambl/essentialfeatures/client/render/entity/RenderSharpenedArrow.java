package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.EntitySharpenedArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSharpenedArrow extends ArrowRenderer<EntitySharpenedArrow> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/sharpened_arrow.png");

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySharpenedArrow entity) {
        return texture;
    }

    public RenderSharpenedArrow(EntityRendererManager rendermanagerIn) {
        super(rendermanagerIn);
    }

}
