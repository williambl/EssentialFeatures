package com.williambl.essentialfeatures.common.item.crafting;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class LightningRecipe extends AbstractCookingRecipe {
    public LightningRecipe(ResourceLocation resourceLocation, String p_i50031_2_, Ingredient p_i50031_3_, ItemStack p_i50031_4_) {
        super(ModCrafting.LIGHTNING_SMELTING_TYPE, resourceLocation, p_i50031_2_, p_i50031_3_, p_i50031_4_, 0, 1);
    }

    public ItemStack getIcon() {
        return new ItemStack(ModBlocks.REDSTONE_ROD);
    }

    public IRecipeSerializer<?> getSerializer() {
        return ModCrafting.LIGHTNING_SMELTING;
    }
}
