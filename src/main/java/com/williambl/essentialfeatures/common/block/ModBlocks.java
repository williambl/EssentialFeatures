package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.EssentialFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.*;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

@ObjectHolder(EssentialFeatures.MODID)
public class ModBlocks {

    @ObjectHolder("viewed_block")
    public static BlockViewedBlock VIEWED_BLOCK;
    @ObjectHolder("smooth_glowstone")
    public static EFBlock SMOOTH_GLOWSTONE;
    @ObjectHolder("polished_glowstone")
    public static EFBlock POLISHED_GLOWSTONE;
    @ObjectHolder("snow_brick")
    public static EFBlock SNOW_BRICK;
    @ObjectHolder("block_breaker")
    public static BlockBlockBreaker BLOCK_BREAKER;
    @ObjectHolder("crying_obsidian")
    public static BlockCryingObsidian CRYING_OBSIDIAN;
    @ObjectHolder("spike_block")
    public static BlockSpike SPIKE_BLOCK;
    @ObjectHolder("block_placer")
    public static BlockBlockPlacer BLOCK_PLACER;

    @ObjectHolder("carved_stone")
    public static EFBlock CARVED_STONE;
    @ObjectHolder("carved_andesite")
    public static EFBlock CARVED_ANDESITE;
    @ObjectHolder("carved_diorite")
    public static EFBlock CARVED_DIORITE;
    @ObjectHolder("carved_granite")
    public static EFBlock CARVED_GRANITE;

    @ObjectHolder("cream_bricks")
    public static EFBlock CREAM_BRICKS;
    @ObjectHolder("dirty_bricks")
    public static EFBlock DIRTY_BRICKS;
    @ObjectHolder("long_bricks")
    public static EFBlock LONG_BRICKS;
    @ObjectHolder("blue_bricks")
    public static EFBlock BLUE_BRICKS;
    @ObjectHolder("mixed_bricks")
    public static EFBlock MIXED_BRICKS;

    @ObjectHolder("slate")
    public static BlockSlate SLATE;
    @ObjectHolder("blaze_block")
    public static BlockBlaze BLAZE_BLOCK;

    @ObjectHolder("packed_sand")
    public static EFBlock PACKED_SAND;
    @ObjectHolder("packed_red_sand")
    public static EFBlock PACKED_RED_SAND;
    @ObjectHolder("packed_gravel")
    public static EFBlock PACKED_GRAVEL;

    @ObjectHolder("stinging_nettles")
    public static BlockNettles NETTLES;

    @ObjectHolder("nether_brick_door")
    public static BlockEFDoor NETHER_BRICK_DOOR;
    @ObjectHolder("purpur_door")
    public static BlockEFDoor PURPUR_DOOR;

    @ObjectHolder("redstone_rod")
    public static BlockRedstoneRod REDSTONE_ROD;

    public static Pair[] STAINED_REDSTONE_TORCHES = new Pair[16];
    public static BlockStainedLamp[] STAINED_LAMPS = new BlockStainedLamp[16];

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {

        public static final Set<BlockItem> ITEM_BLOCKS = new HashSet<>();

        /**
         * Register this mod's {@link Block}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            for (int i = 0; i < 16; i++) {
                STAINED_REDSTONE_TORCHES[i] = Pair.of(
                        new BlockStainedRedstoneTorch(BlockStainedRedstoneTorch.names[i] + "_stained_redstone_torch", i),
                        new BlockStainedRedstoneWallTorch(BlockStainedRedstoneWallTorch.names[i] + "_stained_redstone_wall_torch", i)
                );
                STAINED_LAMPS[i] = new BlockStainedLamp(BlockStainedRedstoneTorch.names[i]+"_stained_redstone_lamp", i);
            }

            event.getRegistry().registerAll(
                    new BlockViewedBlock("viewed_block", Material.ROCK, 5, 5),
                    new EFBlock("smooth_glowstone", Material.GLASS, SoundType.GLASS, 0.5f, 2, 15),
                    new EFBlock("polished_glowstone", Material.GLASS, SoundType.GLASS, 1, 2, 15),
                    new EFBlock("snow_brick", Material.SNOW_BLOCK, SoundType.SNOW, 0.5f, 1),
                    new BlockBlockBreaker("block_breaker", Material.PISTON, 3, 3),
                    new BlockCryingObsidian("crying_obsidian", Material.ROCK, 100, 100),
                    new BlockSpike("spike_block", Material.IRON, 1, 1),
                    new BlockBlockPlacer("block_placer", Material.ROCK, 5, 5),

                    new EFBlock("carved_stone", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("carved_andesite", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("carved_diorite", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("carved_granite", Material.ROCK, SoundType.STONE, 3, 3),

                    new EFBlock("cream_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("dirty_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("long_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("blue_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("mixed_bricks", Material.ROCK, SoundType.STONE, 3, 3),

                    new BlockSlate("slate", Material.ROCK, 2, 3),
                    new BlockBlaze("blaze_block"),

                    new EFBlock("packed_sand", Material.SAND, SoundType.SAND, (float) 0.5, 1),
                    new EFBlock("packed_red_sand", Material.SAND, SoundType.SAND, (float) 0.5, 1),
                    new EFBlock("packed_gravel", Material.SAND, SoundType.GROUND, (float) 0.8, 2),

                    new BlockNettles("stinging_nettles"),

                    new BlockEFDoor("nether_brick_door", Material.ROCK, 1f, 1011, 1005),
                    new BlockEFDoor("purpur_door", Material.ROCK, 1f, 1011, 1005),

                    new BlockRedstoneRod("redstone_rod", Material.MISCELLANEOUS, SoundType.METAL, 0.5f, 0, 13)
            );

            for (Pair torchPair :
                    STAINED_REDSTONE_TORCHES) {
                event.getRegistry().register((Block) torchPair.getLeft());
                event.getRegistry().register((Block) torchPair.getRight());
            }
            event.getRegistry().registerAll(STAINED_LAMPS);

        }

        /**
         * Register this mod's {@link BlockItem}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
            final BlockItem[] items = {
                    new BlockItem(VIEWED_BLOCK, new Item.Properties().group(ItemGroup.REDSTONE)),
                    new BlockItem(SMOOTH_GLOWSTONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(POLISHED_GLOWSTONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(SNOW_BRICK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(BLOCK_BREAKER, new Item.Properties().group(ItemGroup.REDSTONE)),
                    new BlockItem(CRYING_OBSIDIAN, new Item.Properties().group(ItemGroup.DECORATIONS)),
                    new BlockItem(SPIKE_BLOCK, new Item.Properties().group(ItemGroup.DECORATIONS)),
                    new BlockItem(BLOCK_PLACER, new Item.Properties().group(ItemGroup.REDSTONE)),
                    new BlockItem(CARVED_STONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(CARVED_ANDESITE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(CARVED_DIORITE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(CARVED_GRANITE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(CREAM_BRICKS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(DIRTY_BRICKS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(LONG_BRICKS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(BLUE_BRICKS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(MIXED_BRICKS, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(SLATE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(BLAZE_BLOCK, new Item.Properties().group(ItemGroup.DECORATIONS)) {
                        @Override public int getBurnTime(ItemStack itemStack) { return 4800; }
                    },
                    new BlockItem(PACKED_SAND, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(PACKED_RED_SAND, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(PACKED_GRAVEL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),
                    new BlockItem(NETTLES, new Item.Properties().group(ItemGroup.DECORATIONS)),
                    new TallBlockItem(NETHER_BRICK_DOOR, new Item.Properties().group(ItemGroup.REDSTONE)),
                    new TallBlockItem(PURPUR_DOOR, new Item.Properties().group(ItemGroup.REDSTONE)),
                    new BlockItem(REDSTONE_ROD, new Item.Properties().group(ItemGroup.REDSTONE))
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final BlockItem item : items) {
                registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
                ITEM_BLOCKS.add(item);
            }

            for (Pair<BlockStainedRedstoneTorch, BlockStainedRedstoneWallTorch> torchPair : STAINED_REDSTONE_TORCHES) {
                final BlockItem item = new WallOrFloorItem(torchPair.getLeft(), torchPair.getRight(), new Item.Properties().group(ItemGroup.REDSTONE));
                registry.register(item.setRegistryName(torchPair.getLeft().getRegistryName()));
                ITEM_BLOCKS.add(item);
            }

            for (BlockStainedLamp lamp : STAINED_LAMPS) {
                final BlockItem item = new BlockItem(lamp, new Item.Properties().group(ItemGroup.REDSTONE));
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
