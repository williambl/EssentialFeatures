package net.anti_quark.EssentialFeatures.common.craft;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {


	public static void addRecipes () 
	{
		GameRegistry.addSmelting(Blocks.GLOWSTONE, new ItemStack(ModBlocks.SMOOTH_GLOWSTONE), 1.0F);		
		GameRegistry.addSmelting(ModItems.LONDON_CLAY, new ItemStack(ModItems.LONDON_BRICK), 1.0F);
		GameRegistry.addSmelting(ModItems.SAND_CLAY_MIXTURE, new ItemStack(ModItems.CREAM_BRICK), 1.0F);

		ModItems.PORTABLE_JUKEBOXES.forEach(jukebox -> GameRegistry.addShapelessRecipe(new ResourceLocation("essentialfeatures:"+jukebox.getRegistryName().getResourcePath()+"_loading"), new ResourceLocation("essentialfeatures:portable_jukebox_loading"),
				new ItemStack(jukebox),
				Ingredient.fromItem(ModItems.PORTABLE_JUKEBOX),
				Ingredient.fromItem(jukebox.record)
		));
	}

}
