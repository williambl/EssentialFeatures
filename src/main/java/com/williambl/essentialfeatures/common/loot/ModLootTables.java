package com.williambl.essentialfeatures.common.loot;

import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ILootGenerator;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModLootTables {

    public static ILootGenerator entry = new ItemLootEntry(
            ModItems.PORTABLE_JUKEBOX, 20, 50, new ILootFunction[0], new ILootCondition[0], "essentialfeatures:loot_portable_jukebox");

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
                    ) {
                event.getTable().getPool("main").addEntry(entry);
            }
        }
    }
}
