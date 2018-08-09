package com.williambl.essentialfeatures.common.world;

import com.williambl.essentialfeatures.common.config.ModConfig;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorld {
	
	public static SlateGen SLATE = new SlateGen();
	public static NettlesGen NETTLES = new NettlesGen();
	
	public static void registerWorldGenerators() {
		if (ModConfig.slateGen)
			GameRegistry.registerWorldGenerator(SLATE, 0);

		if (ModConfig.nettleGen)
			GameRegistry.registerWorldGenerator(NETTLES, 0);
	}
}
