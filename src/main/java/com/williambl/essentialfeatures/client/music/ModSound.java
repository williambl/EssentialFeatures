package com.williambl.essentialfeatures.client.music;

import com.williambl.essentialfeatures.EssentialFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EssentialFeatures.MODID)
public class ModSound {

    public static SoundEvent OCEAN;
    public static SoundEvent NETHER;
    public static SoundEvent RECORD_SCARLET;
    public static SoundEvent RECORD_LOFI;

    public static void addSoundEvents() {
        OCEAN = new SoundEvent(new ResourceLocation(EssentialFeatures.MODID, "ocean_music"));
        NETHER = new SoundEvent(new ResourceLocation(EssentialFeatures.MODID, "nether_music"));
        RECORD_SCARLET = new SoundEvent(new ResourceLocation(EssentialFeatures.MODID, "record_scarlet"));
        RECORD_LOFI = new SoundEvent(new ResourceLocation(EssentialFeatures.MODID, "record_lofi"));
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().registerAll(
                    OCEAN.setRegistryName("ocean_music"),
                    NETHER.setRegistryName("nether_music"),
                    RECORD_SCARLET.setRegistryName("record_scarlet"),
                    RECORD_LOFI.setRegistryName("record_lofi")
            );
        }
    }
}
