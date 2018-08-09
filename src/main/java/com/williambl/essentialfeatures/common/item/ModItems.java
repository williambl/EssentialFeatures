package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.client.music.ModSound;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.config.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

    public static void addItems() {
        CEREAL = new ItemCereal("cereal", 1, 6, false);
        IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
        DIRTY_CLAY = new EFItem("dirty_clay", CreativeTabs.MATERIALS);
        SAND_CLAY_MIXTURE = new EFItem("sand_clay_mixture", CreativeTabs.MATERIALS);
        DIRTY_BRICK = new EFItem("dirty_brick", CreativeTabs.MATERIALS);
        CREAM_BRICK = new EFItem("cream_brick", CreativeTabs.MATERIALS);
        RECORD_SCARLET = new ItemEFRecord("scarlet", ModSound.RECORD_SCARLET);
        RECORD_LOFI = new ItemEFRecord("lo-fi", ModSound.RECORD_LOFI);
        PORTABLE_NOTE_BLOCK = new ItemPortableNoteBlock("portable_note_block");
        SHARPENED_ARROW = new ItemSharpenedArrow("sharpened_arrow");
        COOKED_NETTLES = new ItemCookedNettles("cooked_nettles");

        addPortableJukeboxes();
    }

    private static void addPortableJukeboxes() {
        PORTABLE_JUKEBOX = new ItemPortableJukebox("portable_jukebox", CreativeTabs.TOOLS, null);

        HashMap<String, ItemRecord> discs = new HashMap<>();
        discs.put("13", (ItemRecord) Items.RECORD_13);
        discs.put("cat", (ItemRecord) Items.RECORD_CAT);
        discs.put("blocks", (ItemRecord) Items.RECORD_BLOCKS);
        discs.put("chirp", (ItemRecord) Items.RECORD_CHIRP);
        discs.put("far", (ItemRecord) Items.RECORD_FAR);
        discs.put("mall", (ItemRecord) Items.RECORD_MALL);
        discs.put("mellohi", (ItemRecord) Items.RECORD_MELLOHI);
        discs.put("stal", (ItemRecord) Items.RECORD_STAL);
        discs.put("strad", (ItemRecord) Items.RECORD_STRAD);
        discs.put("ward", (ItemRecord) Items.RECORD_WARD);
        discs.put("11", (ItemRecord) Items.RECORD_11);
        discs.put("wait", (ItemRecord) Items.RECORD_WAIT);

        discs.put("scarlet", RECORD_SCARLET);
        discs.put("lo-fi", RECORD_LOFI);

        discs.forEach((name, record) -> PORTABLE_JUKEBOXES.add(new ItemPortableJukebox("portable_jukebox_" + name, CreativeTabs.TOOLS, record)));
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            if (!ModConfig.items)
                return;

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
                    COOKED_NETTLES
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

        /**
         * Register this mod's Item Models.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlockModels(ModelRegistryEvent event) {
            if (!ModConfig.items)
                return;

            CEREAL.initModel();
            IRON_CEREAL.initModel();
            DIRTY_CLAY.initModel();
            SAND_CLAY_MIXTURE.initModel();
            DIRTY_BRICK.initModel();
            CREAM_BRICK.initModel();
            RECORD_SCARLET.initModel();
            RECORD_LOFI.initModel();
            PORTABLE_NOTE_BLOCK.initModel();
            SHARPENED_ARROW.initModel();
            COOKED_NETTLES.initModel();

            PORTABLE_JUKEBOX.initModel();
            PORTABLE_JUKEBOXES.forEach(EFItem::initModel);
        }

        public static void registerItemColors() {
            ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
            BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
            itemColors.registerItemColorHandler(new IItemColor() {
                @Override
                public int colorMultiplier(ItemStack stack, int tintIndex) {
                    IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
                    return blockColors.colorMultiplier(iblockstate, (IBlockAccess) null, (BlockPos) null, tintIndex);
                }
            }, ModBlocks.NETTLES);
        }
    }
}
