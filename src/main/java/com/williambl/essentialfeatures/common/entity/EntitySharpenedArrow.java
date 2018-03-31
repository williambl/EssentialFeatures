package com.williambl.essentialfeatures.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySharpenedArrow extends EntityArrow {

    public EntitySharpenedArrow(World worldIn) {
        super(worldIn);
        this.setIsCritical(true);
    }

    public EntitySharpenedArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
        this.setIsCritical(true);
    }

    public EntitySharpenedArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setIsCritical(true);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}
