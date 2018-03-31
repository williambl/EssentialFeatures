package com.williambl.essentialfeatures.common.entity;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySharpenedArrow extends EntityArrow {

    public EntitySharpenedArrow(World worldIn) {
        super(worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}
