package net.anti_quark.EssentialFeatures.common.block;

import net.anti_quark.EssentialFeatures.common.item.ItemBlockWithSubtypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModBlocks {

	public static BlockViewedBlock VIEWED_BLOCK;
	public static BlockSmoothGlowstone SMOOTH_GLOWSTONE;
	public static BlockPolishedGlowstone POLISHED_GLOWSTONE;
	public static BlockStainedLamp STAINED_LAMP;
	public static BlockStainedLamp LIT_STAINED_LAMP;
	public static BlockSnowBrick SNOW_BRICK;
	public static BlockBlockBreaker BLOCK_BREAKER;
	public static BlockCryingObsidian CRYING_OBSIDIAN;
	public static BlockSpike SPIKE_BLOCK;
	public static BlockBlockPlacer BLOCK_PLACER;
	public static BlockDecorativeStone DECORATIVE_STONE;
	public static BlockBrickVariant BRICK_VARIANT;
	
	public static BlockFake SMOOTH_DOUBLE_STONE_SLAB;
	public static BlockFake SMOOTH_DOUBLE_SANDSTONE_SLAB;
	public static BlockFake SMOOTH_DOUBLE_RED_SANDSTONE_SLAB;
	
	public static void addBlocks() 
	{
        VIEWED_BLOCK = new BlockViewedBlock("viewed_block", Material.ROCK, 5, 5);
        SMOOTH_GLOWSTONE = new BlockSmoothGlowstone("smooth_glowstone", Material.GLASS, 1, 2);
        STAINED_LAMP = new BlockStainedLamp("stained_lamp", Material.GLASS, 0.3F, 1.5F, false);
        LIT_STAINED_LAMP = new BlockStainedLamp("lit_stained_lamp", Material.GLASS, 0.3F, 1.5F, true);
        POLISHED_GLOWSTONE = new BlockPolishedGlowstone("polished_glowstone", Material.GLASS, 1, 2);
        SNOW_BRICK = new BlockSnowBrick("snow_brick", Material.CRAFTED_SNOW, 1, 1);
        BLOCK_BREAKER = new BlockBlockBreaker("block_breaker", Material.PISTON, 5, 5);
        CRYING_OBSIDIAN = new BlockCryingObsidian("crying_obsidian", Material.ROCK, 100, 100);
        SPIKE_BLOCK = new BlockSpike("spike_block", Material.IRON, 1, 1);
        BLOCK_PLACER = new BlockBlockPlacer("block_placer", Material.ROCK, 5, 5);
        DECORATIVE_STONE = new BlockDecorativeStone("decorative_stone", Material.ROCK, 3, 3);
        BRICK_VARIANT = new BlockBrickVariant("brick_variant", Material.ROCK, 3, 3);
        
        SMOOTH_DOUBLE_STONE_SLAB = new BlockFake("smooth_double_stone_slab", CreativeTabs.BUILDING_BLOCKS,
        		Blocks.DOUBLE_STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.SEAMLESS, true).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.STONE));
        SMOOTH_DOUBLE_SANDSTONE_SLAB = new BlockFake("smooth_double_sandstone_slab", CreativeTabs.BUILDING_BLOCKS,
        		Blocks.DOUBLE_STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.SEAMLESS, true).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND));
        SMOOTH_DOUBLE_RED_SANDSTONE_SLAB = new BlockFake("smooth_double_red_sandstone_slab", CreativeTabs.BUILDING_BLOCKS,
        		Blocks.DOUBLE_STONE_SLAB2.getDefaultState().withProperty(BlockStoneSlabNew.SEAMLESS, true).withProperty(BlockStoneSlabNew.VARIANT, BlockStoneSlabNew.EnumType.RED_SANDSTONE));
    }
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		addBlocks();
	    event.getRegistry().registerAll(
	    		VIEWED_BLOCK,
	    		SMOOTH_GLOWSTONE,
	    		STAINED_LAMP,
	    		LIT_STAINED_LAMP,
	    		POLISHED_GLOWSTONE,
	    		SNOW_BRICK,
	    		BLOCK_BREAKER,
	    		CRYING_OBSIDIAN,
	    		SPIKE_BLOCK,
	    		BLOCK_PLACER,
	    		DECORATIVE_STONE,
	    		BRICK_VARIANT,
	    		SMOOTH_DOUBLE_STONE_SLAB,
	    		SMOOTH_DOUBLE_SANDSTONE_SLAB,
	    		SMOOTH_DOUBLE_RED_SANDSTONE_SLAB
	    		);
	}
	
	@SubscribeEvent
	public void registerItemBlocks(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new ItemBlock(VIEWED_BLOCK).setRegistryName(VIEWED_BLOCK.getRegistryName()),
				new ItemBlock(SMOOTH_GLOWSTONE).setRegistryName(SMOOTH_GLOWSTONE.getRegistryName()),
				new ItemBlockWithSubtypes(STAINED_LAMP, true, STAINED_LAMP.names).setRegistryName(STAINED_LAMP.getRegistryName()),
				new ItemBlockWithSubtypes(LIT_STAINED_LAMP, true, LIT_STAINED_LAMP.names).setRegistryName(LIT_STAINED_LAMP.getRegistryName()),
				new ItemBlock(POLISHED_GLOWSTONE).setRegistryName(POLISHED_GLOWSTONE.getRegistryName()),
				new ItemBlock(SNOW_BRICK).setRegistryName(SNOW_BRICK.getRegistryName()),
				new ItemBlock(BLOCK_BREAKER).setRegistryName(BLOCK_BREAKER.getRegistryName()),
				new ItemBlock(CRYING_OBSIDIAN).setRegistryName(CRYING_OBSIDIAN.getRegistryName()),
				new ItemBlock(SPIKE_BLOCK).setRegistryName(SPIKE_BLOCK.getRegistryName()),
				new ItemBlock(BLOCK_PLACER).setRegistryName(BLOCK_PLACER.getRegistryName()),
				new ItemBlockWithSubtypes(DECORATIVE_STONE, true, DECORATIVE_STONE.names).setRegistryName(DECORATIVE_STONE.getRegistryName()),
				new ItemBlockWithSubtypes(BRICK_VARIANT, true, BRICK_VARIANT.names).setRegistryName(BRICK_VARIANT.getRegistryName()),
				new ItemBlock(SMOOTH_DOUBLE_STONE_SLAB).setRegistryName(SMOOTH_DOUBLE_STONE_SLAB.getRegistryName()),
				new ItemBlock(SMOOTH_DOUBLE_SANDSTONE_SLAB).setRegistryName(SMOOTH_DOUBLE_SANDSTONE_SLAB.getRegistryName()),
				new ItemBlock(SMOOTH_DOUBLE_RED_SANDSTONE_SLAB).setRegistryName(SMOOTH_DOUBLE_RED_SANDSTONE_SLAB.getRegistryName())
				);
	}

	public static void initModels() 
	{
		VIEWED_BLOCK.initModel();
		SMOOTH_GLOWSTONE.initModel();
		STAINED_LAMP.initModel();
		LIT_STAINED_LAMP.initModel();
		POLISHED_GLOWSTONE.initModel();
		SNOW_BRICK.initModel();
		BLOCK_BREAKER.initModel();
		CRYING_OBSIDIAN.initModel();
		SPIKE_BLOCK.initModel();
		BLOCK_PLACER.initModel();
		DECORATIVE_STONE.initModel();
		BRICK_VARIANT.initModel();
		
		SMOOTH_DOUBLE_STONE_SLAB.initModel();
		SMOOTH_DOUBLE_SANDSTONE_SLAB.initModel();
		SMOOTH_DOUBLE_RED_SANDSTONE_SLAB.initModel();
	}
}
