package com.williambl.essentialfeatures.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemCereal extends ItemFood {

    boolean isIron;

    public ItemCereal(String registryName, float saturation, int healAmount, boolean isIron) {
        super(healAmount, saturation, false, new Properties().group(ItemGroup.FOOD));
        this.isIron = isIron;
        this.setRegistryName(registryName);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return isIron;
    }

    @Nullable
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);

        if (isIron) {
            entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 1));
            entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 1));
        }

        return new ItemStack(Items.BOWL);
    }

}