package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.music.CustomMusic;
import com.williambl.essentialfeatures.common.config.ModConfig;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
        if (ModConfig.showMOTD) {
            String messageURL = "https://raw.githubusercontent.com/williambl/essentialfeatures-motd/master/motd-" + ModList.get().getModContainerById(EssentialFeatures.MODID).get().getModInfo().getVersion();
            URL url;
            try {

                url = new URL(messageURL);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String output;

                while ((output = bufferedReader.readLine()) != null)
                    event.getPlayer().sendMessage(new StringTextComponent(output));
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
