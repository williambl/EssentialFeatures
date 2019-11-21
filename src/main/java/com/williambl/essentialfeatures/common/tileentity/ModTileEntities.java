package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.inventory.container.ContainerType;
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
    @ObjectHolder("lightning_forge")
    public static TileEntityType<LightningForgeTileEntity> LIGHTNING_FORGE;

    @ObjectHolder("lightning_forge")
    public static ContainerType<LightningForgeTileEntity.LightningForgeContainer> LIGHTNING_FORGE_CONTAINER;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> e) {
        e.getRegistry().registerAll(
                TileEntityType.Builder.create(ViewedBlockTileEntity::new, ModBlocks.VIEWED_BLOCK).build(null).setRegistryName("viewed_block"),
                TileEntityType.Builder.create(BlockPlacerTileEntity::new, ModBlocks.BLOCK_PLACER).build(null).setRegistryName("block_placer"),
                TileEntityType.Builder.create(TileEntityRedstoneRod::new, ModBlocks.REDSTONE_ROD).build(null).setRegistryName("redstone_rod"),
                TileEntityType.Builder.create(LightningForgeTileEntity::new, ModBlocks.LIGHTNING_FORGE).build(null).setRegistryName("lightning_forge")
        );
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> e) {
        e.getRegistry().registerAll(
                new ContainerType<>(LightningForgeTileEntity.LightningForgeContainer::new).setRegistryName("lightning_forge")
        );
    }
}
