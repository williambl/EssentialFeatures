package net.anti_quark.EssentialFeatures.common.block;

import net.minecraft.block.material.Material;

public class ModBlocks {

	public static BlockViewedBlock viewedBlock;
	
	public static void addBlocks() {
        viewedBlock = new BlockViewedBlock("ViewedBlock", Material.ROCK, 5, 5);
    }

	public static void initModels() {
		viewedBlock.initModel();
	}
}
