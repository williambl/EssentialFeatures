package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockEFDoor extends BlockDoor {

    private int closeSound;
    private int openSound;

    public BlockEFDoor(String registryName, Material materialIn, int closeSound, int openSound) {
        super(materialIn);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.closeSound = closeSound;
        this.openSound = openSound;
    }

    private int getCloseSound() {
        return this.closeSound;
    }

    private int getOpenSound() {
        return this.openSound;
    }
}
