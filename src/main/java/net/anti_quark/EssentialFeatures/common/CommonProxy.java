package net.anti_quark.EssentialFeatures.common;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.craft.ModRecipes;
import net.anti_quark.EssentialFeatures.common.entity.ModEntities;

public class CommonProxy {

	public void preInit() {
		ModBlocks.addBlocks();
	}

	public void init() {
		ModRecipes.addRecipes();
		ModEntities.addVillagers();
	}

	public void postInit() {
		
	}

}
