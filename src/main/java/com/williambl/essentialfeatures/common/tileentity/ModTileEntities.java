package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EssentialFeatures.MODID)
public class ModTileEntities {

    @ObjectHolder("viewed_block")
    public static TileEntityType<ViewedBlockTileEntity> VIEWED_BLOCK;
    @ObjectHolder("block_placer")
    public static TileEntityType<BlockPlacerTileEntity> BLOCK_PLACER;
    @ObjectHolder("redstone_rod")
    public static TileEntityType<TileEntityRedstoneRod> REDSTONE_ROD;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> e) {
        e.getRegistry().registerAll(
                TileEntityType.Builder.create(ViewedBlockTileEntity::new, ModBlocks.VIEWED_BLOCK).build(null).setRegistryName("viewed_block"),
                TileEntityType.Builder.create(BlockPlacerTileEntity::new, ModBlocks.BLOCK_PLACER).build(null).setRegistryName("block_placer"),
                TileEntityType.Builder.create(TileEntityRedstoneRod::new, ModBlocks.REDSTONE_ROD).build(null).setRegistryName("redstone_rod")
        );

    }
}
