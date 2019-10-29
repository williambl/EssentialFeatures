package com.williambl.essentialfeatures.client.music;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;

public class CustomMusic {

    public static ISound PlayMusic(ISound musicIn) {
        Minecraft mc = Minecraft.getInstance();
        ClientWorld world = mc.world;
        ClientPlayerEntity player = mc.player;
        if (world == null) {
            return musicIn;
        }

        if (world.rand.nextFloat() > 0.5) {
            return musicIn;
        }

        switch (world.getBiome(player.getPosition()).getCategory()) {
            case OCEAN:
                musicIn = SimpleSound.music(ModSound.OCEAN);
                return musicIn;
            default:
                return musicIn;
        }
    }

}
