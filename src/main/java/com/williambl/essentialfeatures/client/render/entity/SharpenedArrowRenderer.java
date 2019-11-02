package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.SharpenedArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SharpenedArrowRenderer extends ArrowRenderer<SharpenedArrowEntity> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/sharpened_arrow.png");

    public SharpenedArrowRenderer(EntityRendererManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(SharpenedArrowEntity entity) {
        return texture;
    }

}
