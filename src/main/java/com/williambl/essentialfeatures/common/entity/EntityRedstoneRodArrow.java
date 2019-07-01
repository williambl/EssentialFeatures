package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;

public class EntityRedstoneRodArrow extends AbstractArrowEntity {

    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;

    private int ticksInAir;

    private double damage;
    private int knockbackStrength;

    public EntityRedstoneRodArrow(World worldIn) {
        super(ModEntities.REDSTONE_ROD_ARROW, worldIn);
    }

    public EntityRedstoneRodArrow(double x, double y, double z, World worldIn) {
        super(ModEntities.REDSTONE_ROD_ARROW, x, y, z, worldIn);
    }

    public EntityRedstoneRodArrow(LivingEntity shooter, World worldIn) {
        super(ModEntities.REDSTONE_ROD_ARROW, shooter, worldIn);
    }

    @Override
    protected void arrowHit(LivingEntity target) {
        super.arrowHit(target);

        if (target.world.canBlockSeeSky(new BlockPos(target.posX, target.posY, target.posZ))) {
            LightningBoltEntity bolt = new LightningBoltEntity(target.world, target.posX, target.posY, target.posZ, false);
            ((ServerWorld) target.world).addLightningBolt(bolt);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.REDSTONE_ROD_ARROW);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.REDSTONE_ROD_ARROW;
    }
}
