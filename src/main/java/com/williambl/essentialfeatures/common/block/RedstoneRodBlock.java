package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.common.tileentity.TileEntityRedstoneRod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class RedstoneRodBlock extends EFBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.values());

    protected static final VoxelShape END_ROD_VERTICAL_AABB = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape END_ROD_NS_AABB = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape END_ROD_EW_AABB = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

    public RedstoneRodBlock(String registryName, Material material, SoundType soundType, float hardness, float resistance, int lightlevel) {
        super(registryName, material, soundType, hardness, resistance, lightlevel);
        this.setDefaultState(this.getStateContainer().getBaseState().with(POWERED, Boolean.FALSE).with(FACING, Direction.UP));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityRedstoneRod();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot)
    {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, ISelectionContext context)
    {
        switch (state.get(FACING).getAxis())
        {
            case X:
            default:
                return END_ROD_EW_AABB;
            case Z:
                return END_ROD_NS_AABB;
            case Y:
                return END_ROD_VERTICAL_AABB;
        }
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState iblockstate = context.getWorld().getBlockState(context.getPos().offset(context.getFace().getOpposite()));

        if (iblockstate.getBlock() == ModBlocks.REDSTONE_ROD)
        {
            Direction enumfacing = iblockstate.get(FACING);

            if (enumfacing == context.getFace())
            {
                return this.getDefaultState().with(FACING, context.getFace().getOpposite());
            }
        }

        return this.getDefaultState().with(FACING, context.getFace());
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        Direction enumfacing = stateIn.get(FACING);
        double d0 = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d2 = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
        double d3 = 0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F;

        if (rand.nextInt(5) == 0)
        {
            worldIn.addParticle(ParticleTypes.END_ROD, d0 + (double) enumfacing.getXOffset() * d3, d1 + (double) enumfacing.getYOffset() * d3, d2 + (double) enumfacing.getZOffset() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
        }
    }

    @Override
    @SuppressWarnings("deprecated")
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecated")
    public int getWeakPower(BlockState blockState, IBlockReader reader, BlockPos pos, Direction side) {
        return blockState.get(POWERED) ? 15 : 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
        builder.add(FACING);
    }

    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public void activate(World worldIn, BlockPos pos, BlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.with(POWERED, Boolean.TRUE));
    }

    public void deactivate(World worldIn, BlockPos pos, BlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.with(POWERED, Boolean.FALSE));
    }

    public boolean isPowered(BlockState blockstate) {
        return blockstate.get(POWERED);
    }

    public void redstoneEffects(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Direction enumfacing = state.get(FACING);
        double d0 = (double)pos.getX() + 0.55D - (double)(world.rand.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() + 0.55D - (double)(world.rand.nextFloat() * 0.1F);
        double d2 = (double)pos.getZ() + 0.55D - (double)(world.rand.nextFloat() * 0.1F);
        double d3 = 0.4F - (world.rand.nextFloat() + world.rand.nextFloat()) * 0.4F;

        for (int i = 0; i < world.rand.nextInt(10); i++)
        {
            world.addParticle(RedstoneParticleData.REDSTONE_DUST, d0 + (double) enumfacing.getXOffset() * d3, d1 + (double) enumfacing.getYOffset() * d3, d2 + (double) enumfacing.getZOffset() * d3, world.rand.nextGaussian() * 0.01D, world.rand.nextGaussian() * 0.01D, world.rand.nextGaussian() * 0.01D);
            world.addParticle(ParticleTypes.END_ROD, d0 + (double) enumfacing.getXOffset() * d3, d1 + (double) enumfacing.getYOffset() * d3, d2 + (double) enumfacing.getZOffset() * d3, world.rand.nextGaussian() * 0.005D, world.rand.nextGaussian() * 0.005D, world.rand.nextGaussian() * 0.005D);
        }
    }
}
