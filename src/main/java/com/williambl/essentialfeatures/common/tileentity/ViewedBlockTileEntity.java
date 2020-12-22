package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.common.block.ViewedBlockBlock;
import com.williambl.essentialfeatures.common.config.Config;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class ViewedBlockTileEntity extends TileEntity implements ITickableTileEntity {

    int radius = Config.viewedBlockRange;
    boolean wasLookingLastTime;

    int tickCounter = 0;

    public ViewedBlockTileEntity() {
        super(ModTileEntities.VIEWED_BLOCK);
    }

    @Override
    public void tick() {
        if (world.isRemote)
            return;

        tickCounter++;

        if (tickCounter != Config.viewedBlockDelay)
            return;
        tickCounter = 0;

        BlockState blockstate = world.getBlockState(getPos());
        ViewedBlockBlock block = (ViewedBlockBlock) world.getBlockState(getPos()).getBlock();
        wasLookingLastTime = block.isPowered(blockstate);
        BlockPos thisPos = getPos();

        boolean isNowLooking = false;
        List<PlayerEntity> players = world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(this.pos.getX() - radius, this.pos.getY() - radius, this.pos.getZ() - radius, this.pos.getX() + radius, this.pos.getY() + radius, this.pos.getZ() + radius));


        for (PlayerEntity player : players) {
            if (!isNowLooking) {
                isNowLooking = checkIfLooking(player, thisPos);
            }
        }

        if (wasLookingLastTime != isNowLooking) {
            if (isNowLooking) {
                block.activate(world, thisPos, blockstate);
            } else {
                block.deactivate(world, thisPos, blockstate);
            }
        }
    }

    public boolean checkIfLooking(PlayerEntity player, BlockPos thisPos) {
        BlockRayTraceResult rayResult = rayTrace(player, 50, 1F);
        return thisPos.equals(rayResult.getPos());
    }

    /*
    For some reason EntityPlayer.raytrace and EntityPlayer.getPositionEyes
    are Client Only, so here are two slightly modified functions for use on servers.
    */
    public BlockRayTraceResult rayTrace(PlayerEntity playerIn, double blockReachDistance, float partialTicks) {
        Vector3d vec3d = getPositionEyes(playerIn, partialTicks);
        Vector3d vec3d1 = playerIn.getLook(partialTicks);
        Vector3d vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return this.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, playerIn));
    }

    public Vector3d getPositionEyes(PlayerEntity playerIn, float partialTicks) {
        if (partialTicks == 1.0F) {
            return new Vector3d(playerIn.getPosX(), playerIn.getPosY() + (double) playerIn.getEyeHeight(), playerIn.getPosZ());
        } else {
            double d0 = playerIn.prevPosX + (playerIn.getPosX() - playerIn.prevPosX) * (double) partialTicks;
            double d1 = playerIn.prevPosY + (playerIn.getPosY() - playerIn.prevPosY) * (double) partialTicks + (double) playerIn.getEyeHeight();
            double d2 = playerIn.prevPosZ + (playerIn.getPosZ() - playerIn.prevPosZ) * (double) partialTicks;
            return new Vector3d(d0, d1, d2);
        }
    }
}
