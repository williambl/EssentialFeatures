package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.entity.merchant.villager.VillagerEntity.EmeraldForItems;
import net.minecraft.entity.merchant.villager.VillagerEntity.ListItemForEmeralds;
import net.minecraft.entity.merchant.villager.VillagerEntity.PriceInfo;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerMechanic {

    protected static final VillagerRegistry.VillagerProfession MECHANIC_PROFESSION = new VillagerRegistry.VillagerProfession("mechanic", "minecraft:textures/entity/villager/villager.png", "");
    protected static final VillagerRegistry.VillagerCareer MECHANIC_CAREER = new VillagerRegistry.VillagerCareer(MECHANIC_PROFESSION, "mechanic")
            .addTrade(1, new EmeraldForItems(Items.REDSTONE, new PriceInfo(20, 1)))
            .addTrade(2, new ListItemForEmeralds(new ItemStack(Blocks.REDSTONE_LAMP), new PriceInfo(1, 3)))
            .addTrade(2, new ListItemForEmeralds(new ItemStack(ModBlocks.STAINED_LAMPS[4]), new PriceInfo(1, 5)));

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerVillagers(RegistryEvent.Register<VillagerProfession> event) {

            event.getRegistry().registerAll(
                    MECHANIC_PROFESSION
            );
        }
    }
}
