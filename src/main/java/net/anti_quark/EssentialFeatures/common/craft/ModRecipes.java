package net.anti_quark.EssentialFeatures.common.craft;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {


	public static void addRecipes () 
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.VIEWED_BLOCK), new Object[] { "qrq", "r*r", "qrq", 'q', Items.QUARTZ, 'r', Items.REDSTONE, '*', Items.ENDER_EYE });
		
		GameRegistry.addSmelting(Blocks.GLOWSTONE, new ItemStack(ModBlocks.SMOOTH_GLOWSTONE), 1.0F);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.POLISHED_GLOWSTONE), new Object[] { "ss", "ss", 's', ModBlocks.SMOOTH_GLOWSTONE});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.SNOW_BRICK), new Object[] { "ss", "ss", 's', Blocks.SNOW});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.CEREAL), new Object[] {"w", "m", "b", 'w', Items.WHEAT, 'm', Items.MILK_BUCKET, 'b', Items.BOWL});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.IRON_CEREAL), new Object[] {"iii", "ici", "iii", 'i', Items.IRON_INGOT, 'c', ModItems.CEREAL});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BLOCK_BREAKER), new Object[] {"cpc", "crc", "crc", 'c', Blocks.COBBLESTONE, 'p', Items.DIAMOND_PICKAXE, 'r', Items.REDSTONE});
	}
}
