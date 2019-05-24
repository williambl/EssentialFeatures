package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.common.tileentity.TileEntityViewedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockViewedBlock extends Block {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public BlockViewedBlock(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
        this.setRegistryName(registryName);
        this.setDefaultState(this.getStateContainer().getBaseState().with(POWERED, Boolean.FALSE));
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
        return new TileEntityViewedBlock();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getWeakPower(IBlockState blockState, IBlockReader blockAccess, BlockPos pos, EnumFacing side) {
        return blockState.get(POWERED) ? 15 : 0;
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(POWERED);
    }

    public void activate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.with(POWERED, Boolean.TRUE));
    }

    public void deactivate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.with(POWERED, Boolean.FALSE));
    }

    public boolean isPowered(IBlockState blockstate){
        return blockstate.get(POWERED);
    }
}
