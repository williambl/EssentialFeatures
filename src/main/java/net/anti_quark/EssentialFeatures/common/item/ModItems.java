package net.anti_quark.EssentialFeatures.common.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.anti_quark.EssentialFeatures.client.music.ModSound;
import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.config.ModConfig;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.anti_quark.EssentialFeatures.common.item.ItemEFRecord;
import net.minecraft.item.ItemRecord;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static ItemCereal CEREAL;
	public static ItemCereal IRON_CEREAL;
	public static EFItem LONDON_CLAY;
	public static EFItem LONDON_BRICK;
	public static EFItem SAND_CLAY_MIXTURE;
	public static EFItem CREAM_BRICK;
	public static ItemEFRecord RECORD_SCARLET;
	public static ItemEFRecord RECORD_LOFI;
	public static ArrayList<ItemPortableJukebox> PORTABLE_JUKEBOXES = new ArrayList<ItemPortableJukebox>();

	public static void addItems () 
	{
		CEREAL = new ItemCereal("cereal", 1, 6, false);
		IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
		LONDON_CLAY = new EFItem("london_clay", CreativeTabs.MATERIALS);
		SAND_CLAY_MIXTURE = new EFItem("sand_clay_mixture", CreativeTabs.MATERIALS);
		LONDON_BRICK = new EFItem("london_brick", CreativeTabs.MATERIALS);
		CREAM_BRICK = new EFItem("cream_brick", CreativeTabs.MATERIALS);
		RECORD_SCARLET = new ItemEFRecord("scarlet", ModSound.RECORD_SCARLET);
		RECORD_LOFI = new ItemEFRecord("lo-fi", ModSound.RECORD_LOFI);

		addPortableJukeboxes();
	}

	private static void addPortableJukeboxes () {

		HashMap<String, ItemRecord> discs = new HashMap<>();
		discs.put("13", (ItemRecord)Items.RECORD_13);
		discs.put("cat", (ItemRecord)Items.RECORD_CAT);
		discs.put("blocks", (ItemRecord)Items.RECORD_BLOCKS);
		discs.put("chirp", (ItemRecord)Items.RECORD_CHIRP);
		discs.put("far", (ItemRecord)Items.RECORD_FAR);
		discs.put("mall", (ItemRecord)Items.RECORD_MALL);
		discs.put("mellohi", (ItemRecord)Items.RECORD_MELLOHI);
		discs.put("stal", (ItemRecord)Items.RECORD_STAL);
		discs.put("strad", (ItemRecord)Items.RECORD_STRAD);
		discs.put("ward", (ItemRecord)Items.RECORD_WARD);
		discs.put("11", (ItemRecord)Items.RECORD_11);
		discs.put("wait", (ItemRecord)Items.RECORD_WAIT);

		discs.put("scarlet", RECORD_SCARLET);
		discs.put("lo-fi", RECORD_LOFI);

		discs.forEach((name, record) -> PORTABLE_JUKEBOXES.add(new ItemPortableJukebox("portable_jukebox_"+name, CreativeTabs.TOOLS, record)));
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
					LONDON_CLAY,
					SAND_CLAY_MIXTURE,
					LONDON_BRICK,
					CREAM_BRICK,
					RECORD_SCARLET,
					RECORD_LOFI,
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
			LONDON_CLAY.initModel();
			SAND_CLAY_MIXTURE.initModel();
			LONDON_BRICK.initModel();
			CREAM_BRICK.initModel();
			RECORD_SCARLET.initModel();
			RECORD_LOFI.initModel();
			PORTABLE_JUKEBOXES.forEach(EFItem::initModel);
		}
	}
}
