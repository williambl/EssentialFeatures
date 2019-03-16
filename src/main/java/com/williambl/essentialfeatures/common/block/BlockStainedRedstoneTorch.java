package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Random;

public class BlockStainedRedstoneTorch extends BlockRedstoneTorch {

    static final String[] names = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};
    public final boolean isOn;
    public int colour;

    public BlockStainedRedstoneTorch(String registryName, boolean isOn, int colour) {
        super(isOn);
        this.isOn = isOn;
        this.colour = colour;
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(CreativeTabs.REDSTONE);
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.STAINED_REDSTONE_TORCHES[colour]);
    }
}
