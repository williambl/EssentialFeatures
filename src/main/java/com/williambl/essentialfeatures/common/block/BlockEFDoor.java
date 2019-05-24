package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.state.properties.DoubleBlockHalf;

import java.util.Random;

public class BlockEFDoor extends BlockDoor {

    private int closeSound;
    private int openSound;

    public BlockEFDoor(String registryName, Material materialIn, float hardness, int closeSound, int openSound) {
        super(Properties.create(materialIn).hardnessAndResistance(hardness));
        this.setRegistryName(registryName);
        this.closeSound = closeSound;
        this.openSound = openSound;
    }

    private int getCloseSound() {
        return this.closeSound;
    }

    private int getOpenSound() {
        return this.openSound;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? Items.AIR : Item.getItemFromBlock(this);
    }

}
