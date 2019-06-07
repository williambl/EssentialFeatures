package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.EssentialFeatures;
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
import net.minecraftforge.registries.ObjectHolder;

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

    @ObjectHolder("nettles")
    public static BlockNettles NETTLES;

    @ObjectHolder("nether_brick_door")
    public static BlockEFDoor NETHER_BRICK_DOOR;
    @ObjectHolder("purpur_door")
    public static BlockEFDoor PURPUR_DOOR;

    @ObjectHolder("redstone_rod")
    public static BlockRedstoneRod REDSTONE_ROD;

    public static BlockStainedRedstoneTorch[] STAINED_REDSTONE_TORCHES;
    public static BlockStainedLamp[] STAINED_LAMPS;

    public static void addBlocks() {
        STAINED_LAMPS = new BlockStainedLamp[16];
        STAINED_REDSTONE_TORCHES = new BlockStainedRedstoneTorch[16];
        for (int i = 0; i < 16; i++) {
            STAINED_REDSTONE_TORCHES[i] = new BlockStainedRedstoneTorch(BlockStainedRedstoneTorch.names[i]+"_stained_redstone_torch", i);
            STAINED_LAMPS[i] = new BlockStainedLamp(BlockStainedRedstoneTorch.names[i]+"_stained_redstone_torch", i);
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
                    new BlockViewedBlock("viewed_block", Material.ROCK, 5, 5),
                    new EFBlock("smooth_glowstone", Material.GLASS, SoundType.GLASS, 0.5f, 2, 1),
                    new EFBlock("polished_glowstone", Material.GLASS, SoundType.GLASS, 1, 2, 1),
                    new EFBlock("snow_brick", Material.CRAFTED_SNOW, SoundType.SNOW, 0.5f, 1),
                    new BlockBlockBreaker("block_breaker", Material.PISTON, 3, 3),
                    new BlockCryingObsidian("crying_obsidian", Material.ROCK, 100, 100),
                    new BlockSpike("spike_block", Material.IRON, 1, 1),
                    new BlockBlockPlacer("block_placer", Material.ROCK, 5, 5),

                    new EFBlock("decorative_stone", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("decorative_andesite", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("decorative_diorite", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("decorative_granite", Material.ROCK, SoundType.STONE, 3, 3),

                    new EFBlock("cream_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("dirty_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("long_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("blue_bricks", Material.ROCK, SoundType.STONE, 3, 3),
                    new EFBlock("mixed_bricks", Material.ROCK, SoundType.STONE, 3, 3),

                    new BlockSlate("slate", Material.ROCK, 2, 3),
                    new BlockBlaze("blaze_block"),

                    new EFBlock("packed_sand", Material.SAND, SoundType.SAND, (float) 0.5, 1),
                    new EFBlock("packed_red_sand", Material.SAND, SoundType.SAND, (float) 0.5, 1),
                    new EFBlock("packed_gravel", Material.GROUND, SoundType.GROUND, (float) 0.8, 2),

                    new BlockNettles("stinging_nettles"),

                    new BlockEFDoor("nether_brick_door", Material.ROCK, 1f, 1011, 1005),
                    new BlockEFDoor("purpur_door", Material.ROCK, 1f, 1011, 1005),

                    new BlockRedstoneRod("redstone_rod", Material.CIRCUITS, SoundType.METAL, 0.5f, 0, 13)
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
                    new ItemBlock(CARVED_STONE, new Item.Properties()),
                    new ItemBlock(CARVED_ANDESITE, new Item.Properties()),
                    new ItemBlock(CARVED_DIORITE, new Item.Properties()),
                    new ItemBlock(CARVED_GRANITE, new Item.Properties()),
                    new ItemBlock(CREAM_BRICKS, new Item.Properties()),
                    new ItemBlock(DIRTY_BRICKS, new Item.Properties()),
                    new ItemBlock(LONG_BRICKS, new Item.Properties()),
                    new ItemBlock(BLUE_BRICKS, new Item.Properties()),
                    new ItemBlock(MIXED_BRICKS, new Item.Properties()),
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
