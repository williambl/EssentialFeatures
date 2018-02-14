package com.williambl.essentialfeatures.common.world;

import java.util.Random;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SlateGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0)
			generateOre(ModBlocks.SLATE, world, random, chunkX, chunkZ, 70, 16, 10);
	}
	
	public void generateOre(Block block, World world, Random random, int chunk_x, int chunk_z, int maxY, int minY, int chancesToSpawn) {
		int heightRange = maxY - minY;
		WorldGenMinable worldgenminable = new WorldGenMinable(block.getDefaultState(), heightRange);
		
		for (int k1 = 0; k1 < chancesToSpawn; ++k1) {
			int xrand = random.nextInt(16);
			int yrand = random.nextInt(heightRange) + minY;
			int zrand = random.nextInt(16);
			BlockPos pos = new BlockPos(chunk_x+xrand, yrand, chunk_z+zrand);
			worldgenminable.generate(world, random, pos);
			System.out.println("attempted generation");
		}
	}
}
