package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Random;

public class BlockStainedLamp extends Block {

    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);
    private final boolean isOn;
    static final String[] names = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};

    public BlockStainedLamp(String registryName, Material material, float hardness, float resistance, boolean isOn) {
        super(material);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setSoundType(SoundType.GLASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
        this.isOn = isOn;
        if (isOn) {
            this.setLightLevel(1.0F);
            this.setCreativeTab(null);
        }
    }

    public void initModel() {

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        if (!isOn) {
            for (int x = 1; x < 16; x++) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), x, new ModelResourceLocation(getRegistryName() + names[x], "inventory"));
            }
        }


    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, COLOR);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(COLOR).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
            list.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ModBlocks.LIT_STAINED_LAMP.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ModBlocks.LIT_STAINED_LAMP.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.scheduleUpdate(pos, this, 4);
            } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ModBlocks.LIT_STAINED_LAMP.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (this.isOn && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, ModBlocks.STAINED_LAMP.getDefaultState().withProperty(COLOR, state.getValue(COLOR)), 2);
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(COLOR).getMetadata();
    }
}
