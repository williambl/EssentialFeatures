package com.williambl.essentialfeatures.common.networking;

import com.williambl.essentialfeatures.client.DistHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PortableJukeboxMessage {

    private boolean startOrStop;
    private UUID playerUUID;
    private ResourceLocation disc;

    public PortableJukeboxMessage(boolean startOrStopIn, UUID entityUUIDIn, ResourceLocation discIn) {
        this.startOrStop = startOrStopIn;
        this.playerUUID = entityUUIDIn;
        this.disc = discIn;
    }

    PortableJukeboxMessage(PacketBuffer buf) {
        this(buf.readBoolean(), buf.readUniqueId(), buf.readResourceLocation());
    }

    void encode(PacketBuffer buf) {
        buf.writeBoolean(startOrStop);
        buf.writeUniqueId(playerUUID);
        buf.writeResourceLocation(disc);
    }

    void handle(Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer())
            return;

        if (this.startOrStop) {
            ctx.get().enqueueWork(() -> DistHelper.playDiscToPlayer(this.playerUUID, this.disc));
        } else {
            ctx.get().enqueueWork(() -> DistHelper.stopDisc(this.disc));
        }
    }
}
