package net.anti_quark.EssentialFeatures.common.craft;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {


	public static void addRecipes () 
	{
		GameRegistry.addSmelting(Blocks.GLOWSTONE, new ItemStack(ModBlocks.SMOOTH_GLOWSTONE), 1.0F);		
		GameRegistry.addSmelting(ModItems.LONDON_CLAY, new ItemStack(ModItems.LONDON_BRICK), 1.0F);
		GameRegistry.addSmelting(ModItems.SAND_CLAY_MIXTURE, new ItemStack(ModItems.CREAM_BRICK), 1.0F);
	}
}
