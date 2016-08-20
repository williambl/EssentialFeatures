package net.anti_quark.EssentialFeatures.client;

import net.anti_quark.EssentialFeatures.client.render.entity.PandaRenderFactory;
import net.anti_quark.EssentialFeatures.client.render.entity.RenderPanda;
import net.anti_quark.EssentialFeatures.common.CommonProxy;
import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.entity.EntityPanda;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraft.client.model.ModelWolf;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit()
	{
    	super.preInit();
		ModBlocks.initModels();
		ModItems.initModels();
		RenderingRegistry.registerEntityRenderingHandler(EntityPanda.class, new PandaRenderFactory(){});
	}
    
	@Override
	public void init()
	{
		super.init();
	}
	
	@Override
	public void postInit()
	{
		super.postInit();
	}
}
