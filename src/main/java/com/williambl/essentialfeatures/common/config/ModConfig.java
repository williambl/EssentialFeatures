package com.williambl.essentialfeatures.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static final String NAME = "MAIN_CONFIG";
    public static final ForgeConfigSpec spec;
    public static final ModConfig INSTANCE;

    static {
        final Pair<ModConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModConfig::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    ModConfig(ForgeConfigSpec.Builder builder) {
        builder.configure(General::new);
        builder.pop();
    }


    public static class General {
        public final ForgeConfigSpec.ConfigValue<Boolean> villagers;
        public final ForgeConfigSpec.ConfigValue<Boolean> smelting;
        public final ForgeConfigSpec.ConfigValue<Boolean> blocks;
        public final ForgeConfigSpec.ConfigValue<Boolean> slate;
        public final ForgeConfigSpec.ConfigValue<Boolean> nettles;
        public final ForgeConfigSpec.ConfigValue<Boolean> items;
        public final ForgeConfigSpec.ConfigValue<Integer> viewedBlockRange;
        public final ForgeConfigSpec.ConfigValue<Integer> viewedBlockDelay;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("General");
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
            slate = builder
                    .comment("Generate slate [false/true|default:true]")
                    .translation("config.slate.enable")
                    .define("slate", true);
            nettles = builder
                    .comment("Generate nettles [false/true|default:true]")
                    .translation("config.nettles.enable")
                    .define("nettles", true);
            items = builder
                    .comment("Add items [false/true|default:true]")
                    .translation("config.items.enable")
                    .define("items", true);
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

}
