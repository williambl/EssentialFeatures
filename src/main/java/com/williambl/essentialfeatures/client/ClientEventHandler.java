package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.music.CustomMusic;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
        event.player.sendMessage(new TextComponentString("Thank you for installing Essential Features v" + EssentialFeatures.VERSION + "!"));

        String updateServer = "https://example.com";
        URL url;
        try {

            url = new URL(updateServer);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(con.getInputStream()) );

            String output;

            while ((output = bufferedReader.readLine()) != null)
                event.player.sendMessage(new TextComponentString(output));
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
