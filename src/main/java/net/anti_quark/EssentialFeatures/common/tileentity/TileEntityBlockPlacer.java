package net.anti_quark.EssentialFeatures.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

import java.util.Random;

public class TileEntityBlockPlacer extends TileEntityDispenser
{
    //TODO: Make this actually work, instead of stacks being full of air...
    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.block_placer";
    }

}