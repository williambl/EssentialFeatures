package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Random;

public class BlockEFDoor extends BlockDoor {

    private int closeSound;
    private int openSound;

    public BlockEFDoor(String registryName, Material materialIn, float hardness, int closeSound, int openSound) {
        super(materialIn);
        this.setRegistryName(registryName);
        this.setHardness(hardness);
        this.closeSound = closeSound;
        this.openSound = openSound;
    }

    private int getCloseSound() {
        return this.closeSound;
    }

    private int getOpenSound() {
        return this.openSound;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : Item.getItemFromBlock(this);
    }

}
