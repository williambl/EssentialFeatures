package net.anti_quark.EssentialFeatures.common.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBlockBreaker extends BlockDirectional {
	
    public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");

	protected BlockBlockBreaker(String registryName, Material material, float hardness, float resistance) {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.valueOf(false)));
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
	}
	
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	if (!worldIn.isRemote)
    	{
    		if (worldIn.isBlockPowered(pos) && state.getValue(TRIGGERED) == Boolean.valueOf(false))
    		{
    			destroy(pos, state, worldIn);
    			worldIn.setBlockState(pos, state.cycleProperty(TRIGGERED), 6);
    		}
    		else if (!worldIn.isBlockPowered(pos) && !state.getValue(TRIGGERED) == Boolean.valueOf(false))
    		{
    			worldIn.setBlockState(pos, state.cycleProperty(TRIGGERED), 6);
    		}
    	}
    }
    
    public void destroy(BlockPos pos, IBlockState state, World worldIn)
    {
		BlockPos offsetPos = pos.offset(state.getValue(FACING));
		Block offsetBlock = worldIn.getBlockState(offsetPos).getBlock();
		
		worldIn.destroyBlock(offsetPos, true);
    }
    
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
    }
    
    public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase entityIn)
    {
        if (MathHelper.abs((float)entityIn.posX - (float)pos.getX()) < 2.0F && MathHelper.abs((float)entityIn.posZ - (float)pos.getZ()) < 2.0F)
        {
            double d0 = entityIn.posY + (double)entityIn.getEyeHeight();

            if (d0 - (double)pos.getY() > 2.0D)
            {
                return EnumFacing.UP;
            }

            if ((double)pos.getY() - d0 > 0.0D)
            {
                return EnumFacing.DOWN;
            }
        }

        return entityIn.getHorizontalFacing().getOpposite();
    }
    
    
    @Override
    protected BlockStateContainer createBlockState()
	{
    	return new BlockStateContainer(this, new IProperty[] {FACING, TRIGGERED});
	}
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(TRIGGERED, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(TRIGGERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }
    
    @Nullable
    public static EnumFacing getFacing(int meta)
    {
        int i = meta & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }

}
