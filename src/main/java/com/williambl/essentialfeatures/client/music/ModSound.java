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

    @ObjectHolder("ocean_music")
    public static SoundEvent OCEAN;

    @ObjectHolder("record_scarlet")
    public static SoundEvent RECORD_SCARLET;

    @ObjectHolder("record_lofi")
    public static SoundEvent RECORD_LOFI;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            ResourceLocation ocean_location = new ResourceLocation("essentialfeatures", "ocean_music");
            ResourceLocation scarlet_location = new ResourceLocation("essentialfeatures", "record_scarlet");
            ResourceLocation lofi_location = new ResourceLocation("essentialfeatures", "record_lofi");
            event.getRegistry().registerAll(
                    new SoundEvent(ocean_location).setRegistryName(ocean_location),
                    new SoundEvent(scarlet_location).setRegistryName(scarlet_location),
                    new SoundEvent(lofi_location).setRegistryName(lofi_location)
            );
        }
    }
}
