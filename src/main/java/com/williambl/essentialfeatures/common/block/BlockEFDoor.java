package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

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

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
