package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;

public class BlockFake extends Block {

    IBlockState realBlockState;

    //This can be used if you want to place an unplaceable block
    public BlockFake(String registryName, ItemGroup tab, IBlockState realBlockState) {
        super(Properties.create(Material.AIR));
        this.setRegistryName(registryName);
        this.realBlockState = realBlockState;
    }

    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        return realBlockState;
    }
}
