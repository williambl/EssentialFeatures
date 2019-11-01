package com.williambl.essentialfeatures.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williambl.essentialfeatures.common.block.BlockStainedLamp;
import com.williambl.essentialfeatures.common.block.BlockStainedRedstoneTorch;
import com.williambl.essentialfeatures.common.block.BlockStainedRedstoneWallTorch;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.commons.lang3.tuple.Pair;
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
                    .addEntry(ItemLootEntry.builder(block)
                            .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                            .acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY)
                                    .func_216055_a("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                                    .func_216055_a("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                            .acceptFunction(SetContents.func_215920_b()
                                    .func_216075_a(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents"))))
                    );
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
                    LOGGER.error("Couldn't write loot table {}", path, e);
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
            for (Pair<BlockStainedRedstoneTorch, BlockStainedRedstoneWallTorch> torchPair :
                    ModBlocks.STAINED_REDSTONE_TORCHES) {
                lootTables.put(torchPair.getLeft(), createStandardTable(torchPair.getLeft().getRegistryName().getPath(), torchPair.getLeft()));
                lootTables.put(torchPair.getRight(), createStandardTable(torchPair.getLeft().getRegistryName().getPath(), torchPair.getLeft()));
            }

            for (BlockStainedLamp lamp : ModBlocks.STAINED_LAMPS) {
                lootTables.put(lamp, createStandardTable(lamp.getRegistryName().getPath(), lamp));
            }
        }
    }

    public static class BaseBlockStates extends BlockStateProvider {
        public BaseBlockStates(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
            super(gen, modid, exFileHelper);
        }

        @Override
        protected void registerStatesAndModels() {

        }
    }
}
