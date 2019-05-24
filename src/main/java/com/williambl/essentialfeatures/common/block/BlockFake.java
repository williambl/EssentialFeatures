package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFake extends Block {

    IBlockState realBlockState;

    //This can be used if you want to place an unplaceable block
    public BlockFake(String registryName, ItemGroup tab, IBlockState realBlockState) {
        super(Material.AIR);
        this.setRegistryName(registryName);
        this.realBlockState = realBlockState;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return realBlockState;
    }
}
