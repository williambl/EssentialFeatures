package net.anti_quark.EssentialFeatures.client;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent.SoundSourceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {
	
	@SubscribeEvent
	public void playSoundEvent (SoundSourceEvent event) {
	    System.out.println(event.getSound().getSound().getSoundLocation());
	}

}
