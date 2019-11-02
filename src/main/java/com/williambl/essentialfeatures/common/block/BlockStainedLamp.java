package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockStainedLamp extends Block {

    public static final BooleanProperty LIT = RedstoneLampBlock.LIT;
    int colour;
    static final String[] names = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};

    public BlockStainedLamp(String registryName, int colourIn) {
        super(Properties.create(Material.GLASS).hardnessAndResistance(0.3f, 1.5f).sound(SoundType.GLASS));
        colour = colourIn;
        this.setRegistryName(registryName);
        this.setDefaultState(this.getStateContainer().getBaseState().with(LIT, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getLightValue(BlockState state) {
        return state.get(LIT) ? 15 : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(LIT, context.getWorld().isBlockPowered(context.getPos()));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
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
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (state.get(LIT) && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, state.cycle(LIT), 2);
            }

        }
    }

}
