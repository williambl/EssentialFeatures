package net.anti_quark.EssentialFeatures.common.world;

import net.anti_quark.EssentialFeatures.common.config.ModConfig;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorld {
	
	public static SlateGen SLATE = new SlateGen();
	
	public static void registerWorldGenerators() {
		if (ModConfig.slateGen)
			GameRegistry.registerWorldGenerator(SLATE, 0);
		System.out.println("registering...");
	}
}
