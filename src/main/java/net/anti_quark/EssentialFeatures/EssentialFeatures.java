package net.anti_quark.EssentialFeatures;

import net.anti_quark.EssentialFeatures.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EssentialFeatures.MODID, version = EssentialFeatures.VERSION)
public class EssentialFeatures {
	
	@Mod.Instance("EssentialFeatures")
	public static EssentialFeatures instance;
	
    public static final String MODID = "EssentialFeatures";
    public static final String VERSION = "0.1";
    
    @SidedProxy(clientSide="net.anti_quark.EssentialFeatures.client.ClientProxy", serverSide="net.anti_quark.EssentialFeatures.server.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
    	proxy.preInit();
	}

    @EventHandler
	public void init(FMLInitializationEvent event)
	{
    	proxy.init();
	}
    
    @EventHandler
	public void Postinit(FMLPostInitializationEvent event)
	{
    	proxy.postInit();
	}

}
