package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.common.block.BlockRedstoneRod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityRedstoneRod extends TileEntity implements ITickable {

    int tickCounter = 0;

    @Override
    public void update() {

        if (world.isRemote)
            return;

        tickCounter++;
        IBlockState blockstate = world.getBlockState(getPos());
        BlockRedstoneRod block = (BlockRedstoneRod) world.getBlockState(getPos()).getBlock();

        if (tickCounter > 10) {
            if (block.isPowered(blockstate))
                block.deactivate(world, pos, blockstate);
        }

        if (!world.isThundering())
            return;

        if (tickCounter < 200 || world.rand.nextDouble() < 0.9)
            return;

        tickCounter = 0;

        EntityLightningBolt bolt = new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), false);
        world.addWeatherEffect(bolt);
        block.redstoneEffects(world, pos);

        block.activate(world, pos, blockstate);
    }
}
