package com.williambl.essentialfeatures.client.render.entity;

import com.williambl.essentialfeatures.client.model.ModelPanda;
import com.williambl.essentialfeatures.common.entity.EntityPanda;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class PandaRenderFactory implements IRenderFactory<EntityPanda> {

    @Override
    public Render<? super EntityPanda> createRenderFor(RenderManager manager) {
        return new RenderPanda(manager, new ModelPanda(), 1.0F);
    }

}
