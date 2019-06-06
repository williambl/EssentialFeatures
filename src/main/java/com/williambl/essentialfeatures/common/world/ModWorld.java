package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.BlockSlate;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.config.ModConfig;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorld {

    public static void registerWorldGenerators() {
        if (ModConfig.INSTANCE.slate.get()) {
            for (Biome biome : new Biome[] {Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST_HILLS}) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createCompositeFeature(Feature.BUSH, new BushConfig(ModBlocks.NETTLES), Biome.TWICE_SURFACE_WITH_CHANCE, new ChanceConfig(2)));
            }
        }

        if (ModConfig.INSTANCE.nettles.get()) {
            ForgeRegistries.BIOMES.forEach(
                    biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, ModBlocks.SLATE.getDefaultState().with(BlockSlate.LAYERS, 8), 33), Biome.COUNT_RANGE, new CountRangeConfig(10, 40, 0, 80)))
            );
        }
    }
}
