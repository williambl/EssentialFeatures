package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBlaze extends EFBlock {

    public BlockBlaze(String registryName) {
        super(registryName, Material.IRON, SoundType.METAL, 5f, 15f, 15);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalker((EntityLivingBase) entityIn)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 5.0F);
            entityIn.setFire(10);
        }
        particleExplosion(worldIn, pos, worldIn.rand);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < 3; ++i) {
            double d0 = (double) pos.getX() + rand.nextDouble();
            double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double) pos.getZ() + rand.nextDouble();

            worldIn.spawnParticle(Particles.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        if (rand.nextDouble() < 0.01 || isTouchingWater(worldIn, pos)) {
            particleExplosion(worldIn, pos, rand);
        }
    }

    private boolean isTouchingWater(World worldIn, BlockPos pos) {
        for (EnumFacing facing : EnumFacing.values())
            if (worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.WATER)
                return true;
        return false;
    }

    private void particleExplosion(World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < 5; ++i) {
            double d0 = (double) pos.getX() + rand.nextDouble();
            double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double) pos.getZ() + rand.nextDouble();

            worldIn.spawnParticle(Particles.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(Particles.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(Particles.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        double d0 = (double) pos.getX();
        double d1 = (double) pos.getY();
        double d2 = (double) pos.getZ();
        worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1f, 1f, false);
    }
}
