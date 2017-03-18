package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static ItemCereal CEREAL;
	public static ItemCereal IRON_CEREAL;
	
	public static void addItems () 
	{
		CEREAL = new ItemCereal("cereal", 1, 6, false);
		IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
		GameRegistry.register(new ItemBlockSmoothSlab(Blocks.DOUBLE_STONE_SLAB, "smoothdoubleslab", BlockStoneSlab.EnumType.STONE));
		GameRegistry.register(new ItemBlockSmoothSlab(Blocks.DOUBLE_STONE_SLAB, "smoothdoubleslab1", BlockStoneSlab.EnumType.SAND));
		GameRegistry.register(new ItemBlockSmoothSlab(Blocks.DOUBLE_STONE_SLAB2, "smoothdoubleslab2", BlockStoneSlabNew.EnumType.RED_SANDSTONE));
	}
	
	public static void initModels ()
	{
		CEREAL.initModel();
		IRON_CEREAL.initModel();
	}
}
