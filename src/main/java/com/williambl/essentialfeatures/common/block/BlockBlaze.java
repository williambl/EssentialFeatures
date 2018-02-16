package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.IFuelHandler;

import java.util.Random;

public class BlockBlaze extends EFBlock implements IFuelHandler {

    public BlockBlaze(String registryName) {
        super(registryName, Material.IRON, CreativeTabs.BUILDING_BLOCKS, SoundType.METAL, 5f, 15f, 1f);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == Item.getItemFromBlock(ModBlocks.BLAZE_BLOCK))
            return 4800;
        return 0;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
            entityIn.setFire(5);
        }
        particleExplosion(worldIn, pos, worldIn.rand);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < 3; ++i)
        {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double)pos.getZ() + rand.nextDouble();

            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        if (rand.nextDouble() < 0.01 || isTouchingWater(worldIn, pos)) {
            particleExplosion(worldIn, pos, rand);
        }
    }

    private boolean isTouchingWater(World worldIn, BlockPos pos) {
        for (EnumFacing facing : EnumFacing.VALUES)
            if (worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.WATER)
                return true;
        return false;
    }

    private void particleExplosion(World worldIn, BlockPos pos, Random rand) {
        for (int i = 0; i < 5; ++i) {
            double d0 = (double) pos.getX() + rand.nextDouble();
            double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double) pos.getZ() + rand.nextDouble();

            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        double d0 = (double) pos.getX();
        double d1 = (double) pos.getY();
        double d2 = (double) pos.getZ();
        worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1f, 1f, false);
    }
}
