package net.anti_quark.EssentialFeatures.common.block;

import net.minecraft.block.material.Material;

public class ModBlocks {

	public static BlockViewedBlock VIEWED_BLOCK;
	public static BlockSmoothGlowstone SMOOTH_GLOWSTONE;
	public static BlockPolishedGlowstone POLISHED_GLOWSTONE;
	public static BlockStainedLamp STAINED_LAMP;
	public static BlockStainedLamp LIT_STAINED_LAMP;
	public static BlockSnowBrick SNOW_BRICK;
	public static BlockBlockBreaker BLOCK_BREAKER;
	public static BlockCryingObsidian CRYING_OBSIDIAN;
	
	public static void addBlocks() 
	{
        VIEWED_BLOCK = new BlockViewedBlock("viewedblock", Material.ROCK, 5, 5);
        SMOOTH_GLOWSTONE = new BlockSmoothGlowstone("smoothglowstone", Material.GLASS, 1, 2);
        STAINED_LAMP = new BlockStainedLamp("stainedlamp", Material.GLASS, 0.3F, 1.5F, false);
        LIT_STAINED_LAMP = new BlockStainedLamp("litstainedlamp", Material.GLASS, 0.3F, 1.5F, true);
        POLISHED_GLOWSTONE = new BlockPolishedGlowstone("polishedglowstone", Material.GLASS, 1, 2);
        SNOW_BRICK = new BlockSnowBrick("snowbrick", Material.CRAFTED_SNOW, 1, 1);
        BLOCK_BREAKER = new BlockBlockBreaker("blockbreaker", Material.PISTON, 5, 5);
        CRYING_OBSIDIAN = new BlockCryingObsidian("crying_obsidian", Material.ROCK, 100, 100);
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
	}
}
