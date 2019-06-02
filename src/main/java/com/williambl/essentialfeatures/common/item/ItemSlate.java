package com.williambl.essentialfeatures.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

public class ItemSlate extends ItemBlock {

    public ItemSlate(Block block) {
        super(block, new Properties().group(ItemGroup.BUILDING_BLOCKS));
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {

        EntityPlayer player = context.getPlayer();
        EnumFacing facing = context.getFace();
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        ItemStack itemstack = context.getItem();

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack)) {
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();
            BlockPos blockpos = pos;

            if ((facing != EnumFacing.UP || block != this.getBlock()) && !block.isReplaceable(iblockstate, (BlockItemUseContext) context)) {
                blockpos = pos.offset(facing);
                iblockstate = world.getBlockState(blockpos);
                block = iblockstate.getBlock();
            }

            if (block == this.getBlock()) {
                int i = iblockstate.get(BlockSnowLayer.LAYERS);

                if (i < 8) {
                    IBlockState iblockstate1 = iblockstate.with(BlockSnowLayer.LAYERS, i + 1);
                    VoxelShape shape = iblockstate1.getCollisionShape(world, blockpos);

                    if (shape != VoxelShapes.empty() && world.checkNoEntityCollision(iblockstate1, blockpos) && world.setBlockState(blockpos, iblockstate1, 10)) {
                        SoundType soundtype = this.getBlock().getSoundType(iblockstate1, world, pos, player);
                        world.playSound(player, blockpos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                        if (player instanceof EntityPlayerMP) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, itemstack);
                        }

                        itemstack.shrink(1);
                        return EnumActionResult.SUCCESS;
                    }
                }
            }

            return super.onItemUse(context);
        } else {
            return EnumActionResult.FAIL;
        }
    }
}
