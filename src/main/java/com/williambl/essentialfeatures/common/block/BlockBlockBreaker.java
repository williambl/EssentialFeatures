package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockBlockBreaker extends BlockDirectional {

    public static final BooleanProperty TRIGGERED = BooleanProperty.create("triggered");

    protected BlockBlockBreaker(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(TRIGGERED, Boolean.FALSE));
        this.setRegistryName(registryName);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos) && state.get(TRIGGERED) == Boolean.valueOf(false)) {
                destroy(pos, state, worldIn);
                worldIn.setBlockState(pos, state.cycle(TRIGGERED), 6);
            } else if (!worldIn.isBlockPowered(pos) && !state.get(TRIGGERED) == Boolean.FALSE) {
                worldIn.setBlockState(pos, state.cycle(TRIGGERED), 6);
            }
        }
    }

    public void destroy(BlockPos pos, IBlockState state, World worldIn) {
        BlockPos offsetPos = pos.offset(state.get(FACING));
        Block offsetBlock = worldIn.getBlockState(offsetPos).getBlock();

        worldIn.destroyBlock(offsetPos, true);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.with(FACING, getFacingFromEntity(pos, placer)), 2);
    }

    public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase entityIn) {
        if (MathHelper.abs((float) entityIn.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) entityIn.posZ - (float) pos.getZ()) < 2.0F) {
            double d0 = entityIn.posY + (double) entityIn.getEyeHeight();

            if (d0 - (double) pos.getY() > 2.0D) {
                return EnumFacing.UP;
            }

            if ((double) pos.getY() - d0 > 0.0D) {
                return EnumFacing.DOWN;
            }
        }

        return entityIn.getHorizontalFacing().getOpposite();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(FACING, TRIGGERED);
    }

}
