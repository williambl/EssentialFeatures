package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;

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
    }
}
