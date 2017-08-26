package net.anti_quark.EssentialFeatures.client.music;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSound {
	
	public static SoundEvent OCEAN;
	
	public static SoundEvent RECORD_SCARLET;

	public static void addSounds() {
		ResourceLocation oceanlocation = new ResourceLocation("essentialfeatures", "ocean_music");
		OCEAN = new SoundEvent(oceanlocation);
		OCEAN.setRegistryName(oceanlocation);
		
		ResourceLocation scarletlocation = new ResourceLocation("essentialfeatures", "record_scarlet");
		RECORD_SCARLET = new SoundEvent(scarletlocation);
		RECORD_SCARLET.setRegistryName(scarletlocation);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
			ModSound.addSounds();
			event.getRegistry().registerAll(
					OCEAN,
					RECORD_SCARLET
			);
		}
	}
}
