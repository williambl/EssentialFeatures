package com.williambl.essentialfeatures.common.tileentity;

import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityBlockPlacer extends TileEntityDispenser {
    //TODO: Make this actually work, instead of stacks being full of air...

    public TileEntityBlockPlacer() {
        super(ModTileEntities.BLOCK_PLACER);
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public ITextComponent getName() {
        ITextComponent itextcomponent = this.getCustomName();
        return itextcomponent != null ? itextcomponent : new TextComponentTranslation("container.block_placer");
    }
}