package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.item.crafting.LightningRecipe;
import com.williambl.essentialfeatures.common.item.crafting.ModCrafting;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.BlastingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Optional;

public class TileEntityRedstoneRod extends TileEntity implements ITickableTileEntity {

    int tickCounter = 0;

    public TileEntityRedstoneRod() {
        super(ModTileEntities.REDSTONE_ROD);
    }

    @Override
    public void tick() {

        if (world.isRemote)
            return;

        tickCounter++;

        BlockState blockstate = getBlockState();

        if (tickCounter > 10) {
            if (ModBlocks.REDSTONE_ROD.isPowered(blockstate))
                ModBlocks.REDSTONE_ROD.deactivate(world, pos, blockstate);
        }

        if (!world.isThundering())
            return;

        if (!world.canBlockSeeSky(pos))
            return;

        if (tickCounter < 200 || world.rand.nextDouble() < 0.999)
            return;

        makeLightning(getBlockState());
    }

    public void makeLightning(BlockState blockstate) {
        if (world.isRemote)
            return;

        tickCounter = 0;

        LightningBoltEntity bolt = new LightningBoltEntity(world, pos.getX(), pos.getY(), pos.getZ(), false);
        ((ServerWorld) world).addLightningBolt(bolt);
        ModBlocks.REDSTONE_ROD.redstoneEffects(world, pos);

        ModBlocks.REDSTONE_ROD.activate(world, pos, blockstate);
        smeltItems(world, pos);
    }

    private void smeltItems(World world, BlockPos pos) {
        List<ItemEntity> itemEntities = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(pos).grow(3.0));

        for (ItemEntity itemEntity : itemEntities) {
            Optional<LightningRecipe> optionalLightningRecipe = world.getRecipeManager().getRecipe(ModCrafting.LIGHTNING_SMELTING_TYPE, new Inventory(itemEntity.getItem()), world);
            Optional<BlastingRecipe> optionalBlastingRecipe = world.getRecipeManager().getRecipe(IRecipeType.BLASTING, new Inventory(itemEntity.getItem()), world);
            if (optionalLightningRecipe.isPresent() || optionalBlastingRecipe.isPresent()) {
                ItemStack result = optionalLightningRecipe.map(
                        lightningRecipe -> lightningRecipe.getRecipeOutput().copy())
                        .orElseGet(() -> optionalBlastingRecipe.get().getRecipeOutput().copy());
                result.setCount(result.getCount() * itemEntity.getItem().getCount());
                itemEntity.setItem(result);
                itemEntity.setInvulnerable(true);
            }
        }
    }
}
