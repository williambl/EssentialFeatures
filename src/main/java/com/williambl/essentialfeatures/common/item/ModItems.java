package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.client.music.ModSound;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ModItems {

    public static ItemCereal CEREAL;
    public static ItemCereal IRON_CEREAL;
    public static EFItem DIRTY_CLAY;
    public static EFItem DIRTY_BRICK;
    public static EFItem SAND_CLAY_MIXTURE;
    public static EFItem CREAM_BRICK;
    public static ItemEFRecord RECORD_SCARLET;
    public static ItemEFRecord RECORD_LOFI;
    public static ItemPortableNoteBlock PORTABLE_NOTE_BLOCK;
    public static ItemSharpenedArrow SHARPENED_ARROW;

    public static ItemPortableJukebox PORTABLE_JUKEBOX;
    public static ArrayList<ItemPortableJukebox> PORTABLE_JUKEBOXES = new ArrayList<>();

    public static ItemCookedNettles COOKED_NETTLES;

    public static ItemRedstoneRodSword REDSTONE_ROD_SWORD;
    public static ItemRedstoneRodArrow REDSTONE_ROD_ARROW;

    public static void addItems() {
        CEREAL = new ItemCereal("cereal", 1, 6, false);
        IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
        DIRTY_CLAY = new EFItem("dirty_clay", ItemGroup.MATERIALS);
        SAND_CLAY_MIXTURE = new EFItem("sand_clay_mixture", ItemGroup.MATERIALS);
        DIRTY_BRICK = new EFItem("dirty_brick", ItemGroup.MATERIALS);
        CREAM_BRICK = new EFItem("cream_brick", ItemGroup.MATERIALS);
        RECORD_SCARLET = new ItemEFRecord("scarlet", 1, ModSound.RECORD_SCARLET);
        RECORD_LOFI = new ItemEFRecord("lo-fi", 2, ModSound.RECORD_LOFI);
        PORTABLE_NOTE_BLOCK = new ItemPortableNoteBlock("portable_note_block");
        SHARPENED_ARROW = new ItemSharpenedArrow("sharpened_arrow");
        COOKED_NETTLES = new ItemCookedNettles("cooked_nettles");
        REDSTONE_ROD_SWORD = new ItemRedstoneRodSword("redstone_rod_sword", ItemTier.GOLD);
        REDSTONE_ROD_ARROW = new ItemRedstoneRodArrow("redstone_rod_arrow");

        addPortableJukeboxes();
    }

    private static void addPortableJukeboxes() {
        PORTABLE_JUKEBOX = new ItemPortableJukebox("portable_jukebox", ItemGroup.TOOLS, null);

        HashMap<String, ItemRecord> discs = new HashMap<>();
        discs.put("13", (ItemRecord) Items.MUSIC_DISC_13);
        discs.put("cat", (ItemRecord) Items.MUSIC_DISC_CAT);
        discs.put("blocks", (ItemRecord) Items.MUSIC_DISC_BLOCKS);
        discs.put("chirp", (ItemRecord) Items.MUSIC_DISC_CHIRP);
        discs.put("far", (ItemRecord) Items.MUSIC_DISC_FAR);
        discs.put("mall", (ItemRecord) Items.MUSIC_DISC_MALL);
        discs.put("mellohi", (ItemRecord) Items.MUSIC_DISC_MELLOHI);
        discs.put("stal", (ItemRecord) Items.MUSIC_DISC_STAL);
        discs.put("strad", (ItemRecord) Items.MUSIC_DISC_STRAD);
        discs.put("ward", (ItemRecord) Items.MUSIC_DISC_WARD);
        discs.put("11", (ItemRecord) Items.MUSIC_DISC_11);
        discs.put("wait", (ItemRecord) Items.MUSIC_DISC_WAIT);

        discs.put("scarlet", RECORD_SCARLET);
        discs.put("lo-fi", RECORD_LOFI);

        discs.forEach((name, record) -> PORTABLE_JUKEBOXES.add(new ItemPortableJukebox("portable_jukebox_" + name, ItemGroup.TOOLS, record)));
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    CEREAL,
                    IRON_CEREAL,
                    DIRTY_CLAY,
                    SAND_CLAY_MIXTURE,
                    DIRTY_BRICK,
                    CREAM_BRICK,
                    RECORD_SCARLET,
                    RECORD_LOFI,
                    PORTABLE_JUKEBOX,
                    PORTABLE_NOTE_BLOCK,
                    SHARPENED_ARROW,
                    COOKED_NETTLES,
                    REDSTONE_ROD_SWORD,
                    REDSTONE_ROD_ARROW
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
            }
            for (final Item jukebox : PORTABLE_JUKEBOXES) {
                registry.register(jukebox);
                ITEMS.add(jukebox);
            }
        }

        @OnlyIn(Dist.CLIENT)
        public static void registerItemColors() {
            ItemColors itemColors = Minecraft.getInstance().getItemColors();
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            itemColors.register((stack, tintIndex) -> {
                IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getDefaultState();
                return blockColors.getColor(iblockstate, null, null, tintIndex);
            }, ModBlocks.NETTLES);
        }

        @SubscribeEvent
        public static void setBurnTimes(FurnaceFuelBurnTimeEvent e) {
            if (e.getItemStack().isItemEqual(new ItemStack(ModBlocks.BLAZE_BLOCK)))
                e.setBurnTime(4800);
        }
    }
}
