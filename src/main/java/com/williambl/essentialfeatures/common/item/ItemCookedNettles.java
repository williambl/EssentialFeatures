package com.williambl.essentialfeatures.common.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;

public class ItemCookedNettles extends ItemFood {

    public ItemCookedNettles(String registryName) {
        super(2, 2, false, new Properties().group(ItemGroup.FOOD));
        this.setRegistryName(registryName);
    }
}
