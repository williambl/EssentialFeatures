package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;

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

}
