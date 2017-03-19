package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static ItemCereal CEREAL;
	public static ItemCereal IRON_CEREAL;
	public static ItemBlockSmoothSlab SMOOTH_DOUBLE_STONE_SLAB;
	public static ItemBlockSmoothSlab SMOOTH_DOUBLE_SANDSTONE_SLAB;
	public static ItemBlockSmoothSlab SMOOTH_DOUBLE_RED_SANDSTONE_SLAB;

	public static void addItems () 
	{
		CEREAL = new ItemCereal("cereal", 1, 6, false);
		IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
		SMOOTH_DOUBLE_STONE_SLAB = new ItemBlockSmoothSlab(Blocks.DOUBLE_STONE_SLAB, "smooth_double_stone_slab", BlockStoneSlab.EnumType.STONE);
		SMOOTH_DOUBLE_SANDSTONE_SLAB = new ItemBlockSmoothSlab(Blocks.DOUBLE_STONE_SLAB, "smooth_double_sandstone_slab", BlockStoneSlab.EnumType.SAND);
		SMOOTH_DOUBLE_RED_SANDSTONE_SLAB = new ItemBlockSmoothSlab(Blocks.DOUBLE_STONE_SLAB2, "smooth_double_red_sandstone_slab", BlockStoneSlabNew.EnumType.RED_SANDSTONE);
	}
	
	public static void initModels ()
	{
		CEREAL.initModel();
		IRON_CEREAL.initModel();
	}
}
