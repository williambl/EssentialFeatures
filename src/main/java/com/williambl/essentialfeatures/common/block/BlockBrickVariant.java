package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockBrickVariant extends Block {

    static final String[] names = new String[]{"cream_bricks", "dirty_bricks", "long_bricks", "blue_bricks", "mixed_bricks"};
    public static final PropertyInteger variant = PropertyInteger.create("variant", 0, 4);

    public BlockBrickVariant(String registryName, Material material, float hardness, float resistance) {
        super(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(variant, 0));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, variant);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(variant, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(variant);
    }

    @Override
    public void getSubBlocks(ItemGroup tab, NonNullList<ItemStack> list) {
        for (Integer integer : variant.getAllowedValues()) {
            list.add(new ItemStack(this, 1, integer));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(variant);
    }

}
