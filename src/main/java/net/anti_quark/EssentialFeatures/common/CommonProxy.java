package net.anti_quark.EssentialFeatures.common;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.craft.ModRecipes;
import net.anti_quark.EssentialFeatures.common.entity.ModEntities;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy {

	public void preInit() {
		ModBlocks.addBlocks();
		ModItems.addItems();
	}

	public void init() {
		ModRecipes.addRecipes();
		ModEntities.addVillagers();
		
		CommonEventHandler handler = new CommonEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
	}

	public void postInit() {
		
	}

}
