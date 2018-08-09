package com.williambl.essentialfeatures.common.tileentity;

import net.minecraft.tileentity.TileEntityDispenser;

public class TileEntityBlockPlacer extends TileEntityDispenser {
    //TODO: Make this actually work, instead of stacks being full of air...

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.block_placer";
    }

}