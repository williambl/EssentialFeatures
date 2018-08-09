package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.BlockSlate;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SlateGen implements IWorldGenerator {

    private WorldGenMinable worldGenMinable;

    public SlateGen() {
        worldGenMinable = new WorldGenMinable(ModBlocks.SLATE.getDefaultState().withProperty(BlockSlate.LAYERS, 8), 25);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0)
            generateOre(worldGenMinable, world, random, chunkX, chunkZ, 40, 80, 10);
    }

    private void generateOre(WorldGenMinable worldGen, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int chancesToSpawn) {
        int heightRange = maxY - minY;

        for (int i = 0; i < chancesToSpawn; i++) {
            int randY = random.nextInt(heightRange) + minY;
            BlockPos pos = new BlockPos(chunkX * 16 + 8, randY, chunkZ * 16 + 8);
            worldGen.generate(world, random, pos);
        }
    }
}
