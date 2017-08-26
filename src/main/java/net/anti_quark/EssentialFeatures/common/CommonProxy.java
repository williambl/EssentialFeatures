package net.anti_quark.EssentialFeatures.common;

import net.anti_quark.EssentialFeatures.client.music.ModSound;
import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.craft.ModRecipes;
import net.anti_quark.EssentialFeatures.common.entity.ModEntities;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy {

	public void preInit() {
	}

	public void init() {
		ModRecipes.addRecipes();		
		CommonEventHandler handler = new CommonEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
	}

	public void postInit() {
		
	}

}
