package com.williambl.essentialfeatures.client.music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;

public class CustomMusic {

    public static ISound PlayMusic(ISound musicIn) {
        Minecraft mc = Minecraft.getInstance();
        WorldClient world = mc.world;
        EntityPlayerSP player = mc.player;

        if (world.rand.nextFloat() > 0.5) {
            return musicIn;
        }

        switch (world.getBiome(player.getPosition()).getCategory()) {
            case OCEAN:
                musicIn = SimpleSound.getMusicRecord(ModSound.OCEAN);
                return musicIn;
            default:
                return musicIn;
        }
    }

}
