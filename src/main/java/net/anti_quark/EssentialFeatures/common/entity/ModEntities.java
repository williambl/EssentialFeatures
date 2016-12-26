package net.anti_quark.EssentialFeatures.common.entity;

import net.anti_quark.EssentialFeatures.EssentialFeatures;
import net.anti_quark.EssentialFeatures.client.render.entity.PandaRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class ModEntities {

	public static void addVillagers () 
	{
		VillagerMechanic.addVillagers();
	}
	
	public static void addEntities ()
	{
		//EntityRegistry.registerModEntity(null, EntityPanda.class, "panda", 0, EssentialFeatures.MODID, 48, 3, false, 222222, 202020);
	}
	
	public static void initRenderers ()
	{
	    //RenderingRegistry.registerEntityRenderingHandler(EntityPanda.class, new PandaRenderFactory(){});
	}
}
