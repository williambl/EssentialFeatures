package net.anti_quark.EssentialFeatures.common;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.craft.ModRecipes;

public class CommonProxy {

	public void preInit() {
		ModBlocks.addBlocks();
	}

	public void init() {
		ModRecipes.addRecipes();
	}

	public void postInit() {
		
	}

}
