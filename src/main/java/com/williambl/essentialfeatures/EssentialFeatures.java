package com.williambl.essentialfeatures;

import com.williambl.essentialfeatures.client.ClientEventHandler;
import com.williambl.essentialfeatures.common.CommonEventHandler;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.entity.ModEntities;
import com.williambl.essentialfeatures.common.item.ModItems;
import com.williambl.essentialfeatures.common.networking.ModPackets;
import com.williambl.essentialfeatures.common.world.ModWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EssentialFeatures.MODID)
public class EssentialFeatures {

    public static final String MODID = "essentialfeatures";

    public EssentialFeatures() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the config method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modConfig);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModWorld.registerWorldGenerators();
        ModPackets.registerPackets();
        ModItems.RegistrationHandler.registerDispenseBehaviours();

        CommonEventHandler handler = new CommonEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
    }

    private void modConfig(ModConfig.ModConfigEvent event)
    {
        ModConfig config = event.getConfig();
        if (config.getSpec() == com.williambl.essentialfeatures.common.config.ModConfig.CLIENT_SPEC)
            com.williambl.essentialfeatures.common.config.ModConfig.refreshClient();
        else if (config.getSpec() == com.williambl.essentialfeatures.common.config.ModConfig.SERVER_SPEC)
            com.williambl.essentialfeatures.common.config.ModConfig.refreshServer();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModBlocks.RegistrationHandler.registerBlockColors();
        ModItems.RegistrationHandler.registerItemColors();
        ModEntities.initRenderers();

        ClientEventHandler handler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
    }

}
