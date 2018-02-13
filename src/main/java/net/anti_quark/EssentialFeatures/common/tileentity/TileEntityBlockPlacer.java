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
    private static final Random RNG = new Random();
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);

    public static void registerFixes(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityBlockPlacer.class, "Items"));
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.block_placer";
    }

    public String getGuiID()
    {
        return "minecraft:dispenser";
    }
}