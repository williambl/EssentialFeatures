package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.RedstoneRodArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RedstoneRodArrowRenderer extends ArrowRenderer<RedstoneRodArrowEntity> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/redstone_rod_arrow.png");

    public RedstoneRodArrowRenderer(EntityRendererManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(RedstoneRodArrowEntity entity) {
        return texture;
    }

}
