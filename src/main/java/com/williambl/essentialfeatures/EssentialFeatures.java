package com.williambl.essentialfeatures;

import com.williambl.essentialfeatures.client.ClientEventHandler;
import com.williambl.essentialfeatures.common.CommonEventHandler;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.config.Config;
import com.williambl.essentialfeatures.common.entity.ModEntities;
import com.williambl.essentialfeatures.common.item.ModItems;
import com.williambl.essentialfeatures.common.item.crafting.ModCrafting;
import com.williambl.essentialfeatures.common.networking.ModPackets;
import com.williambl.essentialfeatures.common.world.ModWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(EssentialFeatures.MODID)
public class EssentialFeatures {

    public static final String MODID = "essentialfeatures";

    public EssentialFeatures() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the config method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modConfig);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        Config.loadConfig(Config.CLIENT_SPEC, FMLPaths.CONFIGDIR.get().resolve("essentialfeatures-client.toml"));
        Config.loadConfig(Config.COMMON_SPEC, FMLPaths.CONFIGDIR.get().resolve("essentialfeatures-common.toml"));

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModPackets.registerPackets();
        ModItems.RegistrationHandler.registerDispenseBehaviours();
        ModCrafting.RegistrationHandler.registerRecipeTypes();
        ModWorld.init();

        CommonEventHandler handler = new CommonEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
    }

    private void modConfig(ModConfig.ModConfigEvent event)
    {
        ModConfig config = event.getConfig();
        if (config.getSpec() == Config.CLIENT_SPEC)
            Config.refreshClient();
        else if (config.getSpec() == Config.COMMON_SPEC)
            Config.refreshCommon();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModBlocks.RegistrationHandler.registerBlockColors();
        ModBlocks.RegistrationHandler.registerBlockRenderTypes();
        ModItems.RegistrationHandler.registerItemColors();
        ModEntities.initRenderers();

        ClientEventHandler handler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
    }

}
