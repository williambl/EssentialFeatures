package com.williambl.essentialfeatures.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;

public class ItemEFRecord extends ItemRecord {

    public ItemEFRecord(String recordName, SoundEvent soundIn) {
        super(recordName, soundIn);
        this.setRegistryName(recordName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.maxStackSize = 1;
        this.setCreativeTab(ItemGroup.MISC);
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}