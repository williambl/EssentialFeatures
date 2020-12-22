package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.music.CustomMusic;
import com.williambl.essentialfeatures.common.config.Config;
import net.minecraft.client.audio.ISound;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class ClientEventHandler {

    @SubscribeEvent
    public void playSoundEvent(PlaySoundEvent event) {
        if (Config.customMusic) {
            if (event.getSound().getCategory() == SoundCategory.MUSIC && !event.getName().startsWith("music.essential_features")) {
                ISound result = CustomMusic.PlayMusic(event.getSound());
                event.setResultSound(result);
            }
        }
    }

    @SubscribeEvent
    public void OnPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
        if (Config.spawnExplosion) {
            e.getPlayer().world.addParticle(ParticleTypes.EXPLOSION, e.getPlayer().getPosX(), e.getPlayer().getPosY(), e.getPlayer().getPosZ(), 1.0D, 0.0D, 0.0D);
            e.getPlayer().world.playSound(null, e.getPlayer().getPosX(), e.getPlayer().getPosY(), e.getPlayer().getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 4.0F, (1.0F + (e.getPlayer().world.rand.nextFloat() - e.getPlayer().world.rand.nextFloat()) * 0.2F) * 0.7F);
        }
    }

    @SubscribeEvent
    public void playerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (Config.motd) {
            String messageURL = "https://raw.githubusercontent.com/williambl/essentialfeatures-motd/master/motd-" + ModList.get().getModContainerById(EssentialFeatures.MODID).get().getModInfo().getVersion();
            URL url;
            try {
                url = new URL(messageURL);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String output;

                while ((output = bufferedReader.readLine()) != null)
                    event.getPlayer().sendMessage(new StringTextComponent(output), UUID.fromString("41C82C87-7AfB-4024-BA57-13D2C99CAE77"));
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
