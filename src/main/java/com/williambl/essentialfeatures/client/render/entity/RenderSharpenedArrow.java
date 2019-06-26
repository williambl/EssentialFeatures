package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.EntitySharpenedArrow;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderSharpenedArrow extends ArrowRenderer<EntitySharpenedArrow> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/sharpened_arrow.png");

    public static final Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySharpenedArrow entity) {
        return texture;
    }

    public RenderSharpenedArrow(EntityRendererManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    public static class Factory implements IRenderFactory<EntitySharpenedArrow> {

        @Override
        public EntityRenderer<? super EntitySharpenedArrow> createRenderFor(EntityRendererManager manager) {
            return new RenderSharpenedArrow(manager);
        }

    }
}
