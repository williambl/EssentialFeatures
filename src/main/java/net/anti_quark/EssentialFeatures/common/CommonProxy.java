package net.anti_quark.EssentialFeatures.common;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.craft.ModRecipes;
import net.anti_quark.EssentialFeatures.common.entity.ModEntities;
import net.anti_quark.EssentialFeatures.common.item.ModItems;

public class CommonProxy {

	public void preInit() {
		ModBlocks.addBlocks();
	}

	public void init() {
		ModItems.addItems();
		ModRecipes.addRecipes();
		ModEntities.addVillagers();
	}

	public void postInit() {
		
	}

}
