package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.common.tileentity.ViewedBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ViewedBlockBlock extends Block {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public ViewedBlockBlock(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
        this.setRegistryName(registryName);
        this.setDefaultState(this.getStateContainer().getBaseState().with(POWERED, Boolean.FALSE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ViewedBlockTileEntity();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(POWERED) ? 15 : 0;
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    public void activate(World worldIn, BlockPos pos, BlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.with(POWERED, Boolean.TRUE));
    }

    public void deactivate(World worldIn, BlockPos pos, BlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.with(POWERED, Boolean.FALSE));
    }

    public boolean isPowered(BlockState blockstate){
        return blockstate.get(POWERED);
    }
}
