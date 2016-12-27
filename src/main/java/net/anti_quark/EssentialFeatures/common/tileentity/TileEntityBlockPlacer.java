package net.anti_quark.EssentialFeatures.common.tileentity;

import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class TileEntityBlockPlacer extends TileEntityDispenser
{
    public static void registerFixesDropper(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityBlockPlacer.class, new String[] {"Items"}));
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.field_190577_o : "container.block_placer";
    }

    public String getGuiID()
    {
        return "minecraft:dropper";
    }
}