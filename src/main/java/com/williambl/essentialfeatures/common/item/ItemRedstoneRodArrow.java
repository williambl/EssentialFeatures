package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.common.entity.EntityRedstoneRodArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedstoneRodArrow extends ItemArrow {

    public ItemRedstoneRodArrow(String registryName) {
        super(new Properties().group(ItemGroup.COMBAT));
        this.setRegistryName(registryName);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new EntityRedstoneRodArrow(shooter, worldIn);
    }

}
