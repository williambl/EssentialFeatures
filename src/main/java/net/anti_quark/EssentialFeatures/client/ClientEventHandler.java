package net.anti_quark.EssentialFeatures.client;

import net.anti_quark.EssentialFeatures.client.music.CustomMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent.SoundSourceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {
	
	@SubscribeEvent
	public void playSoundEvent (PlaySoundEvent event) {
	    if (event.getSound().getCategory() == SoundCategory.MUSIC && !event.getName().startsWith("music.essential_features")) {
	    	    ISound result = CustomMusic.PlayMusic(event.getSound());
	    	    event.setResultSound(result);
	    }
	}

}
