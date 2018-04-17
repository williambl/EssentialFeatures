package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.EntitySharpenedArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderSharpenedArrow extends RenderArrow<EntitySharpenedArrow> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/sharpened_arrow.png");

    public static final Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySharpenedArrow entity) {
        return texture;
    }

    public RenderSharpenedArrow(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    public static class Factory implements IRenderFactory<EntitySharpenedArrow> {

        @Override
        public Render<? super EntitySharpenedArrow> createRenderFor(RenderManager manager) {
            return new RenderSharpenedArrow(manager);
        }

    }
}
