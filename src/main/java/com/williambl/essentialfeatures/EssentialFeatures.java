package com.williambl.essentialfeatures;

import com.williambl.essentialfeatures.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EssentialFeatures.MODID, version = EssentialFeatures.VERSION)
public class EssentialFeatures {
	
	@Mod.Instance("essentialfeatures")
	public static EssentialFeatures instance;
	
    public static final String MODID = "essentialfeatures";
    public static final String VERSION = "1.2.1";
    
    @SidedProxy(
    		clientSide="com.williambl.essentialfeatures.client.ClientProxy",
			serverSide="com.williambl.essentialfeatures.server.ServerProxy")
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
