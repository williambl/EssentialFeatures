package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.common.block.BlockRedstoneRod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ServerWorld;

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
        BlockState blockstate = world.getBlockState(getPos());
        BlockRedstoneRod block = (BlockRedstoneRod) world.getBlockState(getPos()).getBlock();

        if (tickCounter > 10) {
            if (block.isPowered(blockstate))
                block.deactivate(world, pos, blockstate);
        }

        if (!world.isThundering())
            return;

        if (!world.canBlockSeeSky(pos))
            return;

        if (tickCounter < 200 || world.rand.nextDouble() < 0.999)
            return;

        tickCounter = 0;

        LightningBoltEntity bolt = new LightningBoltEntity(world, pos.getX(), pos.getY(), pos.getZ(), false);
        ((ServerWorld) world).addLightningBolt(bolt);
        block.redstoneEffects(world, pos);

        block.activate(world, pos, blockstate);
    }
}
