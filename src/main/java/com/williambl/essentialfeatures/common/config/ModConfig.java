package com.williambl.essentialfeatures.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static
    {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static
    {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static boolean addVillagers = true;
    public static boolean addSmelting = false;
    public static boolean addBlocks = true;
    public static boolean addItems = true;
    public static boolean generateSlate = true;
    public static boolean generateNettles = true;

    public static int viewedBlockDelay = 2;
    public static int viewedBlockRange = 50;

    public static class ServerConfig
    {
        public final ForgeConfigSpec.ConfigValue<Boolean> villagers;
        public final ForgeConfigSpec.ConfigValue<Boolean> smelting;
        public final ForgeConfigSpec.ConfigValue<Boolean> blocks;
        public final ForgeConfigSpec.ConfigValue<Boolean> items;
        public final ForgeConfigSpec.ConfigValue<Boolean> slate;
        public final ForgeConfigSpec.ConfigValue<Boolean> nettles;
        public final ForgeConfigSpec.ConfigValue<Integer> viewedBlockRange;
        public final ForgeConfigSpec.ConfigValue<Integer> viewedBlockDelay;

        ServerConfig(ForgeConfigSpec.Builder builder)
        {
            builder.push("general");
            villagers = builder
                    .comment("Add mechanic villagers [false/true|default:true]")
                    .translation("config.villagers.enable")
                    .define("villagers", true);
            smelting = builder
                    .comment("Add smelting recipes [false/true|default:true]")
                    .translation("config.smelting.enable")
                    .define("smelting", true);
            blocks = builder
                    .comment("Add blocks [false/true|default:true]")
                    .translation("config.blocks.enable")
                    .define("blocks", true);
            items = builder
                    .comment("Add items [false/true|default:true]")
                    .translation("config.items.enable")
                    .define("items", true);
            slate = builder
                    .comment("Generate slate [false/true|default:true]")
                    .translation("config.slate.enable")
                    .define("slate", true);
            nettles = builder
                    .comment("Generate nettles [false/true|default:true]")
                    .translation("config.nettles.enable")
                    .define("nettles", true);
            viewedBlockDelay = builder
                    .comment("Ticks between viewed block checks [0..20|default:2]")
                    .translation("config.viewed_block.delay")
                    .defineInRange("viewed_block_delay", 2, 0, 20);
            viewedBlockRange = builder
                    .comment("Range in which viewed block checks [0..64|default:50]")
                    .translation("config.viewed_block.range")
                    .defineInRange("viewed_block_range", 50, 0, 64);
            builder.pop();
        }
    }

    public static class ClientConfig
    {

        ClientConfig(ForgeConfigSpec.Builder builder)
        {
        }
    }

    public static void refreshClient()
    {
    }

    public static void refreshServer()
    {
        addVillagers = SERVER.villagers.get();
        addSmelting = SERVER.smelting.get();
        addItems = SERVER.items.get();
        addBlocks = SERVER.blocks.get();
        generateSlate = SERVER.slate.get();
        generateNettles = SERVER.nettles.get();
        viewedBlockDelay = SERVER.viewedBlockDelay.get();
        viewedBlockRange = SERVER.viewedBlockRange.get();
    }
}
