package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.music.CustomMusic;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ClientEventHandler {

    @SubscribeEvent
    public void playSoundEvent(PlaySoundEvent event) {
        if (event.getSound().getCategory() == SoundCategory.MUSIC && !event.getName().startsWith("music.essential_features")) {
            ISound result = CustomMusic.PlayMusic(event.getSound());
            event.setResultSound(result);
        }
    }

    @SubscribeEvent
    public void playerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        event.player.sendMessage(new TextComponentString("Thank you for installing Essential Features v" + EssentialFeatures.VERSION + "!"));
    }

}
