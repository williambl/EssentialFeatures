package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSlate extends Block {

   public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 1, 8);
   protected static final VoxelShape[] SHAPES = new VoxelShape[]{VoxelShapes.empty(), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

   public BlockSlate(String registryName, Material material, float hardness, float resistance) {
      super(Properties.create(material).hardnessAndResistance(hardness, resistance));
      this.setRegistryName(registryName);
   }

   @Override
   @SuppressWarnings("deprecation")
   public boolean allowsMovement(IBlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
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
   public boolean isFullCube(IBlockState state) {
      return state.get(LAYERS) == 8;
   }

   @Override
   @SuppressWarnings("deprecation")
   public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
   }

   @Override
   @SuppressWarnings("deprecation")
   public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
      return SHAPES[state.get(LAYERS)];
   }

   @Override
   @SuppressWarnings("deprecation")
   public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
      return SHAPES[state.get(LAYERS) - 1];
   }

   @Override
   @SuppressWarnings("deprecation")
   public int quantityDropped(IBlockState state, Random random) {
      return state.get(LAYERS) + 1;
   }

   @Override
   @SuppressWarnings("deprecation")
   public boolean isReplaceable(IBlockState state, BlockItemUseContext useContext) {
      int i = state.get(LAYERS);
      if (useContext.getItem().getItem() == this.asItem() && i < 8) {
         if (useContext.replacingClickedOnBlock()) {
            return useContext.getFace() == EnumFacing.UP;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   @Nullable
   @Override
   public IBlockState getStateForPlacement(BlockItemUseContext context) {
      IBlockState iblockstate = context.getWorld().getBlockState(context.getPos());
      if (iblockstate.getBlock() == this) {
         int i = iblockstate.get(LAYERS);
         return iblockstate.with(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
      } else {
         return super.getStateForPlacement(context);
      }
   }

   @Override
   protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
      builder.add(LAYERS);
   }

}
