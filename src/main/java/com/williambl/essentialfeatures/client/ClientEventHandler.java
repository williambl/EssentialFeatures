package com.williambl.essentialfeatures.client;

import com.williambl.essentialfeatures.client.music.CustomMusic;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
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
