package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.block.SlateBlock;
import com.williambl.essentialfeatures.common.config.Config;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorld {

    public static void registerWorldGenerators() {
        if (Config.nettles) {
            for (Biome biome : new Biome[] {Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST_HILLS}) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227248_z_.configure(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.NETTLES.getDefaultState()), new SimpleBlockPlacer()).build()).createDecoratedFeature(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(2))));
            }
        }

        if (Config.slate) {
            ForgeRegistries.BIOMES.forEach(
                    biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SLATE.getDefaultState().with(SlateBlock.LAYERS, 8), 33)).createDecoratedFeature(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 40, 0, 80))))
            );
        }
    }
}
