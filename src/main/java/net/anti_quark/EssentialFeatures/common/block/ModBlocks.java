package net.anti_quark.EssentialFeatures.common.block;

import net.minecraft.block.material.Material;

public class ModBlocks {

	public static BlockViewedBlock viewedBlock;
	public static BlockSmoothGlowstone smoothGlowstone;
	public static BlockStainedLamp stainedLamp;
	public static BlockStainedLamp litStainedLamp;
	
	public static void addBlocks() {
        viewedBlock = new BlockViewedBlock("viewedblock", Material.ROCK, 5, 5);
        smoothGlowstone = new BlockSmoothGlowstone("smoothglowstone", Material.GLASS, 1, 2);
        stainedLamp = new BlockStainedLamp("stainedlamp", Material.GLASS, 0.3F, 1.5F, false);
        litStainedLamp = new BlockStainedLamp("litstainedlamp", Material.GLASS, 0.3F, 1.5F, true);
    }

	public static void initModels() {
		viewedBlock.initModel();
		smoothGlowstone.initModel();
		stainedLamp.initModel();
		litStainedLamp.initModel();
	}
}
