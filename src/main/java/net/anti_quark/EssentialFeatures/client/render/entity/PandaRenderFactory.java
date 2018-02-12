package net.anti_quark.EssentialFeatures.client.render.entity;

import net.anti_quark.EssentialFeatures.client.model.ModelPanda;
import net.anti_quark.EssentialFeatures.common.entity.EntityPanda;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class PandaRenderFactory implements IRenderFactory<EntityPanda> {

    @Override
    public Render<? super EntityPanda> createRenderFor(RenderManager manager) {
        return new RenderPanda(manager, new ModelPanda(), 1.0F);
    }

}
