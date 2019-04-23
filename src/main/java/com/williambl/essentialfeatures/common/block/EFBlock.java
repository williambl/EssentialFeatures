package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class EFBlock extends Block {

    public EFBlock(String registryName, Material material, SoundType soundType, float hardness, float resistance, int lightLevel) {
        super(Block.Properties.create(
                material).
                hardnessAndResistance(hardness, resistance).
                lightValue(lightLevel).
                sound(soundType));
        this.setRegistryName(registryName);
    }

    public EFBlock(String registryName, Material material, SoundType soundType, float hardness, float resistance) {
         super(Block.Properties.create(
                material).
                hardnessAndResistance(hardness, resistance).
                sound(soundType));
        this.setRegistryName(registryName);
    }
}
