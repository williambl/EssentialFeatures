package net.anti_quark.EssentialFeatures.client.music;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSound {
	
	public static SoundEvent OCEAN;
	
	public static SoundEvent RECORD_SCARLET;

	public static void addSounds() {
		ResourceLocation oceanlocation = new ResourceLocation("essentialfeatures", "ocean_music");
		OCEAN = new SoundEvent(oceanlocation);
		GameRegistry.register(OCEAN, oceanlocation);
		
		ResourceLocation scarletlocation = new ResourceLocation("essentialfeatures", "record_scarlet");
		RECORD_SCARLET = new SoundEvent(scarletlocation);
		GameRegistry.register(RECORD_SCARLET, scarletlocation);
	}
}
