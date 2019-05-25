package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.EssentialFeatures;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModTileEntities {

    public static TileEntityType<TileEntityViewedBlock> VIEWED_BLOCK;
    public static TileEntityType<TileEntityBlockPlacer> BLOCK_PLACER;
    public static TileEntityType<TileEntityRedstoneRod> REDSTONE_ROD;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent<TileEntityType<?>> e) {
        VIEWED_BLOCK = TileEntityType.register(EssentialFeatures.MODID + ":viewed_block", TileEntityType.Builder.create(TileEntityViewedBlock::new));
        BLOCK_PLACER = TileEntityType.register(EssentialFeatures.MODID + ":block_placer", TileEntityType.Builder.create(TileEntityBlockPlacer::new));
        REDSTONE_ROD = TileEntityType.register(EssentialFeatures.MODID + ":redstone_rod", TileEntityType.Builder.create(TileEntityRedstoneRod::new));
    }
}
