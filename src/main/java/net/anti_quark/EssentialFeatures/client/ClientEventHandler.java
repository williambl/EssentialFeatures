package net.anti_quark.EssentialFeatures.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent.SoundSourceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {
	
	@SubscribeEvent
	public void playSoundEvent (SoundSourceEvent event) {
	    if (event.getSound().getSound().getSoundLocation().toString().substring(0, 15) == "minecraft:music") {
	    	Minecraft.getMinecraft().getSoundHandler().stopSound(event.getSound());
	    	//event.getManager().playSound(p_sound);
	    }
	}

}
