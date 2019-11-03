package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockBreakerBlock extends DirectionalBlock {

    public static final BooleanProperty TRIGGERED = BooleanProperty.create("triggered");

    protected BlockBreakerBlock(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(TRIGGERED, Boolean.FALSE));
        this.setRegistryName(registryName);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos)) {
                destroy(pos, state, worldIn);
                worldIn.setBlockState(pos, state.cycle(TRIGGERED), 6);
            } else if (!worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, state.cycle(TRIGGERED), 6);
            }
        }
    }

    public void destroy(BlockPos pos, BlockState state, World worldIn) {
        BlockPos offsetPos = pos.offset(state.get(FACING));
        worldIn.destroyBlock(offsetPos, true);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.with(FACING, getFacingFromEntity(pos, placer)), 2);
    }

    public static Direction getFacingFromEntity(BlockPos pos, LivingEntity entityIn) {
        if (MathHelper.abs((float) entityIn.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) entityIn.posZ - (float) pos.getZ()) < 2.0F) {
            double d0 = entityIn.posY + (double) entityIn.getEyeHeight();

            if (d0 - (double) pos.getY() > 2.0D) {
                return Direction.UP;
            }

            if ((double) pos.getY() - d0 > 0.0D) {
                return Direction.DOWN;
            }
        }

        return entityIn.getHorizontalFacing().getOpposite();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TRIGGERED);
    }

}
