package com.williambl.essentialfeatures.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class Config {

    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;

    public static final CommonConfig COMMON_CONFIG;
    public static final ClientConfig CLIENT_CONFIG;
    public static boolean slate = true;
    public static boolean nettles = false;
    public static int viewedBlockDelay = 2;
    public static int viewedBlockRange = 50;
    public static boolean witchBats = true;
    public static double witchCatChance = 0.05;
    public static boolean motd = true;
    public static boolean spawnExplosion = true;
    public static boolean customMusic = true;

    static {
        COMMON_CONFIG = new CommonConfig(COMMON_BUILDER);
        CLIENT_CONFIG = new ClientConfig(CLIENT_BUILDER);

        COMMON_SPEC = COMMON_BUILDER.build();
        CLIENT_SPEC = CLIENT_BUILDER.build();
    }

    public static void refreshCommon() {
        slate = COMMON_CONFIG.slate.get();
        nettles = COMMON_CONFIG.nettles.get();
        viewedBlockDelay = COMMON_CONFIG.viewedBlockDelay.get();
        viewedBlockRange = COMMON_CONFIG.viewedBlockRange.get();
        witchBats = COMMON_CONFIG.witchBats.get();
        witchCatChance = COMMON_CONFIG.witchCatChance.get();
    }

    public static void refreshClient() {
        motd = CLIENT_CONFIG.motd.get();
        spawnExplosion = CLIENT_CONFIG.spawnExplosion.get();
        customMusic = CLIENT_CONFIG.customMusic.get();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        spec.setConfig(configData);
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.ConfigValue<Boolean> slate;
        public final ForgeConfigSpec.ConfigValue<Boolean> nettles;
        public final ForgeConfigSpec.ConfigValue<Integer> viewedBlockRange;
        public final ForgeConfigSpec.ConfigValue<Integer> viewedBlockDelay;
        public final ForgeConfigSpec.ConfigValue<Boolean> witchBats;
        public final ForgeConfigSpec.ConfigValue<Double> witchCatChance;

        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("generation");
            slate = builder
                    .comment("Generate slate [default:true]")
                    .translation("config.slate.enable")
                    .define("slate", true);
            nettles = builder
                    .comment("Generate nettles [default:true]")
                    .translation("config.nettles.enable")
                    .define("nettles", true);
            builder.pop();
            builder.push("optimisation");
            viewedBlockDelay = builder
                    .comment("Ticks between viewed block checks [default:2]")
                    .translation("config.viewed_block.delay")
                    .defineInRange("viewed_block.delay", 2, 0, 20);
            viewedBlockRange = builder
                    .comment("Range in which viewed block checks [default:50]")
                    .translation("config.viewed_block.range")
                    .defineInRange("viewed_block.range", 50, 0, 64);
            builder.pop();
            builder.push("misc");
            witchBats = builder
                    .comment("Spawn a cloud of bats on witch death [default:true]")
                    .translation("config.witch.bats")
                    .define("witch.bats", true);
            witchCatChance = builder
                    .comment("Chance that a cat will spawn on witch death [default:0.05]")
                    .translation("config.witch.cat_chance")
                    .defineInRange("witch.cat_chance", 0.05, 0, 1);
            builder.pop();
        }
    }

    public static class ClientConfig {
        public final ForgeConfigSpec.BooleanValue motd;
        public final ForgeConfigSpec.BooleanValue spawnExplosion;
        public final ForgeConfigSpec.BooleanValue customMusic;

        ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("misc");
            motd = builder
                    .comment("Print the MOTD from the net in chat on login [default:true]")
                    .translation("config.motd")
                    .define("motd", true);
            spawnExplosion = builder
                    .comment("Make explosion effects on respawn [default:true]")
                    .translation("config.spawn_explosion")
                    .define("spawn_explosion", true);
            customMusic = builder
                    .comment("Play custom background music [default:true]")
                    .translation("config.custom_music")
                    .define("custom_music", true);
            builder.pop();
        }
    }
}
