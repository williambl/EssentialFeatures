package net.anti_quark.EssentialFeatures.common.block;

import net.minecraft.block.material.Material;

public class ModBlocks {

	public static BlockViewedBlock viewedBlock;
	public static BlockSmoothGlowstone smoothGlowstone;
	
	public static void addBlocks() {
        viewedBlock = new BlockViewedBlock("viewedblock", Material.ROCK, 5, 5);
        smoothGlowstone = new BlockSmoothGlowstone("smoothglowstone", Material.GLASS, 1, 2);
    }

	public static void initModels() {
		viewedBlock.initModel();
		smoothGlowstone.initModel();
	}
}
