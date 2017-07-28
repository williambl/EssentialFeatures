package net.anti_quark.EssentialFeatures.client.music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class CustomMusic {
	
	static final int OCEAN = 0;
	static final int DEEP_OCEAN = 24;
	
	public static ISound PlayMusic (ISound musicIn) {
		Minecraft mc = Minecraft.getMinecraft();
		WorldClient world = mc.theWorld;
		EntityPlayerSP player = mc.thePlayer;
		
		if (world == null) {
			return musicIn;
		}
		else {
			System.out.println(Biome.getIdForBiome(world.getBiome(player.getPosition())));
			switch (Biome.getIdForBiome(world.getBiome(player.getPosition()))) {
			case OCEAN:
				return null;
			case DEEP_OCEAN:
				return null;
			default:
				return musicIn;
			}
		}
	}

}
