package com.williambl.essentialfeatures.common;

import com.williambl.essentialfeatures.client.music.ModSound;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.config.ModConfig;
import com.williambl.essentialfeatures.common.craft.ModRecipes;
import com.williambl.essentialfeatures.common.entity.ModEntities;
import com.williambl.essentialfeatures.common.item.ModItems;
import com.williambl.essentialfeatures.common.world.ModWorld;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void preInit() {
        ModSound.addSounds();
        if (ModConfig.blocks)
            ModBlocks.addBlocks();
        if (ModConfig.items)
            ModItems.addItems();
        ModEntities.addEntities();
        ModWorld.registerWorldGenerators();
    }

    public void init() {
        if (ModConfig.smelting)
            ModRecipes.addRecipes();

        CommonEventHandler handler = new CommonEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
    }

    public void postInit() {

    }

}
