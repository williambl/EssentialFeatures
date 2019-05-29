package com.williambl.essentialfeatures.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockDoor extends ItemBlock {

    public ItemBlockDoor(Block block)
    {
        super(block, new Properties().group(ItemGroup.REDSTONE));
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public EnumActionResult onItemUse(ItemUseContext context)
    {
        EnumFacing facing = context.getFace();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        EntityPlayer player = context.getPlayer();
        ItemStack itemstack = context.getItem();

        if (facing != EnumFacing.UP)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (!block.isReplaceable(iblockstate, (BlockItemUseContext) context))
            {
                pos = pos.offset(facing);
            }

            if (player.canPlayerEdit(pos, facing, itemstack) && this.getBlock().isValidPosition(iblockstate, world, pos))
            {
                EnumFacing enumfacing = EnumFacing.fromAngle((double)player.rotationYaw);
                int i = enumfacing.getXOffset();
                int j = enumfacing.getZOffset();
                boolean flag = i < 0 && context.getHitZ() < 0.5F || i > 0 && context.getHitZ() > 0.5F || j < 0 && context.getHitZ() > 0.5F || j > 0 && context.getHitZ() < 0.5F;
                placeDoor(world, pos, enumfacing, this.getBlock(), flag);
                SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }

    public static void placeDoor(World worldIn, BlockPos pos, EnumFacing facing, Block door, boolean isRightHinge)
    {
        BlockPos blockpos = pos.offset(facing.rotateY());
        BlockPos blockpos1 = pos.offset(facing.rotateYCCW());
        int i = (worldIn.getBlockState(blockpos1).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos1.up()).isNormalCube() ? 1 : 0);
        int j = (worldIn.getBlockState(blockpos).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos.up()).isNormalCube() ? 1 : 0);
        boolean flag = worldIn.getBlockState(blockpos1).getBlock() == door || worldIn.getBlockState(blockpos1.up()).getBlock() == door;
        boolean flag1 = worldIn.getBlockState(blockpos).getBlock() == door || worldIn.getBlockState(blockpos.up()).getBlock() == door;

        if ((!flag || flag1) && j <= i)
        {
            if (flag1 && !flag || j < i)
            {
                isRightHinge = false;
            }
        }
        else
        {
            isRightHinge = true;
        }

        BlockPos blockpos2 = pos.up();
        boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos2);
        IBlockState iblockstate = door.getDefaultState().with(BlockDoor.FACING, facing).with(BlockDoor.HINGE, isRightHinge ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT).with(BlockDoor.POWERED, Boolean.valueOf(flag2)).with(BlockDoor.OPEN, Boolean.valueOf(flag2));
        worldIn.setBlockState(pos, iblockstate.with(BlockDoor.HALF, DoubleBlockHalf.LOWER), 2);
        worldIn.setBlockState(blockpos2, iblockstate.with(BlockDoor.HALF, DoubleBlockHalf.UPPER), 2);
        worldIn.notifyNeighborsOfStateChange(pos, door);
        worldIn.notifyNeighborsOfStateChange(blockpos2, door);
    }

}
