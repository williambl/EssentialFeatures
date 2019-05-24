package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBlockBreaker extends BlockDirectional {

    public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");

    protected BlockBlockBreaker(String registryName, Material material, float hardness, float resistance) {
        super(material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.FALSE));
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos) && state.getValue(TRIGGERED) == Boolean.valueOf(false)) {
                destroy(pos, state, worldIn);
                worldIn.setBlockState(pos, state.cycleProperty(TRIGGERED), 6);
            } else if (!worldIn.isBlockPowered(pos) && !state.getValue(TRIGGERED) == Boolean.FALSE) {
                worldIn.setBlockState(pos, state.cycleProperty(TRIGGERED), 6);
            }
        }
    }

    public void destroy(BlockPos pos, IBlockState state, World worldIn) {
        BlockPos offsetPos = pos.offset(state.getValue(FACING));
        Block offsetBlock = worldIn.getBlockState(offsetPos).getBlock();

        worldIn.destroyBlock(offsetPos, true);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
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
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, TRIGGERED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(TRIGGERED, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getIndex();

        if (state.getValue(TRIGGERED)) {
            i |= 8;
        }

        return i;
    }

    @Nullable
    public static EnumFacing getFacing(int meta) {
        int i = meta & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

}
