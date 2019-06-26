package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.EntityRedstoneRodArrow;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderRedstoneRodArrow extends ArrowRenderer<EntityRedstoneRodArrow> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/redstone_rod_arrow.png");

    public static final Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRedstoneRodArrow entity) {
        return texture;
    }

    public RenderRedstoneRodArrow(EntityRendererManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    public static class Factory implements IRenderFactory<EntityRedstoneRodArrow> {

        @Override
        public EntityRenderer<? super EntityRedstoneRodArrow> createRenderFor(EntityRendererManager manager) {
            return new RenderRedstoneRodArrow(manager);
        }

    }
}
