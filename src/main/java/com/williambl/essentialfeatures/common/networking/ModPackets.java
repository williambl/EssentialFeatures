package com.williambl.essentialfeatures.common.networking;

import com.williambl.essentialfeatures.EssentialFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModPackets {
    private static String PROTOCOL_VERSION = "1";

    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(EssentialFeatures.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        int discriminator = 0;
        INSTANCE.registerMessage(
                discriminator++,
                PortableJukeboxMessage.class,
                PortableJukeboxMessage::encode,
                PortableJukeboxMessage::new,
                PortableJukeboxMessage::handle
        );
    }
}
