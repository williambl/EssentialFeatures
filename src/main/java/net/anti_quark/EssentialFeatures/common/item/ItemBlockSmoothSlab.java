package net.anti_quark.EssentialFeatures.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockSmoothSlab extends ItemBlockWithSubtypes {
	
	private String[] subtypeNames;

	public ItemBlockSmoothSlab(Block block, boolean hasSubtypes, String[] names) {
		super(block, hasSubtypes, names);
		
        if (hasSubtypes)
        {
            this.setMaxDamage(0);
            this.setHasSubtypes(true);
            this.setSubtypeNames(names);
        }
	}
	
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.func_190926_b() && playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            return this.tryPlace(playerIn, itemstack, playerIn, pos.offset(facing), comparable) ? EnumActionResult.SUCCESS : super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

}
