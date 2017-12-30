package net.anti_quark.EssentialFeatures.common.oredict;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDict {

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		/**
		 * Register this mod's Ore Dictionary entries.
		 *
		 * @param event The event
		 */
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerOreDictEntries(final RegistryEvent.Register<Item> event) {
			OreDictionary.registerOre("", );
		}

	}
}
