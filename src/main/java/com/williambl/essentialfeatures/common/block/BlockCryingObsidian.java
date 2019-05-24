package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCryingObsidian extends Block {

    public BlockCryingObsidian(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance).lightValue(2));
        this.setRegistryName(registryName);
    }

    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.getPlayer() != null) {
            EntityPlayer player = context.getPlayer();
            player.setSpawnDimenion(player.dimension);
            player.setSpawnPoint(player.getPosition(), true, player.dimension);

            particleExplosion(context.getWorld(), context.getPos());
        }
        return super.getStateForPlacement(context);
    }


    @Override
    public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        EnumFacing enumfacing = EnumFacing.random(rand);

        if (enumfacing != EnumFacing.UP && !worldIn.getBlockState(pos.offset(enumfacing)).isFullCube()) {
            double d0 = (double) pos.getX();
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ();

            if (enumfacing == EnumFacing.DOWN) {
                d1 = d1 - 0.05D;
                d0 += rand.nextDouble();
                d2 += rand.nextDouble();
            } else {
                d1 = d1 + rand.nextDouble() * 0.8D;

                if (enumfacing.getAxis() == EnumFacing.Axis.X) {
                    d2 += rand.nextDouble();

                    if (enumfacing == EnumFacing.EAST) {
                        ++d0;
                    } else {
                        d0 += 0.05D;
                    }
                } else {
                    d0 += rand.nextDouble();

                    if (enumfacing == EnumFacing.SOUTH) {
                        ++d2;
                    } else {
                        d2 += 0.05D;
                    }
                }
            }

            worldIn.spawnParticle(Particles.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public void particleExplosion(World worldIn, BlockPos pos) {
        if (worldIn.isRemote) {
            for (int i = 0; i < 10; i++) {
                double d0 = (double) pos.getX() + worldIn.rand.nextDouble();
                double d1 = (double) pos.getY() + worldIn.rand.nextDouble() * 0.5D + 0.5D;
                double d2 = (double) pos.getZ() + worldIn.rand.nextDouble();

                worldIn.spawnParticle(Particles.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.spawnParticle(Particles.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 0.25f, 1f, false);
            }
        }
    }
}
