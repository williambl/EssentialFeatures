package net.anti_quark.EssentialFeatures.client;

import net.anti_quark.EssentialFeatures.client.render.entity.PandaRenderFactory;
import net.anti_quark.EssentialFeatures.common.CommonProxy;
import net.anti_quark.EssentialFeatures.common.entity.EntityPanda;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit()
	{
		super.preInit();
		RenderingRegistry.registerEntityRenderingHandler(EntityPanda.class, new PandaRenderFactory(){});
	}
    
	@Override
	public void init()
	{
		super.init();
		ClientEventHandler handler = new ClientEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
 	}
	
	@Override
	public void postInit()
	{
		super.postInit();
	}
}
