package net.anti_quark.EssentialFeatures.common.craft;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {


	public static void addRecipes () 
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.viewedBlock), new Object[] { "qrq", "r*r", "qrq", 'q', Items.QUARTZ, 'r', Items.REDSTONE, '*', Items.ENDER_EYE });
	}
}
