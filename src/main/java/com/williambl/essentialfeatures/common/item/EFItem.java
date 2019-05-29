package com.williambl.essentialfeatures.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class EFItem extends Item {

    /*
     * This can be used if you just want an item that does nothing special,
     * instead of creating a new class for it.
     */
    public EFItem(String registryName, ItemGroup itemGroup) {
        super(new Properties().group(itemGroup));
        this.setRegistryName(registryName);
    }

}