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
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.viewedBlock), new Object[] { "qrq", "r*r", "qrq", 'q', Items.QUARTZ, 'r', Items.REDSTONE, '*', Items.ENDER_EYE });
		GameRegistry.addSmelting(Blocks.GLOWSTONE, new ItemStack(ModBlocks.smoothGlowstone), 1.0F);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.polishedGlowstone), new Object[] { "ss", "ss", 's', ModBlocks.smoothGlowstone});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.snowBrick), new Object[] { "ss", "ss", 's', Blocks.SNOW});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemCereal), new Object[] {"w", "m", "b", 'w', Items.WHEAT, 'm', Items.MILK_BUCKET, 'b', Items.BOWL});
	}
}
