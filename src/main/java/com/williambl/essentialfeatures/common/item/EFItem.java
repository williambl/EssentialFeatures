package com.williambl.essentialfeatures.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

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