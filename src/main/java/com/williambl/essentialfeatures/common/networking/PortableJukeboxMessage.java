package com.williambl.essentialfeatures.common.networking;

import com.williambl.essentialfeatures.client.music.MovingSound;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;
import java.util.function.Supplier;

public class PortableJukeboxMessage {

    private boolean startOrStop;
    private UUID playerUUID;
    private ResourceLocation soundEvent;

    public PortableJukeboxMessage(boolean startOrStopIn, UUID entityUUIDIn, ResourceLocation soundEventIn) {
        this.startOrStop = startOrStopIn;
        this.playerUUID = entityUUIDIn;
        this.soundEvent = soundEventIn;
    }

    PortableJukeboxMessage(PacketBuffer buf) {
        this(buf.readBoolean(), buf.readUniqueId(), buf.readResourceLocation());
    }

    void encode(PacketBuffer buf) {
        buf.writeBoolean(startOrStop);
        buf.writeUniqueId(playerUUID);
        buf.writeResourceLocation(soundEvent);
    }

    void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer())
            return;

        if (this.startOrStop) {
            ctx.get().enqueueWork(() -> {
                System.out.println("recieved message!");
                Minecraft.getInstance().getSoundHandler().stop();
                Minecraft.getInstance().getSoundHandler().play(
                        new MovingSound(
                                Minecraft.getInstance().world.getPlayerByUuid(this.playerUUID),
                                ForgeRegistries.SOUND_EVENTS.getValue(this.soundEvent)
                        )
                );
            });
        } else {
            ctx.get().enqueueWork(() -> Minecraft.getInstance().getSoundHandler().stop(this.soundEvent, SoundCategory.NEUTRAL));
        }
    }
}
