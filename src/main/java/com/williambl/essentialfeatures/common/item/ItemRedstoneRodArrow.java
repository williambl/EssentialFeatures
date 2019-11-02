package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.common.entity.RedstoneRodArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedstoneRodArrow extends ArrowItem {

    public ItemRedstoneRodArrow(String registryName) {
        super(new Properties().group(ItemGroup.COMBAT));
        this.setRegistryName(registryName);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new RedstoneRodArrowEntity(shooter, worldIn);
    }

}
