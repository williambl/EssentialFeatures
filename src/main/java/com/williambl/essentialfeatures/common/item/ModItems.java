package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.EssentialFeatures;
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
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ObjectHolder(EssentialFeatures.MODID)
public class ModItems {

    @ObjectHolder("cereal")
    public static ItemCereal CEREAL;
    @ObjectHolder("iron_cereal")
    public static ItemCereal IRON_CEREAL;
    @ObjectHolder("dirty_clay")
    public static EFItem DIRTY_CLAY;
    @ObjectHolder("dirty_brick")
    public static EFItem DIRTY_BRICK;
    @ObjectHolder("sand_clay_mixture")
    public static EFItem SAND_CLAY_MIXTURE;
    @ObjectHolder("cream_brick")
    public static EFItem CREAM_BRICK;
    @ObjectHolder("scarlet")
    public static ItemEFRecord RECORD_SCARLET;
    @ObjectHolder("lo-fi")
    public static ItemEFRecord RECORD_LOFI;
    @ObjectHolder("portable_note_block")
    public static ItemPortableNoteBlock PORTABLE_NOTE_BLOCK;
    @ObjectHolder("sharpened_arrow")
    public static ItemSharpenedArrow SHARPENED_ARROW;

    @ObjectHolder("portable_jukebox")
    public static ItemPortableJukebox PORTABLE_JUKEBOX;
    public static ArrayList<ItemPortableJukebox> PORTABLE_JUKEBOXES = new ArrayList<>();

    @ObjectHolder("cooked_nettles")
    public static ItemCookedNettles COOKED_NETTLES;

    @ObjectHolder("redstone_rod_sword")
    public static ItemRedstoneRodSword REDSTONE_ROD_SWORD;
    @ObjectHolder("redstone_rod_arrow")
    public static ItemRedstoneRodArrow REDSTONE_ROD_ARROW;

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
            addPortableJukeboxes();
            final Item[] items = {
                    new ItemCereal("cereal", 1, 6, false),
                    new ItemCereal("iron_cereal", 3, 6, true),
                    new EFItem("dirty_clay", ItemGroup.MATERIALS),
                    new EFItem("sand_clay_mixture", ItemGroup.MATERIALS),
                    new EFItem("dirty_brick", ItemGroup.MATERIALS),
                    new EFItem("cream_brick", ItemGroup.MATERIALS),
                    new ItemEFRecord("scarlet", 1, ModSound.RECORD_SCARLET),
                    new ItemEFRecord("lo-fi", 2, ModSound.RECORD_LOFI),
                    new ItemPortableNoteBlock("portable_note_block"),
                    new ItemSharpenedArrow("sharpened_arrow"),
                    new ItemCookedNettles("cooked_nettles"),
                    new ItemRedstoneRodSword("redstone_rod_sword", ItemTier.GOLD),
                    new ItemRedstoneRodArrow("redstone_rod_arrow")
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
