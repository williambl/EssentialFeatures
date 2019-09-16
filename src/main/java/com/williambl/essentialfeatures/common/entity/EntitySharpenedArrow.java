package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Arrays;

public class EntitySharpenedArrow extends AbstractArrowEntity {

    private int xTile;
    private int yTile;
    private int zTile;
    private BlockState inBlockState;

    private int ticksInAir;

    private double damage;
    private int knockbackStrength;

    private Material[] breakableMaterials = {
            Material.GLASS,
            Material.ICE,
            Material.PLANTS,
            Material.WEB
    };

    public EntitySharpenedArrow(EntityType entityType, World world) {
        super(entityType, world);
        setIsCritical(true);
    }

    public EntitySharpenedArrow(World worldIn) {
        super(ModEntities.SHARPENED_ARROW, worldIn);
        this.setIsCritical(true);
    }

    public EntitySharpenedArrow(double x, double y, double z, World worldIn) {
        super(ModEntities.SHARPENED_ARROW, x, y, z, worldIn);
        this.setIsCritical(true);
    }

    public EntitySharpenedArrow(LivingEntity shooter, World worldIn) {
        super(ModEntities.SHARPENED_ARROW, shooter, worldIn);
        this.setIsCritical(true);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            this.func_213868_a((EntityRayTraceResult) raytraceResultIn);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceResultIn;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());
            this.inBlockState = blockstate;
            if (!Arrays.asList(breakableMaterials).contains(blockstate.getMaterial())) {
                Vec3d vec3d = blockraytraceresult.getHitVec().subtract(this.posX, this.posY, this.posZ);
                this.setMotion(vec3d);
                Vec3d vec3d1 = vec3d.normalize().scale(0.05F);
                this.posX -= vec3d1.x;
                this.posY -= vec3d1.y;
                this.posZ -= vec3d1.z;
                this.playSound(this.getHitGroundSound(), 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.inGround = true;
                this.arrowShake = 7;
                this.setIsCritical(false);
                this.func_213872_b((byte) 0);
                this.setHitSound(SoundEvents.ENTITY_ARROW_HIT);
                this.func_213865_o(false);
                blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);
            } else {
                if (blockstate.getMaterial() != Material.AIR)
                    this.inBlockState.onEntityCollision(this.world, blockraytraceresult.getPos(), this);

                if (blockstate.getMaterial() == Material.GLASS || blockstate.getMaterial() == Material.ICE)
                    this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                else if (blockstate.getMaterial() == Material.PLANTS || blockstate.getMaterial() == Material.WEB)
                    this.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                world.setBlockState(new BlockPos(this.xTile, this.yTile, this.zTile), Blocks.AIR.getDefaultState());
            }

            this.setIsCritical(false);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.SHARPENED_ARROW);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.SHARPENED_ARROW;
    }
}
