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
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BLOCK_PLACER), new Object[] {"ccc", "cpc", "crc", 'c', Blocks.COBBLESTONE, 'p', Blocks.PISTON, 'r', Items.REDSTONE});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.DOUBLE_STONE_SLAB, 2, 8), new Object[] {new ItemStack(Blocks.STONE_SLAB, 2, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.DOUBLE_STONE_SLAB, 2, 9), new Object[] {new ItemStack(Blocks.STONE_SLAB, 2, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.DOUBLE_STONE_SLAB2, 2, 8), new Object[] {new ItemStack(Blocks.STONE_SLAB2, 2, 0)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.LONDON_CLAY, 2), new Object[]{new ItemStack(Items.CLAY_BALL, 2), new ItemStack(Blocks.SAND), new ItemStack(Items.GUNPOWDER)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.SAND_CLAY_MIXTURE, 1, 0), new Object[] {new ItemStack(Items.CLAY_BALL), new ItemStack(Blocks.SAND)});
		
		GameRegistry.addSmelting(ModItems.LONDON_CLAY, new ItemStack(ModItems.LONDON_BRICK), 1.0F);
		GameRegistry.addSmelting(ModItems.SAND_CLAY_MIXTURE, new ItemStack(ModItems.CREAM_BRICK), 1.0F);
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 0), new Object[] {"bb", "bb", 'b', ModItems.CREAM_BRICK});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 1), new Object[] {"bb", "bb", 'b', ModItems.LONDON_BRICK});
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 2, 2), new Object[] {Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 3), new Object[] {"bl", "lb", 'b', Items.BRICK, 'l', new ItemStack(Items.DYE, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 4), new Object[] {new ItemStack(Items.BRICK), new ItemStack(Items.NETHERBRICK), new ItemStack(ModItems.LONDON_BRICK), new ItemStack(ModItems.CREAM_BRICK)});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.CRYING_OBSIDIAN), new Object[] {" l ", "lol", " l ", 'l', new ItemStack(Items.DYE, 1, 4), 'o', Blocks.OBSIDIAN});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.SPIKE_BLOCK, 8), new Object[] {"i i", "iii", "iii", 'i', Items.IRON_INGOT});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 0), new Object[] {" s ", "s s", " s ", 's', Blocks.STONE});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 1), new Object[] {" s ", "s s", " s ", 's', new ItemStack(Blocks.STONE, 1, 1)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 2), new Object[] {" s ", "s s", " s ", 's', new ItemStack(Blocks.STONE, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 3), new Object[] {" s ", "s s", " s ", 's', new ItemStack(Blocks.STONE, 1, 3)});

	}
}
