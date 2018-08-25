package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NettlesGen implements IWorldGenerator {

    private WorldGenBush worldGenBush;

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

            worldGen.generate(world, random, pos);
        }
    }
}
