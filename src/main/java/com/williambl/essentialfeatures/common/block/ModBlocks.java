package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.common.item.ItemBlockDoor;
import com.williambl.essentialfeatures.common.item.ItemSlate;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class ModBlocks {

    public static BlockViewedBlock VIEWED_BLOCK;
    public static EFBlock SMOOTH_GLOWSTONE;
    public static EFBlock POLISHED_GLOWSTONE;
    public static EFBlock SNOW_BRICK;
    public static BlockBlockBreaker BLOCK_BREAKER;
    public static BlockCryingObsidian CRYING_OBSIDIAN;
    public static BlockSpike SPIKE_BLOCK;
    public static BlockBlockPlacer BLOCK_PLACER;

    public static EFBlock DECORATIVE_STONE;
    public static EFBlock DECORATIVE_ANDESITE;
    public static EFBlock DECORATIVE_DIORITE;
    public static EFBlock DECORATIVE_GRANITE;

    public static EFBlock CREAM_BRICKS;
    public static EFBlock DIRTY_BRICKS;
    public static EFBlock LONG_BRICKS;
    public static EFBlock BLUE_BRICKS;
    public static EFBlock MIXED_BRICKS;

    public static BlockSlate SLATE;
    public static BlockBlaze BLAZE_BLOCK;

    public static EFBlock PACKED_SAND;
    public static EFBlock PACKED_RED_SAND;
    public static EFBlock PACKED_GRAVEL;

    public static BlockNettles NETTLES;

    public static BlockEFDoor NETHER_BRICK_DOOR;
    public static BlockEFDoor PURPUR_DOOR;

    public static BlockRedstoneRod REDSTONE_ROD;

    public static BlockStainedRedstoneTorch[] STAINED_REDSTONE_TORCHES;
    public static BlockStainedLamp[] STAINED_LAMPS;

    public static void addBlocks() {
        VIEWED_BLOCK = new BlockViewedBlock("viewed_block", Material.ROCK, 5, 5);
        SMOOTH_GLOWSTONE = new EFBlock("smooth_glowstone", Material.GLASS, SoundType.GLASS, 0.5f, 2, 1);
        POLISHED_GLOWSTONE = new EFBlock("polished_glowstone", Material.GLASS, SoundType.GLASS, 1, 2, 1);
        SNOW_BRICK = new EFBlock("snow_brick", Material.CRAFTED_SNOW, SoundType.SNOW, 0.5f, 1);
        BLOCK_BREAKER = new BlockBlockBreaker("block_breaker", Material.PISTON, 3, 3);
        CRYING_OBSIDIAN = new BlockCryingObsidian("crying_obsidian", Material.ROCK, 100, 100);
        SPIKE_BLOCK = new BlockSpike("spike_block", Material.IRON, 1, 1);
        BLOCK_PLACER = new BlockBlockPlacer("block_placer", Material.ROCK, 5, 5);

        DECORATIVE_STONE = new EFBlock("decorative_stone", Material.ROCK, SoundType.STONE, 3, 3);
        DECORATIVE_ANDESITE = new EFBlock("decorative_andesite", Material.ROCK, SoundType.STONE, 3, 3);
        DECORATIVE_DIORITE = new EFBlock("decorative_diorite", Material.ROCK, SoundType.STONE, 3, 3);
        DECORATIVE_GRANITE = new EFBlock("decorative_granite", Material.ROCK, SoundType.STONE, 3, 3);

        CREAM_BRICKS = new EFBlock("cream_bricks", Material.ROCK, SoundType.STONE, 3, 3);
        DIRTY_BRICKS = new EFBlock("dirty_bricks", Material.ROCK, SoundType.STONE, 3, 3);
        LONG_BRICKS = new EFBlock("long_bricks", Material.ROCK, SoundType.STONE, 3, 3);
        BLUE_BRICKS = new EFBlock("blue_bricks", Material.ROCK, SoundType.STONE, 3, 3);
        MIXED_BRICKS = new EFBlock("mixed_bricks", Material.ROCK, SoundType.STONE, 3, 3);

        SLATE = new BlockSlate("slate", Material.ROCK, 2, 3);
        BLAZE_BLOCK = new BlockBlaze("blaze_block");

        PACKED_SAND = new EFBlock("packed_sand", Material.SAND, SoundType.SAND, (float) 0.5, 1);
        PACKED_RED_SAND = new EFBlock("packed_red_sand", Material.SAND, SoundType.SAND, (float) 0.5, 1);
        PACKED_GRAVEL = new EFBlock("packed_gravel", Material.GROUND, SoundType.GROUND, (float) 0.8, 2);

        NETTLES = new BlockNettles("stinging_nettles");

        NETHER_BRICK_DOOR = new BlockEFDoor("nether_brick_door", Material.ROCK, 1f, 1011, 1005);
        PURPUR_DOOR = new BlockEFDoor("purpur_door", Material.ROCK, 1f, 1011, 1005);

        REDSTONE_ROD = new BlockRedstoneRod("redstone_rod", Material.CIRCUITS, SoundType.METAL, 0.5f, 0, 13);

        STAINED_LAMPS = new BlockStainedLamp[16];
        STAINED_REDSTONE_TORCHES = new BlockStainedRedstoneTorch[16];
        for (int i = 0; i < 16; i++) {
            STAINED_REDSTONE_TORCHES[i] = new BlockStainedRedstoneTorch(BlockStainedRedstoneTorch.names[i]+"_stained_redstone_torch", i);
            STAINED_LAMPS[i] = new BlockStainedLamp(BlockStainedRedstoneTorch.names[i]+"_stained_redstone_torch", i);
        }
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

        /**
         * Register this mod's {@link Block}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            event.getRegistry().registerAll(
                    VIEWED_BLOCK,
                    SMOOTH_GLOWSTONE,
                    POLISHED_GLOWSTONE,
                    SNOW_BRICK,
                    BLOCK_BREAKER,
                    CRYING_OBSIDIAN,
                    SPIKE_BLOCK,
                    BLOCK_PLACER,
                    DECORATIVE_STONE,
                    SLATE,
                    BLAZE_BLOCK,
                    PACKED_SAND,
                    PACKED_RED_SAND,
                    PACKED_GRAVEL,
                    NETTLES,
                    NETHER_BRICK_DOOR,
                    PURPUR_DOOR,
                    REDSTONE_ROD
            );
            event.getRegistry().registerAll(STAINED_REDSTONE_TORCHES);
            event.getRegistry().registerAll(STAINED_LAMPS);

        }

        /**
         * Register this mod's {@link ItemBlock}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(VIEWED_BLOCK, new Item.Properties()),
                    new ItemBlock(SMOOTH_GLOWSTONE, new Item.Properties()),
                    new ItemBlock(POLISHED_GLOWSTONE, new Item.Properties()),
                    new ItemBlock(SNOW_BRICK, new Item.Properties()),
                    new ItemBlock(BLOCK_BREAKER, new Item.Properties()),
                    new ItemBlock(CRYING_OBSIDIAN, new Item.Properties()),
                    new ItemBlock(SPIKE_BLOCK, new Item.Properties()),
                    new ItemBlock(BLOCK_PLACER, new Item.Properties()),
                    new ItemSlate(SLATE),
                    new ItemBlock(BLAZE_BLOCK, new Item.Properties()),
                    new ItemBlock(PACKED_SAND, new Item.Properties()),
                    new ItemBlock(PACKED_RED_SAND, new Item.Properties()),
                    new ItemBlock(PACKED_GRAVEL, new Item.Properties()),
                    new ItemBlock(NETTLES, new Item.Properties()),
                    new ItemBlockDoor(NETHER_BRICK_DOOR),
                    new ItemBlockDoor(PURPUR_DOOR),
                    new ItemBlock(REDSTONE_ROD, new Item.Properties())
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : items) {
                registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
                ITEM_BLOCKS.add(item);
            }

            for (BlockStainedRedstoneTorch torch : STAINED_REDSTONE_TORCHES) {
                    final ItemBlock item = new ItemBlock(torch, new Item.Properties());
                    registry.register(item.setRegistryName(torch.getRegistryName()));
                    ITEM_BLOCKS.add(item);
            }

            for (BlockStainedLamp lamp : STAINED_LAMPS) {
                    final ItemBlock item = new ItemBlock(lamp, new Item.Properties());
                    registry.register(item.setRegistryName(lamp.getRegistryName()));
                    ITEM_BLOCKS.add(item);
            }

        }

        @OnlyIn(Dist.CLIENT)
        public static void registerBlockColors() {
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            blockColors.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColors.getFoliageColor(worldIn, pos) : FoliageColors.getDefault(), NETTLES);
        }

    }
}
