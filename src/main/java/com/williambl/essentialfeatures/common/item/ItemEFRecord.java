package com.williambl.essentialfeatures.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class ItemEFRecord extends MusicDiscItem {

    public ItemEFRecord(String registryName, int comparatorValueIn, SoundEvent soundIn) {
        super(comparatorValueIn, soundIn, new Properties().group(ItemGroup.MISC).maxStackSize(1));
        this.setRegistryName(registryName);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return super.getDisplayName(stack).applyTextStyle(TextFormatting.AQUA);
    }
}