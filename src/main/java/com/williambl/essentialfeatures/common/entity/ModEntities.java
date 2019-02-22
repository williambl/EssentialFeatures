package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.render.entity.RenderSharpenedArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

    public static void addEntities() {
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(EssentialFeatures.MODID, "sharpened_arrow"), EntitySharpenedArrow.class, "SharpenedArrow", id++, EssentialFeatures.instance, 64, 3, true);
    }

    public static void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySharpenedArrow.class, RenderSharpenedArrow.FACTORY);
    }
}
