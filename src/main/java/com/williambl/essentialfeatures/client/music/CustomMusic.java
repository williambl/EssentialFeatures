package com.williambl.essentialfeatures.client.music;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.biome.Biome;

public class CustomMusic {
	
	static final int OCEAN = 0;
	static final int DEEP_OCEAN = 24;
	
    private final static Random rand = new Random();
	
	public static ISound PlayMusic (ISound musicIn) {
		Minecraft mc = Minecraft.getMinecraft();
		WorldClient world = mc.world;
		EntityPlayerSP player = mc.player;

		if (rand.nextFloat() > 0.5) {
			return musicIn;
		}
		
		if (world == null) {
			return musicIn;
		} else {
			switch (Biome.getIdForBiome(world.getBiome(player.getPosition()))) {
			case OCEAN:
		        musicIn = PositionedSoundRecord.getMusicRecord(ModSound.OCEAN);
				return musicIn;
			case DEEP_OCEAN:
		        musicIn = PositionedSoundRecord.getMusicRecord(ModSound.OCEAN);
				return musicIn;
			default:
				return musicIn;
			}
		}
	}

}
