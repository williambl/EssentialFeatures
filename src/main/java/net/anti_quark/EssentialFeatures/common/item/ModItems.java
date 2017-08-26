package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.client.music.ModSound;
import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.anti_quark.EssentialFeatures.common.item.ItemEFRecord;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static ItemCereal CEREAL;
	public static ItemCereal IRON_CEREAL;
	public static EFItem LONDON_CLAY;
	public static EFItem LONDON_BRICK;
	public static EFItem SAND_CLAY_MIXTURE;
	public static EFItem CREAM_BRICK;
	public static ItemEFRecord RECORD;

	public static void addItems () 
	{
		CEREAL = new ItemCereal("cereal", 1, 6, false);
		IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
		LONDON_CLAY = new EFItem("london_clay", CreativeTabs.MATERIALS);
		SAND_CLAY_MIXTURE = new EFItem("sand_clay_mixture", CreativeTabs.MATERIALS);
		LONDON_BRICK = new EFItem("london_brick", CreativeTabs.MATERIALS);
		CREAM_BRICK = new EFItem("cream_brick", CreativeTabs.MATERIALS);
		RECORD = new ItemEFRecord("scarlet", ModSound.RECORD_SCARLET);
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				CEREAL,
				IRON_CEREAL,
				LONDON_CLAY,
				SAND_CLAY_MIXTURE,
				LONDON_BRICK,
				CREAM_BRICK,
				RECORD
				);
	}
	
	public static void initModels ()
	{
		CEREAL.initModel();
		IRON_CEREAL.initModel();
		LONDON_CLAY.initModel();
		SAND_CLAY_MIXTURE.initModel();
		LONDON_BRICK.initModel();
		CREAM_BRICK.initModel();
		RECORD.initModel();
	}
}
