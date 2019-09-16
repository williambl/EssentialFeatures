package com.williambl.essentialfeatures.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemCereal extends EFItem {

    boolean isIron;

    public ItemCereal(String registryName, Food food, boolean isIron) {
        super(registryName, new Item.Properties().group(ItemGroup.FOOD).food(food));
        this.isIron = isIron;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return isIron;
    }

    @Nullable
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }

}