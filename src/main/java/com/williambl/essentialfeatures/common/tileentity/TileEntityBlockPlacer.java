package com.williambl.essentialfeatures.common.tileentity;

import net.minecraft.tileentity.DispenserTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TileEntityBlockPlacer extends DispenserTileEntity {

    public TileEntityBlockPlacer() {
        super(ModTileEntities.BLOCK_PLACER);
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public ITextComponent getName() {
        ITextComponent itextcomponent = this.getCustomName();
        return itextcomponent != null ? itextcomponent : new TranslationTextComponent("container.block_placer");
    }
}