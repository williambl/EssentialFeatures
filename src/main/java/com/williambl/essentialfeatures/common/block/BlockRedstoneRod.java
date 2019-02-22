package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.common.tileentity.TileEntityRedstoneRod;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRedstoneRod extends EFBlock implements ITileEntityProvider {

    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockRedstoneRod(String registryName, Material material, CreativeTabs tab, SoundType soundType, float hardness, float resistance) {
        super(registryName, material, tab, soundType, hardness, resistance);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));
        this.hasTileEntity = true;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRedstoneRod();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(POWERED, (meta & 1) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POWERED) ? 1 : 0;
    }

    public void activate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.withProperty(POWERED, Boolean.TRUE));
    }

    public void deactivate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.withProperty(POWERED, Boolean.FALSE));
    }

    public boolean isPowered(IBlockState blockstate) {
        return blockstate.getValue(POWERED);
    }
}
