package com.williambl.essentialfeatures.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class BlockCryingObsidian extends Block {

	public BlockCryingObsidian(String registryName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setLightLevel(0.1F);
	}
	
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    		if(placer instanceof EntityPlayer) 
    		{
    			((EntityPlayer) placer).setSpawnChunk(placer.getPosition(), true, worldIn.provider.getDimension());
    		
    			particleExplosion(worldIn, pos);
    		}
    		return this.getStateFromMeta(meta);
    }
    
    
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	EnumFacing enumfacing = EnumFacing.random(rand);

        if (enumfacing != EnumFacing.UP && !worldIn.getBlockState(pos.offset(enumfacing)).isFullCube())
        {
            double d0 = (double)pos.getX();
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ();

            if (enumfacing == EnumFacing.DOWN)
            {
                d1 = d1 - 0.05D;
                d0 += rand.nextDouble();
                d2 += rand.nextDouble();
            }
            else
            {
                d1 = d1 + rand.nextDouble() * 0.8D;

                if (enumfacing.getAxis() == EnumFacing.Axis.X)
                {
                    d2 += rand.nextDouble();

                    if (enumfacing == EnumFacing.EAST)
                    {
                        ++d0;
                    }
                    else
                    {
                        d0 += 0.05D;
                    }
                }
                else
                {
                    d0 += rand.nextDouble();

                    if (enumfacing == EnumFacing.SOUTH)
                    {
                        ++d2;
                    }
                    else
                    {
                        d2 += 0.05D;
                    }
                }
            }
            
    	worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    
    public void particleExplosion (World worldIn, BlockPos pos) 
    {
    	if (worldIn.isRemote)
    	{
    		double d0 = (double)pos.getX();
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ();
            
    		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    		worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 1.0D, 0.0D, 0.0D);
    	}
    }
}
