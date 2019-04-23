package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.common.config.ModConfig;
import com.williambl.essentialfeatures.common.item.ItemBlockDoor;
import com.williambl.essentialfeatures.common.item.ItemBlockWithSubtypes;
import com.williambl.essentialfeatures.common.item.ItemSlate;
import com.williambl.essentialfeatures.common.tileentity.TileEntityBlockPlacer;
import com.williambl.essentialfeatures.common.tileentity.TileEntityRedstoneRod;
import com.williambl.essentialfeatures.common.tileentity.TileEntityViewedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public class ModBlocks {

    public static BlockViewedBlock VIEWED_BLOCK;
    public static EFBlock SMOOTH_GLOWSTONE;
    public static EFBlock POLISHED_GLOWSTONE;
    public static BlockStainedLamp STAINED_LAMP;
    public static BlockStainedLamp LIT_STAINED_LAMP;
    public static EFBlock SNOW_BRICK;
    public static BlockBlockBreaker BLOCK_BREAKER;
    public static BlockCryingObsidian CRYING_OBSIDIAN;
    public static BlockSpike SPIKE_BLOCK;
    public static BlockBlockPlacer BLOCK_PLACER;
    public static BlockDecorativeStone DECORATIVE_STONE;
    public static BlockBrickVariant BRICK_VARIANT;

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

    public static void addBlocks() {
        VIEWED_BLOCK = new BlockViewedBlock("viewed_block", Material.ROCK, 5, 5);
        SMOOTH_GLOWSTONE = new EFBlock("smooth_glowstone", Material.GLASS, CreativeTabs.BUILDING_BLOCKS, SoundType.GLASS, 0.5f, 2, 1);
        STAINED_LAMP = new BlockStainedLamp("stained_lamp", Material.GLASS, 0.3F, 1.5F, false);
        LIT_STAINED_LAMP = new BlockStainedLamp("lit_stained_lamp", Material.GLASS, 0.3F, 1.5F, true);
        POLISHED_GLOWSTONE = new EFBlock("polished_glowstone", Material.GLASS, CreativeTabs.BUILDING_BLOCKS, SoundType.GLASS, 1, 2, 1);
        SNOW_BRICK = new EFBlock("snow_brick", Material.CRAFTED_SNOW, CreativeTabs.BUILDING_BLOCKS, SoundType.SNOW, 0.5f, 1);
        BLOCK_BREAKER = new BlockBlockBreaker("block_breaker", Material.PISTON, 3, 3);
        CRYING_OBSIDIAN = new BlockCryingObsidian("crying_obsidian", Material.ROCK, 100, 100);
        SPIKE_BLOCK = new BlockSpike("spike_block", Material.IRON, 1, 1);
        BLOCK_PLACER = new BlockBlockPlacer("block_placer", Material.ROCK, 5, 5);
        DECORATIVE_STONE = new BlockDecorativeStone("decorative_stone", Material.ROCK, 3, 3);
        BRICK_VARIANT = new BlockBrickVariant("brick_variant", Material.ROCK, 3, 3);

        SLATE = new BlockSlate("slate", Material.ROCK, 2, 3);
        BLAZE_BLOCK = new BlockBlaze("blaze_block");

        PACKED_SAND = new EFBlock("packed_sand", Material.SAND, CreativeTabs.BUILDING_BLOCKS, SoundType.SAND, (float) 0.5, 1);
        PACKED_RED_SAND = new EFBlock("packed_red_sand", Material.SAND, CreativeTabs.BUILDING_BLOCKS, SoundType.SAND, (float) 0.5, 1);
        PACKED_GRAVEL = new EFBlock("packed_gravel", Material.GROUND, CreativeTabs.BUILDING_BLOCKS, SoundType.GROUND, (float) 0.8, 2);

        NETTLES = new BlockNettles("stinging_nettles");

        NETHER_BRICK_DOOR = new BlockEFDoor("nether_brick_door", Material.ROCK, 1f, 1011, 1005);
        PURPUR_DOOR = new BlockEFDoor("purpur_door", Material.ROCK, 1f, 1011, 1005);

        REDSTONE_ROD = new BlockRedstoneRod("redstone_rod", Material.CIRCUITS, CreativeTabs.DECORATIONS, SoundType.METAL, 0.5f, 0, 0.95f);

        STAINED_REDSTONE_TORCHES = new BlockStainedRedstoneTorch[32];
        for (int i = 0; i < 16; i++) {
            STAINED_REDSTONE_TORCHES[i+16] = new BlockStainedRedstoneTorch(BlockStainedRedstoneTorch.names[i]+"_stained_redstone_torch", false, i);
            STAINED_REDSTONE_TORCHES[i] = new BlockStainedRedstoneTorch("lit_"+BlockStainedRedstoneTorch.names[i]+"_stained_redstone_torch", true, i);
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
            if (!ModConfig.blocks)
                return;
            final IForgeRegistry<Block> registry = event.getRegistry();

            event.getRegistry().registerAll(
                    VIEWED_BLOCK,
                    SMOOTH_GLOWSTONE,
                    STAINED_LAMP,
                    LIT_STAINED_LAMP,
                    POLISHED_GLOWSTONE,
                    SNOW_BRICK,
                    BLOCK_BREAKER,
                    CRYING_OBSIDIAN,
                    SPIKE_BLOCK,
                    BLOCK_PLACER,
                    DECORATIVE_STONE,
                    BRICK_VARIANT,
                    SMOOTH_DOUBLE_STONE_SLAB,
                    SMOOTH_DOUBLE_SANDSTONE_SLAB,
                    SMOOTH_DOUBLE_RED_SANDSTONE_SLAB,
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
            GameRegistry.registerTileEntity(TileEntityViewedBlock.class, VIEWED_BLOCK.getRegistryName().toString());
            GameRegistry.registerTileEntity(TileEntityBlockPlacer.class, BLOCK_PLACER.getRegistryName().toString());
            GameRegistry.registerTileEntity(TileEntityRedstoneRod.class, REDSTONE_ROD.getRegistryName().toString());
            GameRegistry.registerFuelHandler(BLAZE_BLOCK);

        }

        /**
         * Register this mod's {@link ItemBlock}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
            if (!ModConfig.blocks)
                return;

            final ItemBlock[] items = {
                    new ItemBlock(VIEWED_BLOCK),
                    new ItemBlock(SMOOTH_GLOWSTONE),
                    new ItemBlockWithSubtypes(STAINED_LAMP, true, BlockStainedLamp.names),
                    new ItemBlockWithSubtypes(LIT_STAINED_LAMP, true, BlockStainedLamp.names),
                    new ItemBlock(POLISHED_GLOWSTONE),
                    new ItemBlock(SNOW_BRICK),
                    new ItemBlock(BLOCK_BREAKER),
                    new ItemBlock(CRYING_OBSIDIAN),
                    new ItemBlock(SPIKE_BLOCK),
                    new ItemBlock(BLOCK_PLACER),
                    new ItemBlockWithSubtypes(DECORATIVE_STONE, true, BlockDecorativeStone.names),
                    new ItemBlockWithSubtypes(BRICK_VARIANT, true, BlockBrickVariant.names),
                    new ItemBlock(SMOOTH_DOUBLE_STONE_SLAB),
                    new ItemBlock(SMOOTH_DOUBLE_SANDSTONE_SLAB),
                    new ItemBlock(SMOOTH_DOUBLE_RED_SANDSTONE_SLAB),
                    new ItemSlate(SLATE),
                    new ItemBlock(BLAZE_BLOCK),
                    new ItemBlock(PACKED_SAND),
                    new ItemBlock(PACKED_RED_SAND),
                    new ItemBlock(PACKED_GRAVEL),
                    new ItemBlock(NETTLES),
                    new ItemBlockDoor(NETHER_BRICK_DOOR),
                    new ItemBlockDoor(PURPUR_DOOR),
                    new ItemBlock(REDSTONE_ROD)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : items) {
                registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
                ITEM_BLOCKS.add(item);
            }

            for (BlockStainedRedstoneTorch torch : STAINED_REDSTONE_TORCHES) {
                if (torch.isOn) {
                    final ItemBlock item = new ItemBlock(torch);
                    registry.register(item.setRegistryName(torch.getRegistryName()));
                    ITEM_BLOCKS.add(item);
                }
            }

        }

        /**
         * Register this mod's ItemBlock Models.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlockModels(ModelRegistryEvent event) {
            if (!ModConfig.blocks)
                return;

            VIEWED_BLOCK.initModel();
            SMOOTH_GLOWSTONE.initModel();
            STAINED_LAMP.initModel();
            LIT_STAINED_LAMP.initModel();
            POLISHED_GLOWSTONE.initModel();
            SNOW_BRICK.initModel();
            BLOCK_BREAKER.initModel();
            CRYING_OBSIDIAN.initModel();
            SPIKE_BLOCK.initModel();
            BLOCK_PLACER.initModel();
            DECORATIVE_STONE.initModel();
            BRICK_VARIANT.initModel();

            SMOOTH_DOUBLE_STONE_SLAB.initModel();
            SMOOTH_DOUBLE_SANDSTONE_SLAB.initModel();
            SMOOTH_DOUBLE_RED_SANDSTONE_SLAB.initModel();
            SLATE.initModel();
            BLAZE_BLOCK.initModel();
            PACKED_SAND.initModel();
            PACKED_RED_SAND.initModel();
            PACKED_GRAVEL.initModel();
            NETTLES.initModel();

            NETHER_BRICK_DOOR.initModel();
            PURPUR_DOOR.initModel();

            REDSTONE_ROD.initModel();

            for (BlockStainedRedstoneTorch torch : STAINED_REDSTONE_TORCHES) {
                torch.initModel();
            }
        }

        public static void registerTileEntities() {
            registerTileEntity(TileEntityViewedBlock.class);
            registerTileEntity(TileEntityBlockPlacer.class);
            registerTileEntity(TileEntityRedstoneRod.class);
        }

        private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
            GameRegistry.registerTileEntity(tileEntityClass, EssentialFeatures.MODID + ":" + tileEntityClass.getSimpleName().replaceFirst("TileEntity", ""));
        }

        @SideOnly(Side.CLIENT)
        public static void registerBlockColors() {
            BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
            blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic(), NETTLES);
        }

    }
}
