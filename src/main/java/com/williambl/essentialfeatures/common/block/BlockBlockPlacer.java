package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BlockSourceImpl;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockBlockPlacer extends BlockDispenser {

    private final IBehaviorDispenseItem dropBehavior = new BehaviorPlaceBlock();

    protected BlockBlockPlacer(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH).with(TRIGGERED, Boolean.FALSE));
        this.setRegistryName(registryName);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityDispenser();
    }

    @Override
    protected void dispense(World worldIn, BlockPos pos) {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
        TileEntityDispenser tileentityblockplacer = blocksourceimpl.getBlockTileEntity();

        int i = tileentityblockplacer.getDispenseSlot();

        if (i < 0) {
            worldIn.playEvent(1001, pos, 0);
        } else {
            ItemStack itemstack = tileentityblockplacer.getStackInSlot(i);

            if (!itemstack.isEmpty()) {
                ItemStack itemstack1;

                itemstack1 = this.dropBehavior.dispense(blocksourceimpl, itemstack);

                tileentityblockplacer.setInventorySlotContents(i, itemstack1);
            }
        }
    }

    public class BehaviorPlaceBlock implements IBehaviorDispenseItem {
        @Override
        public ItemStack dispense(IBlockSource source, ItemStack stack) {
            Block block = Block.getBlockFromItem(stack.getItem());

            if (block == Blocks.AIR)
                return stack;

            EnumFacing facing = source.getBlockState().get(BlockDispenser.FACING);
            EnumFacing.Axis axis = facing.getAxis();

            BlockPos pos = source.getBlockPos().offset(facing);
            World world = source.getWorld();

            if (world.isAirBlock(pos)) {
                IBlockState state = block.getStateForPlacement(new BlockItemUseContext(world, null, stack, pos, facing, pos.getX(), pos.getY(), pos.getZ()));

                if (state != null) {
                    world.setBlockState(pos, state);
                    SoundType soundtype = block.getSoundType();
                    world.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    stack.shrink(1);
                }
            }

            return stack;
        }
    }

}
