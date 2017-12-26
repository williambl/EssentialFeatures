package net.anti_quark.EssentialFeatures.common.config;

import net.anti_quark.EssentialFeatures.EssentialFeatures;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = EssentialFeatures.MODID)
public class ModConfig {

    @Config.Comment("Add mechanic villagers")
    public static boolean villagers = true;

    @Config.Comment("Add crafting recipes")
    public static boolean crafting = true;

    @Config.Comment("Add blocks")
    public static boolean blocks = true;

    @Config.Comment("Generate slate")
    public static boolean slateGen = true;

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
