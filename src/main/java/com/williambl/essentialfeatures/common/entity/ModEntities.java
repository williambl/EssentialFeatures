package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.render.entity.RedstoneRodArrowRenderer;
import com.williambl.essentialfeatures.client.render.entity.SharpenedArrowRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EssentialFeatures.MODID)
public class ModEntities {

    @ObjectHolder("sharpened_arrow")
    public static EntityType<SharpenedArrowEntity> SHARPENED_ARROW;
    @ObjectHolder("redstone_rod_arrow")
    public static EntityType<RedstoneRodArrowEntity> REDSTONE_ROD_ARROW;

    public static void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(SharpenedArrowEntity.class, SharpenedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RedstoneRodArrowEntity.class, RedstoneRodArrowRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> e) {
        e.getRegistry().registerAll(
                EntityType.Builder.create(SharpenedArrowEntity::new, EntityClassification.MISC).size(0.5f, 0.5f).setCustomClientFactory(SharpenedArrowEntity::new).build(EssentialFeatures.MODID + ":sharpened_arrow").setRegistryName("sharpened_arrow"),
                EntityType.Builder.create(RedstoneRodArrowEntity::new, EntityClassification.MISC).size(0.5f, 0.5f).setCustomClientFactory(RedstoneRodArrowEntity::new).build(EssentialFeatures.MODID + ":redstone_rod_arrow").setRegistryName("redstone_rod_arrow")
        );
    }
}
