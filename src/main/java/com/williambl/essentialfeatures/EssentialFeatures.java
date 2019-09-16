package com.williambl.essentialfeatures;

import com.williambl.essentialfeatures.client.ClientEventHandler;
import com.williambl.essentialfeatures.common.CommonEventHandler;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.entity.ModEntities;
import com.williambl.essentialfeatures.common.item.ModItems;
import com.williambl.essentialfeatures.common.world.ModWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(EssentialFeatures.MODID)
@Mod.EventBusSubscriber(modid = EssentialFeatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EssentialFeatures {
    //TODO: Move recipes into JSONs

    public static final String MODID = "essentialfeatures";

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        ModWorld.registerWorldGenerators();
        ModEntities.initRenderers();

        CommonEventHandler handler = new CommonEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
    }

    @SubscribeEvent
    public void modConfig(ModConfig.ModConfigEvent event)
    {
        ModConfig config = event.getConfig();
        if (config.getSpec() == com.williambl.essentialfeatures.common.config.ModConfig.CLIENT_SPEC)
            com.williambl.essentialfeatures.common.config.ModConfig.refreshClient();
        else if (config.getSpec() == com.williambl.essentialfeatures.common.config.ModConfig.SERVER_SPEC)
            com.williambl.essentialfeatures.common.config.ModConfig.refreshServer();
    }

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        ClientEventHandler handler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        ModBlocks.RegistrationHandler.registerBlockColors();
        ModItems.RegistrationHandler.registerItemColors();
    }

}
