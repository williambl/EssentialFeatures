package com.williambl.essentialfeatures.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

public class ItemEFRecord extends MusicDiscItem {

    //TODO: Fix this, no sound
    public ItemEFRecord(String registryName, int comparatorValueIn, SoundEvent soundIn) {
        super(comparatorValueIn, soundIn, new Properties().group(ItemGroup.MISC).maxStackSize(1));
        this.setRegistryName(registryName);
    }

}