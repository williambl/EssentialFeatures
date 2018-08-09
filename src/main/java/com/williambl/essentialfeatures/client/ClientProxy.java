package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.common.CommonProxy;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.entity.ModEntities;
import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        ModEntities.initRenderers();
    }

    @Override
    public void init() {
        super.init();
        ClientEventHandler handler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        ModBlocks.RegistrationHandler.registerBlockColors();
        ModItems.RegistrationHandler.registerItemColors();
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
