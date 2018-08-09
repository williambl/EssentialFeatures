package com.williambl.essentialfeatures.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class EFItem extends Item {

    /*
     * This can be used if you just want an item that does nothing special,
     * instead of creating a new class for it.
     */
    public EFItem(String registryName, CreativeTabs tab) {
        super();
        this.setCreativeTab(tab);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}