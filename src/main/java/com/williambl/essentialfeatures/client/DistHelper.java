package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.client.music.MovingSound;
import net.minecraft.client.Minecraft;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.UUID;

public class DistHelper {

    public static void playDiscToPlayer(UUID playerUUID, ResourceLocation disc) {
        Minecraft.getInstance().getSoundHandler().stop();
        Minecraft.getInstance().getSoundHandler().play(
                new MovingSound(
                        Minecraft.getInstance().world.getPlayerByUuid(playerUUID),
                        ((MusicDiscItem) Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(disc))).getSound()
                )
        );
    }

    public static void stopDisc(ResourceLocation disc) {
        Minecraft.getInstance().getSoundHandler().stop(((MusicDiscItem) Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(disc))).getSound().getName(), SoundCategory.NEUTRAL);
    }
}
