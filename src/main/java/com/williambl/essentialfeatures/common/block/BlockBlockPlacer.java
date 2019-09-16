package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.ProxyBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.DispenserTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockBlockPlacer extends DispenserBlock {

    private final IDispenseItemBehavior dropBehavior = new BehaviorPlaceBlock();

    protected BlockBlockPlacer(String registryName, Material material, float hardness, float resistance) {
        super(Properties.create(material).hardnessAndResistance(hardness, resistance));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(TRIGGERED, Boolean.FALSE));
        this.setRegistryName(registryName);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new DispenserTileEntity();
    }

    @Override
    protected void dispense(World worldIn, BlockPos pos) {
        ProxyBlockSource blocksourceimpl = new ProxyBlockSource(worldIn, pos);
        DispenserTileEntity tileentityblockplacer = blocksourceimpl.getBlockTileEntity();

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

    public class BehaviorPlaceBlock implements IDispenseItemBehavior {
        @Override
        public ItemStack dispense(IBlockSource source, ItemStack stack) {
            //TODO: Get this to work properly, it never actually did
            Block block = Block.getBlockFromItem(stack.getItem());

            if (block == Blocks.AIR)
                return stack;

            Direction facing = source.getBlockState().get(DispenserBlock.FACING);
            Direction.Axis axis = facing.getAxis();

            BlockPos pos = source.getBlockPos().offset(facing);
            World world = source.getWorld();

            if (world.isAirBlock(pos)) {
                BlockState state = block.getDefaultState()/*getStateForPlacement(new BlockItemUseContext(new ItemUseContext(world, null, stack, pos, facing, pos.getX(), pos.getY(), pos.getZ())))*/;

                if (state != null) {
                    world.setBlockState(pos, state);
                    SoundType soundtype = block.getSoundType(state);
                    world.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    stack.shrink(1);
                }
            }

            return stack;
        }
    }

}
