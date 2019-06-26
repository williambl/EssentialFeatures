package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.state.properties.DoubleBlockHalf;

import java.util.Random;

public class BlockEFDoor extends DoorBlock {

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

    public Item getItemDropped(BlockState state, Random rand, int fortune) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? Items.AIR : Item.getItemFromBlock(this);
    }

}
