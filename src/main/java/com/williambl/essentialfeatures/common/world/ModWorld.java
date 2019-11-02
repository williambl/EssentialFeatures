package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.block.SlateBlock;
import com.williambl.essentialfeatures.common.config.ModConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorld {

    public static void registerWorldGenerators() {
        if (ModConfig.generateSlate) {
            for (Biome biome : new Biome[] {Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST_HILLS}) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(ModBlocks.NETTLES.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(2)));
            }
        }

        if (ModConfig.generateNettles) {
            ForgeRegistries.BIOMES.forEach(
                    biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SLATE.getDefaultState().with(SlateBlock.LAYERS, 8), 33), Placement.COUNT_RANGE, new CountRangeConfig(10, 40, 0, 80)))
            );
        }
    }
}
