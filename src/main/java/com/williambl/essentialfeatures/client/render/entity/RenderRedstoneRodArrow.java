package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.common.entity.EntityRedstoneRodArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderRedstoneRodArrow extends RenderArrow<EntityRedstoneRodArrow> {

    private ResourceLocation texture = new ResourceLocation("essentialfeatures:textures/entity/projectiles/redstone_rod_arrow.png");

    public static final Factory FACTORY = new Factory();

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRedstoneRodArrow entity) {
        return texture;
    }

    public RenderRedstoneRodArrow(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    public static class Factory implements IRenderFactory<EntityRedstoneRodArrow> {

        @Override
        public Render<? super EntityRedstoneRodArrow> createRenderFor(RenderManager manager) {
            return new RenderRedstoneRodArrow(manager);
        }

    }
}
