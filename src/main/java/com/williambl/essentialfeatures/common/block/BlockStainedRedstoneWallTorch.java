package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockStainedRedstoneWallTorch extends RedstoneWallTorchBlock {

    static final String[] names = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};
    int colour;

    public BlockStainedRedstoneWallTorch(String registryName, int colourIn) {
        super(Properties.create(Material.REDSTONE_LIGHT));
        colour = colourIn;
        this.setRegistryName(registryName);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(BlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock((Block) ModBlocks.STAINED_REDSTONE_TORCHES[colour].getRight());
    }
}
