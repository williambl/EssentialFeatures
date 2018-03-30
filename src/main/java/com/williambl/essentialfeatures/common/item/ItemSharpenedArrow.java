package com.williambl.essentialfeatures.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemArrow;
import net.minecraftforge.client.model.ModelLoader;

public class ItemSharpenedArrow extends ItemArrow {

    public ItemSharpenedArrow(String registryName) {
		super();
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
