package com.williambl.essentialfeatures.common.tileentity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LightningForgeTileEntity extends AbstractFurnaceTileEntity {
    public LightningForgeTileEntity() {
        super(ModTileEntities.LIGHTNING_FORGE, IRecipeType.BLASTING);
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.getItem() == Items.REDSTONE;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.lightning_forge");
    }

    @Override
    protected int getBurnTime(ItemStack stack) {
        return super.getBurnTime(stack) == 0 ? 0 : 1;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new LightningForgeContainer(id, player, this, this.furnaceData);
    }

    public static class LightningForgeContainer extends AbstractFurnaceContainer {
        public LightningForgeContainer(int id, PlayerInventory playerInv) {
            super(ModTileEntities.LIGHTNING_FORGE_CONTAINER, IRecipeType.BLASTING, id, playerInv);
        }

        public LightningForgeContainer(int id, PlayerInventory playerInv, IInventory furnaceInv, IIntArray p_i50098_4_) {
            super(ModTileEntities.LIGHTNING_FORGE_CONTAINER, IRecipeType.BLASTING, id, playerInv, furnaceInv, p_i50098_4_);
        }

        @Override
        protected boolean isFuel(ItemStack stack) {
            return LightningForgeTileEntity.isFuel(stack);
        }
    }
}
