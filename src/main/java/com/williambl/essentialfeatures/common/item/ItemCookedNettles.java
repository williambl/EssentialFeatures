package com.williambl.essentialfeatures.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;

public class ItemCookedNettles extends ItemFood {

    public ItemCookedNettles(String registryName) {
        super(2, false);
        this.setCreativeTab(CreativeTabs.FOOD);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
