package com.williambl.essentialfeatures.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.common.block.*;
import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.RedstoneWallTorchBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(new LootTables(event.getGenerator()));
        event.getGenerator().addProvider(new BlockStates(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new ItemModels(event.getGenerator(), event.getExistingFileHelper()));
    }

    public abstract static class BaseLootTables extends LootTableProvider {

        private static final Logger LOGGER = LogManager.getLogger();
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        // Filled by subclasses
        protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

        private final DataGenerator generator;

        public BaseLootTables(DataGenerator dataGeneratorIn) {
            super(dataGeneratorIn);
            this.generator = dataGeneratorIn;
        }

        // Subclasses can override this to fill the 'lootTables' map.
        protected abstract void addTables();

        // Subclasses can call this if they want a standard loot table. Modify this for your own needs
        protected LootTable.Builder createStandardTable(String name, Block block) {
            LootPool.Builder builder = LootPool.builder()
                    .name(name)
                    .rolls(ConstantRange.of(1))
                    .addEntry(ItemLootEntry.builder(block));
            return LootTable.builder().addLootPool(builder);
        }

        @Override
        // Entry point
        public void act(DirectoryCache cache) {
            addTables();

            Map<ResourceLocation, LootTable> tables = new HashMap<>();
            for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
                tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
            }
            writeTables(cache, tables);
        }

        // Actually write out the tables in the output folder
        private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
            Path outputFolder = this.generator.getOutputFolder();
            System.out.println(outputFolder);
            tables.forEach((key, lootTable) -> {
                Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
                try {
                    IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
                } catch (IOException e) {
                    LOGGER.log(Level.ERROR, "Couldn't write loot table {}", path, e);
                }
            });
        }

        @Override
        public String getName() {
            return "EssentialFeatures Loot Tables";
        }
    }

    public static class LootTables extends BaseLootTables {

        public LootTables(DataGenerator dataGeneratorIn) {
            super(dataGeneratorIn);
        }

        @Override
        protected void addTables() {
            lootTables.put(ModBlocks.VIEWED_BLOCK, createStandardTable("viewed_block", ModBlocks.VIEWED_BLOCK));
            lootTables.put(ModBlocks.SMOOTH_GLOWSTONE, createStandardTable("smooth_glowstone", ModBlocks.SMOOTH_GLOWSTONE));
            lootTables.put(ModBlocks.POLISHED_GLOWSTONE, createStandardTable("polished_glowstone", ModBlocks.POLISHED_GLOWSTONE));
            lootTables.put(ModBlocks.SNOW_BRICK, createStandardTable("snow_brick", ModBlocks.SNOW_BRICK));
            lootTables.put(ModBlocks.BLOCK_BREAKER, createStandardTable("block_breaker", ModBlocks.BLOCK_BREAKER));
            lootTables.put(ModBlocks.CRYING_OBSIDIAN, createStandardTable("crying_obsidian", ModBlocks.CRYING_OBSIDIAN));
            lootTables.put(ModBlocks.SPIKE_BLOCK, createStandardTable("spike_block", ModBlocks.SPIKE_BLOCK));
            lootTables.put(ModBlocks.BLOCK_PLACER, createStandardTable("block_placer", ModBlocks.BLOCK_PLACER));
            lootTables.put(ModBlocks.CARVED_STONE, createStandardTable("carved_stone", ModBlocks.CARVED_STONE));
            lootTables.put(ModBlocks.CARVED_ANDESITE, createStandardTable("carved_andesite", ModBlocks.CARVED_ANDESITE));
            lootTables.put(ModBlocks.CARVED_DIORITE, createStandardTable("carved_diorite", ModBlocks.CARVED_DIORITE));
            lootTables.put(ModBlocks.CARVED_GRANITE, createStandardTable("carved_granite", ModBlocks.CARVED_GRANITE));
            lootTables.put(ModBlocks.CREAM_BRICKS, createStandardTable("cream_bricks", ModBlocks.CREAM_BRICKS));
            lootTables.put(ModBlocks.DIRTY_BRICKS, createStandardTable("dirty_bricks", ModBlocks.DIRTY_BRICKS));
            lootTables.put(ModBlocks.LONG_BRICKS, createStandardTable("long_bricks", ModBlocks.LONG_BRICKS));
            lootTables.put(ModBlocks.BLUE_BRICKS, createStandardTable("blue_bricks", ModBlocks.BLUE_BRICKS));
            lootTables.put(ModBlocks.MIXED_BRICKS, createStandardTable("mixed_bricks", ModBlocks.MIXED_BRICKS));
            lootTables.put(ModBlocks.BLAZE_BLOCK, createStandardTable("blaze_block", ModBlocks.BLAZE_BLOCK));
            lootTables.put(ModBlocks.PACKED_SAND, createStandardTable("packed_sand", ModBlocks.PACKED_SAND));
            lootTables.put(ModBlocks.PACKED_RED_SAND, createStandardTable("packed_red_sand", ModBlocks.PACKED_RED_SAND));
            lootTables.put(ModBlocks.PACKED_GRAVEL, createStandardTable("packed_gravel", ModBlocks.PACKED_GRAVEL));
            lootTables.put(ModBlocks.REDSTONE_ROD, createStandardTable("redstone_rod", ModBlocks.REDSTONE_ROD));
            for (Pair<StainedRedstoneTorchBlock, StainedRedstoneWallTorchBlock> torchPair :
                    ModBlocks.STAINED_REDSTONE_TORCHES) {
                lootTables.put(torchPair.getLeft(), createStandardTable(torchPair.getLeft().getRegistryName().getPath(), torchPair.getLeft()));
                lootTables.put(torchPair.getRight(), createStandardTable(torchPair.getLeft().getRegistryName().getPath(), torchPair.getLeft()));
            }

            for (StainedLampBlock lamp : ModBlocks.STAINED_LAMPS) {
                lootTables.put(lamp, createStandardTable(lamp.getRegistryName().getPath(), lamp));
            }
        }
    }

    private static class ItemModels extends ItemModelProvider {
        public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
            super(gen, EssentialFeatures.MODID, helper);
        }

        @Override
        protected void registerModels() {
            makeItemModelFromBlock(ModBlocks.VIEWED_BLOCK);
            makeItemModelFromBlock(ModBlocks.SMOOTH_GLOWSTONE);
            makeItemModelFromBlock(ModBlocks.POLISHED_GLOWSTONE);
            makeItemModelFromBlock(ModBlocks.SNOW_BRICK);
            makeBlockBreakerItemModel(ModBlocks.BLOCK_BREAKER);
            makeItemModelFromBlock(ModBlocks.CRYING_OBSIDIAN);
            makeFlatItemModelFromBlock(ModBlocks.SPIKE_BLOCK);
            makeItemModelFromBlock(ModBlocks.BLOCK_PLACER);
            makeItemModelFromBlock(ModBlocks.CARVED_STONE);
            makeItemModelFromBlock(ModBlocks.CARVED_ANDESITE);
            makeItemModelFromBlock(ModBlocks.CARVED_DIORITE);
            makeItemModelFromBlock(ModBlocks.CARVED_GRANITE);
            makeItemModelFromBlock(ModBlocks.CREAM_BRICKS);
            makeItemModelFromBlock(ModBlocks.DIRTY_BRICKS);
            makeItemModelFromBlock(ModBlocks.LONG_BRICKS);
            makeItemModelFromBlock(ModBlocks.BLUE_BRICKS);
            makeItemModelFromBlock(ModBlocks.MIXED_BRICKS);
            makeSlateItemModel(ModBlocks.SLATE);
            makeItemModel(ModBlocks.NETHER_BRICK_DOOR.getRegistryName().getPath(), modLoc("item/door_nether_brick"));
            makeItemModel(ModBlocks.PURPUR_DOOR.getRegistryName().getPath(), modLoc("item/door_purpur"));
            makeItemModelFromBlock(ModBlocks.BLAZE_BLOCK);
            makeItemModelFromBlock(ModBlocks.PACKED_SAND);
            makeItemModelFromBlock(ModBlocks.PACKED_RED_SAND);
            makeItemModelFromBlock(ModBlocks.PACKED_GRAVEL);
            makeFlatItemModelFromBlock(ModBlocks.NETTLES);
            makeItemModelFromBlock(ModBlocks.REDSTONE_ROD);

            for (Pair<StainedRedstoneTorchBlock, StainedRedstoneWallTorchBlock> torchPair :
                    ModBlocks.STAINED_REDSTONE_TORCHES) {
                makeFlatItemModelFromTorchBlock(torchPair.getLeft());
            }
            for (StainedLampBlock lamp : ModBlocks.STAINED_LAMPS) {
                makeItemModelFromBlock(lamp);
            }

            makeItemModelFromItem(ModItems.CEREAL);
            makeItemModelFromItem(ModItems.COOKED_NETTLES);
            makeItemModelFromItem(ModItems.CREAM_BRICK);
            makeItemModelFromItem(ModItems.DIRTY_BRICK);
            makeItemModelFromItem(ModItems.DIRTY_CLAY);
            makeItemModelFromItem(ModItems.IRON_CEREAL);
            makeItemModelFromItem(ModItems.PORTABLE_JUKEBOX);
            makeItemModelFromItem(ModItems.PORTABLE_NOTE_BLOCK);
            makeItemModelFromItem(ModItems.RECORD_LOFI);
            makeItemModelFromItem(ModItems.RECORD_SCARLET);
            makeItemModelFromItem(ModItems.REDSTONE_ROD_ARROW);
            makeItemModelFromItem(ModItems.REDSTONE_ROD_SWORD);
            makeItemModelFromItem(ModItems.SAND_CLAY_MIXTURE);
            makeItemModelFromItem(ModItems.SHARPENED_ARROW);
        }

        private void makeItemModelFromBlock(Block block) {
            String path = block.getRegistryName().getPath();
            getBuilder(path)
                    .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
        }


        private void makeFlatItemModelFromTorchBlock(Block block) {
            String path = block.getRegistryName().getPath();
            makeItemModel(path, modLoc("block/lit_" + path));
        }

        private void makeFlatItemModelFromBlock(Block block) {
            String path = block.getRegistryName().getPath();
            makeItemModel(path, modLoc("block/" + path));
        }

        private void makeItemModelFromItem(Item item) {
            String path = item.getRegistryName().getPath();
            makeItemModel(path, modLoc("item/" + path));
        }

        private void makeItemModel(String path, ResourceLocation texture) {
            getBuilder(path)
                    .parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                    .texture("layer0", texture);
        }

        private void makeBlockBreakerItemModel(BlockBreakerBlock block) {
            String path = block.getRegistryName().getPath();
            getBuilder(path)
                    .parent(new ModelFile.UncheckedModelFile(mcLoc("block/cube_bottom_top")))
                    .texture("bottom", mcLoc("block/piston_bottom"))
                    .texture("side", modLoc("block/block_breaker_side"))
                    .texture("top", modLoc("block/block_breaker_inner"));
        }

        private void makeSlateItemModel(SlateBlock block) {
            String path = block.getRegistryName().getPath();

            getBuilder(path)
                    .parent(new ModelFile.UncheckedModelFile(modLoc("block/slate_height2")));
        }

        @Override
        public String getName() {
            return "Essential Features Item Models";
        }
    }

    public static class BlockStates extends BlockStateProvider {
        public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
            super(gen, EssentialFeatures.MODID, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {
            makeSimpleBlockState(ModBlocks.SMOOTH_GLOWSTONE, modLoc("block/smooth_glowstone"));
            makeSimpleBlockState(ModBlocks.POLISHED_GLOWSTONE, modLoc("block/polished_glowstone"));
            makeSimpleBlockState(ModBlocks.SNOW_BRICK, modLoc("block/snow_brick"));
            makeSimpleBlockState(ModBlocks.CARVED_STONE, modLoc("block/carved_stone"));
            makeSimpleBlockState(ModBlocks.CARVED_ANDESITE, modLoc("block/carved_andesite"));
            makeSimpleBlockState(ModBlocks.CARVED_DIORITE, modLoc("block/carved_diorite"));
            makeSimpleBlockState(ModBlocks.CARVED_GRANITE, modLoc("block/carved_granite"));
            makeSimpleBlockState(ModBlocks.CREAM_BRICKS, modLoc("block/cream_bricks"));
            makeSimpleBlockState(ModBlocks.DIRTY_BRICKS, modLoc("block/dirty_bricks"));
            makeSimpleBlockState(ModBlocks.LONG_BRICKS, modLoc("block/long_bricks"));
            makeSimpleBlockState(ModBlocks.BLUE_BRICKS, modLoc("block/blue_bricks"));
            makeSimpleBlockState(ModBlocks.MIXED_BRICKS, modLoc("block/mixed_bricks"));
            makeSimpleBlockState(ModBlocks.BLAZE_BLOCK, modLoc("block/blaze_block"));
            makeSimpleBlockState(ModBlocks.PACKED_SAND, modLoc("block/packed_sand"));
            makeSimpleBlockState(ModBlocks.PACKED_RED_SAND, modLoc("block/packed_red_sand"));
            makeSimpleBlockState(ModBlocks.PACKED_GRAVEL, modLoc("block/packed_gravel"));

            for (Pair<StainedRedstoneTorchBlock, StainedRedstoneWallTorchBlock> torchPair :
                    ModBlocks.STAINED_REDSTONE_TORCHES) {
                makeStandingTorchBlockState(torchPair.getLeft(), modLoc("block/lit_" + torchPair.getLeft().getRegistryName().getPath()), modLoc("block/" + torchPair.getLeft().getRegistryName().getPath()));
                makeWallTorchBlockState(torchPair.getRight(), modLoc("block/lit_" + torchPair.getLeft().getRegistryName().getPath()), modLoc("block/" + torchPair.getLeft().getRegistryName().getPath()));
            }

            for (StainedLampBlock lamp : ModBlocks.STAINED_LAMPS) {
                makeLampBlockState(lamp, modLoc("block/lit_" + lamp.getRegistryName().getPath()), modLoc("block/" + lamp.getRegistryName().getPath()));
            }

            makeBlockBreakerBlockState(ModBlocks.BLOCK_BREAKER, modLoc("block/block_breaker_inner"), modLoc("block/block_breaker_side"), mcLoc("block/piston_bottom"));
            makeBlockPlacerBlockState(ModBlocks.BLOCK_PLACER, modLoc("block/block_placer"), modLoc("block/block_placer_vertical"), mcLoc("block/furnace_side"), mcLoc("block/furnace_top"));
            makeDoorBlockState(ModBlocks.NETHER_BRICK_DOOR, modLoc("block/door_nether_brick_upper"), modLoc("block/door_nether_brick_lower"));
            makeDoorBlockState(ModBlocks.PURPUR_DOOR, modLoc("block/door_purpur_upper"), modLoc("block/door_purpur_lower"));
            makeRedstoneRodBlockState(ModBlocks.REDSTONE_ROD, modLoc("block/redstone_rod"));
            makeSlateBlockState(ModBlocks.SLATE, modLoc("block/slate_side"), modLoc("block/slate_top"));
            makeCrossModel(ModBlocks.SPIKE_BLOCK, modLoc("block/spike_block"));
            makeTintedCrossModel(ModBlocks.NETTLES, modLoc("block/stinging_nettles"));
            makeViewedBlockModel(ModBlocks.VIEWED_BLOCK, modLoc("block/powered_viewed_block"), modLoc("block/viewed_block"));
        }

        private void makeSimpleBlockState(Block block, ResourceLocation texture) {
            ModelFile model = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cube_all")))
                    .texture("all", texture);
            getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
        }

        private void makeLampBlockState(StainedLampBlock block, ResourceLocation onTexture, ResourceLocation offTexture) {
            ModelFile modelOn = models().getBuilder(block.getRegistryName().getPath() + "_powered")
                    .parent(models().getExistingFile(mcLoc("block/cube_all")))
                    .texture("all", onTexture);
            ModelFile modelOff = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cube_all")))
                    .texture("all", offTexture);
            getVariantBuilder(block).forAllStates(state -> state.get(StainedLampBlock.LIT) ? ConfiguredModel.builder().modelFile(modelOn).build() : ConfiguredModel.builder().modelFile(modelOff).build());
        }

        private void makeStandingTorchBlockState(StainedRedstoneTorchBlock block, ResourceLocation onTexture, ResourceLocation offTexture) {
            ModelFile modelStandingOn = models().getBuilder(block.getRegistryName().getPath() + "_powered")
                    .parent(models().getExistingFile(mcLoc("block/template_torch")))
                    .texture("torch", onTexture);
            ModelFile modelStandingOff = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/template_torch")))
                    .texture("torch", offTexture);

            getVariantBuilder(block).forAllStates(state -> state.get(RedstoneTorchBlock.LIT) ? ConfiguredModel.builder().modelFile(modelStandingOn).build() : ConfiguredModel.builder().modelFile(modelStandingOff).build());
        }

        private void makeWallTorchBlockState(StainedRedstoneWallTorchBlock block, ResourceLocation onTexture, ResourceLocation offTexture) {
            ModelFile modelWallOn = models().getBuilder(block.getRegistryName().getPath() + "powered")
                    .parent(models().getExistingFile(mcLoc("block/torch_wall")))
                    .texture("torch", onTexture);
            ModelFile modelWallOff = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/torch_wall")))
                    .texture("torch", offTexture);

            getVariantBuilder(block).forAllStates(state -> {
                ConfiguredModel.Builder builder = ConfiguredModel.builder();

                if (state.get(RedstoneWallTorchBlock.REDSTONE_TORCH_LIT))
                    builder.modelFile(modelWallOn);
                else
                    builder.modelFile(modelWallOff);

                switch (state.get(StainedRedstoneWallTorchBlock.FACING)) {
                    case NORTH:
                        builder.rotationY(270);
                        break;
                    case WEST:
                        builder.rotationY(180);
                        break;
                    case SOUTH:
                        builder.rotationY(90);
                        break;
                }

                return builder.build();
            });
        }

        private void makeBlockBreakerBlockState(BlockBreakerBlock block, ResourceLocation platformTexture, ResourceLocation sideTexture, ResourceLocation bottomTexture) {
            ModelFile model = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/template_piston")))
                    .texture("platform", platformTexture)
                    .texture("side", sideTexture)
                    .texture("bottom", bottomTexture);

            getVariantBuilder(block).forAllStates(state -> {
                ConfiguredModel.Builder builder = ConfiguredModel.builder().modelFile(model);

                switch (state.get(BlockBreakerBlock.FACING)) {
                    case WEST:
                        builder.rotationY(270);
                        break;
                    case SOUTH:
                        builder.rotationY(180);
                        break;
                    case EAST:
                        builder.rotationY(90);
                        break;
                    case UP:
                        builder.rotationX(90);
                        break;
                    case DOWN:
                        builder.rotationX(270);
                        break;
                }

                return builder.build();
            });
        }

        private void makeBlockPlacerBlockState(BlockPlacerBlock block, ResourceLocation frontTexture, ResourceLocation frontVerticalTexture, ResourceLocation sideTexture, ResourceLocation topTexture) {
            ModelFile horizontalModel = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/orientable")))
                    .texture("front", frontTexture)
                    .texture("side", sideTexture)
                    .texture("top", topTexture);
            ModelFile verticalModel = models().getBuilder(block.getRegistryName().getPath() + "_vertical")
                    .parent(models().getExistingFile(mcLoc("block/orientable_vertical")))
                    .texture("front", frontVerticalTexture)
                    .texture("side", topTexture);

            getVariantBuilder(block).forAllStates(state -> {
                ConfiguredModel.Builder builder = ConfiguredModel.builder();

                switch (state.get(BlockBreakerBlock.FACING)) {
                    case WEST:
                        builder.rotationY(270);
                        builder.modelFile(horizontalModel);
                        break;
                    case SOUTH:
                        builder.rotationY(180);
                        builder.modelFile(horizontalModel);
                        break;
                    case EAST:
                        builder.rotationY(90);
                        builder.modelFile(horizontalModel);
                        break;
                    case UP:
                        builder.modelFile(verticalModel);
                        break;
                    case DOWN:
                        builder.modelFile(verticalModel);
                        builder.rotationX(180);
                        break;
                    default:
                        builder.modelFile(horizontalModel);
                        break;
                }

                return builder.build();
            });
        }

        private void makeDoorBlockState(EFDoorBlock block, ResourceLocation topTexture, ResourceLocation bottomTexture) {
            ModelFile bottomModel = models().getBuilder(block.getRegistryName().getPath() + "_bottom")
                    .parent(models().getExistingFile(mcLoc("block/door_bottom")))
                    .texture("top", topTexture)
                    .texture("bottom", bottomTexture);
            ModelFile bottomHingeModel = models().getBuilder(block.getRegistryName().getPath() + "_bottom_rh")
                    .parent(models().getExistingFile(mcLoc("block/door_bottom_rh")))
                    .texture("top", topTexture)
                    .texture("bottom", bottomTexture);
            ModelFile topModel = models().getBuilder(block.getRegistryName().getPath() + "_top")
                    .parent(models().getExistingFile(mcLoc("block/door_top")))
                    .texture("top", topTexture)
                    .texture("bottom", bottomTexture);
            ModelFile topHingeModel = models().getBuilder(block.getRegistryName().getPath() + "_top_rh")
                    .parent(models().getExistingFile(mcLoc("block/door_top_rh")))
                    .texture("top", topTexture)
                    .texture("bottom", bottomTexture);

            getVariantBuilder(block).forAllStates(state -> {
                ConfiguredModel.Builder builder = ConfiguredModel.builder();

                if (state.get(EFDoorBlock.HINGE) == DoorHingeSide.LEFT) {
                    if ((state.get(EFDoorBlock.HALF) == DoubleBlockHalf.LOWER))
                        builder.modelFile(state.get(EFDoorBlock.OPEN) ? bottomHingeModel : bottomModel);
                    else
                        builder.modelFile(state.get(EFDoorBlock.OPEN) ? topHingeModel : topModel);

                    switch (state.get(EFDoorBlock.FACING)) {
                        case EAST:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 90 : 0);
                            break;
                        case SOUTH:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 180 : 90);
                            break;
                        case WEST:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 270 : 180);
                            break;
                        case NORTH:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 0 : 270);
                            break;
                    }
                } else {
                    if (state.get(EFDoorBlock.HALF) == DoubleBlockHalf.LOWER)
                        builder.modelFile(state.get(EFDoorBlock.OPEN) ? bottomModel : bottomHingeModel);
                    else
                        builder.modelFile(state.get(EFDoorBlock.OPEN) ? topModel : topHingeModel);

                    switch (state.get(EFDoorBlock.FACING)) {
                        case EAST:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 270 : 0);
                            break;
                        case SOUTH:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 0 : 90);
                            break;
                        case WEST:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 90 : 180);
                            break;
                        case NORTH:
                            builder.rotationY(state.get(EFDoorBlock.OPEN) ? 180 : 270);
                            break;
                    }
                }

                return builder.build();
            });
        }

        private void makeRedstoneRodBlockState(RedstoneRodBlock block, ResourceLocation texture) {
            ModelFile model = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/end_rod")))
                    .texture("end_rod", texture)
                    .texture("particle", texture);

            getVariantBuilder(block).forAllStates(state -> {
                ConfiguredModel.Builder builder = ConfiguredModel.builder();
                builder.modelFile(model);

                switch (state.get(RedstoneRodBlock.FACING)) {
                    case DOWN:
                        builder.rotationX(180);
                        break;
                    case EAST:
                        builder.rotationX(90);
                        builder.rotationY(90);
                        break;
                    case SOUTH:
                        builder.rotationX(90);
                        builder.rotationY(180);
                        break;
                    case WEST:
                        builder.rotationX(90);
                        builder.rotationY(270);
                        break;
                    case NORTH:
                        builder.rotationX(90);
                        break;
                }

                return builder.build();
            });
        }

        private void makeSlateBlockState(SlateBlock block, ResourceLocation sideTexture, ResourceLocation topTexture) {
            //Other models are manually made in models/block folder. Don't delete them.
            ModelFile fullModel = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cube_column")))
                    .texture("side", sideTexture)
                    .texture("end", topTexture);

            getVariantBuilder(block).forAllStates(state -> {
                ConfiguredModel.Builder builder = ConfiguredModel.builder();
                switch (state.get(SlateBlock.LAYERS)) {
                    case 1:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height2")));
                        break;
                    case 2:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height4")));
                        break;
                    case 3:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height6")));
                        break;
                    case 4:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height8")));
                        break;
                    case 5:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height10")));
                        break;
                    case 6:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height12")));
                        break;
                    case 7:
                        builder.modelFile(models().getExistingFile(modLoc("block/slate_height14")));
                        break;
                    case 8:
                        builder.modelFile(fullModel);
                        break;
                }

                return builder.build();
            });
        }

        private void makeCrossModel(Block block, ResourceLocation texture) {
            ModelFile model = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cross")))
                    .texture("cross", texture);

            getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
        }

        private void makeTintedCrossModel(Block block, ResourceLocation texture) {
            ModelFile model = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/tinted_cross")))
                    .texture("cross", texture);

            getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
        }

        private void makeViewedBlockModel(ViewedBlockBlock block, ResourceLocation onTexture, ResourceLocation offTexture) {
            ModelFile modelOn = models().getBuilder(block.getRegistryName().getPath() + "_powered")
                    .parent(models().getExistingFile(mcLoc("block/cube_all")))
                    .texture("all", onTexture);
            ModelFile modelOff = models().getBuilder(block.getRegistryName().getPath())
                    .parent(models().getExistingFile(mcLoc("block/cube_all")))
                    .texture("all", offTexture);
            getVariantBuilder(block).forAllStates(state -> state.get(ViewedBlockBlock.POWERED) ? ConfiguredModel.builder().modelFile(modelOn).build() : ConfiguredModel.builder().modelFile(modelOff).build());
        }
    }
}
