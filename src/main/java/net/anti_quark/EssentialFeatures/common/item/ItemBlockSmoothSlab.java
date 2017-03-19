package net.anti_quark.EssentialFeatures.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBlockSmoothSlab extends ItemBlock {
	
	BlockStoneSlab.EnumType blockType;
	BlockStoneSlabNew.EnumType blockType2;
		
	public ItemBlockSmoothSlab(Block block, String name, BlockStoneSlab.EnumType type) {
		super(block);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setRegistryName(name);
		this.blockType = type;
		this.setUnlocalizedName(this.getRegistryName().toString());
        GameRegistry.register(this);
	}
	
	public ItemBlockSmoothSlab(Block block, String name, BlockStoneSlabNew.EnumType type) {
		super(block);
		this.setRegistryName(name);
		this.blockType2 = type;
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setUnlocalizedName(name);
        GameRegistry.register(this);
	}
	
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(facing);
        }

        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.func_190926_b() && playerIn.canPlayerEdit(pos, facing, itemstack) && worldIn.func_190527_a(this.block, pos, false, facing, (Entity)null))
        {
            IBlockState iblockstate1 = 
            		blockType != null ?
            		this.block.getBlockState().getBaseState().withProperty(BlockStoneSlab.SEAMLESS, true).withProperty(BlockStoneSlab.VARIANT, blockType)
            		: this.block.getBlockState().getBaseState().withProperty(BlockStoneSlabNew.SEAMLESS, true).withProperty(BlockStoneSlabNew.VARIANT, blockType2);

            if (placeBlockAt(itemstack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1))
            {
                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
                playerIn.playSound(soundtype.getPlaceSound(), (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.func_190918_g(1);
            }

            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
    
}
