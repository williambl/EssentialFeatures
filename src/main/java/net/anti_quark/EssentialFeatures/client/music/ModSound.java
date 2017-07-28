package net.anti_quark.EssentialFeatures.client.music;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSound {
	
	public static SoundEvent OCEAN;

	public static void addSounds() {
		ResourceLocation oceanlocation = new ResourceLocation("essentialfeatures", "ocean_music");
		OCEAN = new SoundEvent(oceanlocation);
		GameRegistry.register(OCEAN, oceanlocation);
	}
}
