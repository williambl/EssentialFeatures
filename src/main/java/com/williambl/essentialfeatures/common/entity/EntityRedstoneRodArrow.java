package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityRedstoneRodArrow extends EntityArrow {

    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;

    private int ticksInAir;

    private double damage;
    private int knockbackStrength;

    public EntityRedstoneRodArrow(World worldIn) {
        super(worldIn);
    }

    public EntityRedstoneRodArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityRedstoneRodArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected void arrowHit(EntityLivingBase target) {
        super.arrowHit(target);

        if (target.world.canBlockSeeSky(new BlockPos(target.posX, target.posY, target.posZ))) {
            EntityLightningBolt bolt = new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, false);
            target.world.addWeatherEffect(bolt);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.REDSTONE_ROD_ARROW);
    }
}