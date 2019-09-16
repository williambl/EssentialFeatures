package com.williambl.essentialfeatures.common.loot;

public class ModLootTables {

    //TODO: Move this into a datapack or however it's done now
   /*public static ILootGenerator entry = ItemLootEntry.builder(
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
    }*/
}
