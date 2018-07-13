package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.block.BlockSlate;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class NettlesGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0)
			generatePlants(ModBlocks.NETTLES, world, random, chunkX, chunkZ, 40, 80, 10);
	}
	
	public void generatePlants(BlockBush block, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int chancesToSpawn) {
		int heightRange = maxY - minY;
		WorldGenBush worldgen = new WorldGenBush(block);
		
		for (int i = 0; i < chancesToSpawn; i++) {
			int randY = random.nextInt(heightRange) + minY;
			BlockPos pos = new BlockPos(chunkX*16, randY, chunkZ*16);

			worldgen.generate(world, random, pos);
		}
	}
}
