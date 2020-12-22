package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class EFBlock extends Block {

    public EFBlock(String registryName, Material material, SoundType soundType, float hardness, float resistance, int lightLevel) {
        super(Block.Properties.create(
                material).
                hardnessAndResistance(hardness, resistance).
                setLightLevel((blockState) -> lightLevel).
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
