package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class SlateBlock extends Block implements IWaterLoggable {

   public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 8);
   protected static final VoxelShape[] SHAPES = new VoxelShape[]{VoxelShapes.empty(), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

   public SlateBlock(String registryName, Material material, float hardness, float resistance) {
      super(Properties.create(material).hardnessAndResistance(hardness, resistance));
      this.setRegistryName(registryName);
      this.setDefaultState(getStateContainer().getBaseState().with(LAYERS, 1).with(BlockStateProperties.WATERLOGGED, false));
   }

   @Override
   @SuppressWarnings("deprecation")
   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
      switch(type) {
         case LAND:
            return state.get(LAYERS) < 5;
         case WATER:
            return false;
         case AIR:
            return false;
         default:
            return false;
      }
   }

   @Override
   @SuppressWarnings("deprecation")
   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return SHAPES[state.get(LAYERS)];
   }

   @Override
   @SuppressWarnings("deprecation")
   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return SHAPES[state.get(LAYERS) - 1];
   }

   @Override
   @SuppressWarnings("deprecation")
   public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
      int i = state.get(LAYERS);
      if (useContext.getItem().getItem() == this.asItem() && i < 8) {
         if (useContext.replacingClickedOnBlock()) {
            return useContext.getFace() == Direction.UP;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   @Nullable
   @Override
   public BlockState getStateForPlacement(BlockItemUseContext context) {
      BlockState iblockstate = context.getWorld().getBlockState(context.getPos());
      if (iblockstate.getBlock() == this) {
         int i = iblockstate.get(LAYERS);
         return iblockstate.with(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
      } else {
         return super.getStateForPlacement(context);
      }
   }

   @Override
   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(LAYERS, BlockStateProperties.WATERLOGGED);
   }

   @Override
   public IFluidState getFluidState(BlockState state) {
      return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
   }

   @Override
   public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
      if (!state.get(BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
         if (!worldIn.isRemote()) {
            worldIn.setBlockState(pos, state.with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
         }

         return true;
      } else {
         return false;
      }
   }
}
