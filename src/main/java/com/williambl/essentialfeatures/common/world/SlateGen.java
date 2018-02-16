package com.williambl.essentialfeatures.common.world;

import java.util.Random;

import com.williambl.essentialfeatures.common.block.BlockSlate;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
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
			generateOre(ModBlocks.SLATE.getDefaultState().withProperty(BlockSlate.LAYERS, 8), world, random, chunkX, chunkZ, 40, 80, 10, 25);
	}
	
	public void generateOre(IBlockState block, World world, Random random, int chunkX, int chunkZ, int minY, int maxY, int chancesToSpawn, int veinSize) {
		int heightRange = maxY - minY;
		WorldGenMinable worldgen = new WorldGenMinable(block, veinSize);
		
		for (int i = 0; i < chancesToSpawn; i++) {
			int randX = random.nextInt(16);
			int randY = random.nextInt(heightRange) + minY;
			int randZ = random.nextInt(16);
			BlockPos pos = new BlockPos(chunkX+randX, randY, chunkZ+randZ);

			worldgen.generate(world, random, pos);
		}
	}
}
