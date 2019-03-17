package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NettlesGen implements IWorldGenerator {

    private WorldGenBush worldGenBush;

    private List<Biome> biomes = Arrays.asList(Biomes.PLAINS, Biomes.MUTATED_PLAINS, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST, Biomes.MUTATED_FOREST, Biomes.MUTATED_ROOFED_FOREST, Biomes.ROOFED_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.MUTATED_BIRCH_FOREST_HILLS);

    public NettlesGen() {
        worldGenBush = new WorldGenBush(ModBlocks.NETTLES);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0)
            generatePlants(worldGenBush, world, random, chunkX, chunkZ, 40, 80, 2);
    }

    private void generatePlants(WorldGenBush worldGen, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int chancesToSpawn) {
        int heightRange = maxY - minY;

        for (int i = 0; i < chancesToSpawn; i++) {
            int randY = random.nextInt(heightRange) + minY;
            BlockPos pos = new BlockPos((chunkX * 16) + 8, randY, (chunkZ * 16) + 8);

            if (biomes.contains(world.getBiome(pos)))
            worldGen.generate(world, random, pos);
        }
    }
}
