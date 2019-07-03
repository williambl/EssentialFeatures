package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCryingObsidian extends Block {

    public BlockCryingObsidian(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance).lightValue(2));
        this.setRegistryName(registryName);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.getPlayer() != null) {
            PlayerEntity player = context.getPlayer();
            player.setSpawnDimenion(player.dimension);
            player.setSpawnPoint(player.getPosition(), true, player.dimension);

            particleExplosion(context.getWorld(), context.getPos());
        }
        return super.getStateForPlacement(context);
    }


    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction enumfacing = Direction.random(rand);

        if (enumfacing != Direction.UP && !worldIn.getBlockState(pos.offset(enumfacing)).isFullCube()) {
            double d0 = (double) pos.getX();
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ();

            if (enumfacing == Direction.DOWN) {
                d1 = d1 - 0.05D;
                d0 += rand.nextDouble();
                d2 += rand.nextDouble();
            } else {
                d1 = d1 + rand.nextDouble() * 0.8D;

                if (enumfacing.getAxis() == Direction.Axis.X) {
                    d2 += rand.nextDouble();

                    if (enumfacing == Direction.EAST) {
                        ++d0;
                    } else {
                        d0 += 0.05D;
                    }
                } else {
                    d0 += rand.nextDouble();

                    if (enumfacing == Direction.SOUTH) {
                        ++d2;
                    } else {
                        d2 += 0.05D;
                    }
                }
            }

            worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    public void particleExplosion(World worldIn, BlockPos pos) {
        if (worldIn.isRemote) {
            for (int i = 0; i < 10; i++) {
                double d0 = (double) pos.getX() + worldIn.rand.nextDouble();
                double d1 = (double) pos.getY() + worldIn.rand.nextDouble() * 0.5D + 0.5D;
                double d2 = (double) pos.getZ() + worldIn.rand.nextDouble();

                worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 0.25f, 1f, false);
            }
        }
    }
}
