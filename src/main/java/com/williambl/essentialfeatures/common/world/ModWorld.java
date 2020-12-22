package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.block.SlateBlock;
import com.williambl.essentialfeatures.common.config.Config;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;


@Mod.EventBusSubscriber()
public class ModWorld {

    private static final ConfiguredFeature<?, ?> SLATE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.SLATE.getDefaultState().with(SlateBlock.LAYERS, 8), 33)).range(128).square().func_242731_b(4);
    private static final ConfiguredFeature<?, ?> NETTLES = Feature.FLOWER.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.NETTLES.getDefaultState()), new SimpleBlockPlacer()).build()).withPlacement(Placement.HEIGHTMAP_WORLD_SURFACE.configure(NoPlacementConfig.INSTANCE));

    static {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(EssentialFeatures.MODID, "slate"), SLATE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(EssentialFeatures.MODID, "nettles"), NETTLES);
    }

    @SubscribeEvent
    public static void registerWorldGenerators(final BiomeLoadingEvent event) {
        if (Config.nettles) {
            if (event.getCategory() == Biome.Category.FOREST) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> NETTLES);
            }
        }

        if (Config.slate) {
            event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> SLATE);
        }
    }
}
