package com.williambl.essentialfeatures.common.loot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModLootTables {

    private static LootEntry.Builder entry = TableLootEntry.builder(new ResourceLocation("essentialfeatures:inject/loot_chests")).weight(3);

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onLootTableLoad(LootTableLoadEvent event) {
            ResourceLocation name = event.getName();
            if (name.equals(LootTables.CHESTS_DESERT_PYRAMID)
                    || name.equals(LootTables.CHESTS_ABANDONED_MINESHAFT)
                    || name.equals(LootTables.CHESTS_JUNGLE_TEMPLE)
                    || name.equals(LootTables.CHESTS_SIMPLE_DUNGEON)
                    || name.equals(LootTables.CHESTS_NETHER_BRIDGE)
                    || name.equals(LootTables.CHESTS_IGLOO_CHEST)
            ) {
                LootPool pool = LootPool.builder().addEntry(entry).name("injected_loot_chests").build();
                event.getTable().addPool(pool);
            }
        }
    }
}
