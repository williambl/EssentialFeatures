package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.EssentialFeatures;
import com.williambl.essentialfeatures.client.music.ModSound;
import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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
public class ModItems {

    @ObjectHolder("cereal")
    public static CerealItem CEREAL;
    @ObjectHolder("iron_cereal")
    public static CerealItem IRON_CEREAL;
    @ObjectHolder("dirty_clay")
    public static EFItem DIRTY_CLAY;
    @ObjectHolder("dirty_brick")
    public static EFItem DIRTY_BRICK;
    @ObjectHolder("sand_clay_mixture")
    public static EFItem SAND_CLAY_MIXTURE;
    @ObjectHolder("cream_brick")
    public static EFItem CREAM_BRICK;
    @ObjectHolder("scarlet")
    public static EFRecordItem RECORD_SCARLET;
    @ObjectHolder("lo_fi")
    public static EFRecordItem RECORD_LOFI;
    @ObjectHolder("portable_note_block")
    public static PortableNoteBlockItem PORTABLE_NOTE_BLOCK;
    @ObjectHolder("sharpened_arrow")
    public static ItemSharpenedArrow SHARPENED_ARROW;

    @ObjectHolder("portable_jukebox")
    public static PortableJukeboxItem PORTABLE_JUKEBOX;

    @ObjectHolder("cooked_nettles")
    public static EFItem COOKED_NETTLES;

    @ObjectHolder("redstone_rod_sword")
    public static RedstoneRodSwordItem REDSTONE_ROD_SWORD;
    @ObjectHolder("redstone_rod_arrow")
    public static ItemRedstoneRodArrow REDSTONE_ROD_ARROW;

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
            ModSound.addSoundEvents(); //This is ugly but soundevents are done after items so this must be done :/
            final Item[] items = {
                    new CerealItem("cereal", new Food.Builder().hunger(6).saturation(1).build(), false),
                    new CerealItem("iron_cereal", new Food.Builder().hunger(6).saturation(3).effect(new EffectInstance(Effects.RESISTANCE, 600, 1), 1).effect(new EffectInstance(Effects.REGENERATION, 200, 1), 1).build(), true),
                    new EFItem("dirty_clay", ItemGroup.MATERIALS),
                    new EFItem("sand_clay_mixture", ItemGroup.MATERIALS),
                    new EFItem("dirty_brick", ItemGroup.MATERIALS),
                    new EFItem("cream_brick", ItemGroup.MATERIALS),
                    new EFRecordItem("scarlet", 1, ModSound.RECORD_SCARLET),
                    new EFRecordItem("lo_fi", 2, ModSound.RECORD_LOFI),
                    new PortableNoteBlockItem("portable_note_block"),
                    new ItemSharpenedArrow("sharpened_arrow"),
                    new EFItem("cooked_nettles", new Item.Properties().group(ItemGroup.FOOD)),
                    new RedstoneRodSwordItem("redstone_rod_sword", ItemTier.GOLD),
                    new ItemRedstoneRodArrow("redstone_rod_arrow"),
                    new PortableJukeboxItem("portable_jukebox", ItemGroup.MISC)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
            }
        }

        @OnlyIn(Dist.CLIENT)
        public static void registerItemColors() {
            ItemColors itemColors = Minecraft.getInstance().getItemColors();
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            itemColors.register((stack, tintIndex) -> {
                BlockState iblockstate = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                return blockColors.getColor(iblockstate, null, null, tintIndex);
            }, ModBlocks.NETTLES);
        }
    }
}
