package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLamp;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockStainedLamp extends Block {

    public static final BooleanProperty LIT = BlockRedstoneLamp.LIT;
    static final String[] names = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};

    public BlockStainedLamp(String registryName, Material material, float hardness, float resistance, boolean isOn) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance).sound(SoundType.GLASS));
        this.setRegistryName(registryName);
        this.setDefaultState(this.getStateContainer().getBaseState().with(LIT, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getLightValue(IBlockState state) {
        return state.get(LIT) ? super.getLightValue(state) : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(LIT);
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(LIT, context.getWorld().isBlockPowered(context.getPos()));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            boolean flag = state.get(LIT);
            if (flag != worldIn.isBlockPowered(pos)) {
                if (flag) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                } else {
                    worldIn.setBlockState(pos, state.cycle(LIT), 2);
                }
            }

        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (state.get(LIT) && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, state.cycle(LIT), 2);
            }

        }
    }

}
