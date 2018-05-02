package com.williambl.essentialfeatures.common.config;

import com.williambl.essentialfeatures.EssentialFeatures;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = EssentialFeatures.MODID)
public class ModConfig {

    @Config.Comment("Add mechanic villagers")
    @Config.RequiresMcRestart
    public static boolean villagers = true;

    @Config.Comment("Add smelting recipes")
    @Config.RequiresMcRestart
    public static boolean smelting = true;

    @Config.Comment("Add blocks")
    @Config.RequiresMcRestart
    public static boolean blocks = true;

    @Config.Comment("Generate slate")
    @Config.RequiresMcRestart
    public static boolean slateGen = true;

    @Config.Comment("Add items")
    @Config.RequiresMcRestart
    public static boolean items = true;

    @Config.Comment("Range for viewed block")
    @Config.RequiresWorldRestart
    public static int viewedRange = 50;

    @Config.Comment("Ticks between viewed block checks")
    @Config.RequiresWorldRestart
    public static int viewedDelay = 2;

    @Mod.EventBusSubscriber(modid = EssentialFeatures.MODID)
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(EssentialFeatures.MODID)) {
                ConfigManager.sync(EssentialFeatures.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
