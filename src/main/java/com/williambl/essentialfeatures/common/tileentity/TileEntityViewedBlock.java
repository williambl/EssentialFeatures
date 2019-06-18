package com.williambl.essentialfeatures.common.tileentity;

import com.williambl.essentialfeatures.common.block.BlockViewedBlock;
import com.williambl.essentialfeatures.common.config.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.*;

import java.util.List;

public class TileEntityViewedBlock extends TileEntity implements ITickable {

    int radius = ModConfig.viewedBlockRange;
    boolean wasLookingLastTime;

    int tickCounter = 0;

    public TileEntityViewedBlock() {
        super(ModTileEntities.VIEWED_BLOCK);
    }

    @Override
    public void tick() {

        if (world.isRemote)
            return;

        tickCounter++;

        if (tickCounter != ModConfig.viewedBlockDelay)
            return;
        tickCounter = 0;

        IBlockState blockstate = world.getBlockState(getPos());
        BlockViewedBlock block = (BlockViewedBlock) world.getBlockState(getPos()).getBlock();
        wasLookingLastTime = block.isPowered(blockstate);
        BlockPos thisPos = getPos();

        boolean isNowLooking = false;
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX() - radius, this.pos.getY() - radius, this.pos.getZ() - radius, this.pos.getX() + radius, this.pos.getY() + radius, this.pos.getZ() + radius));


        for (EntityPlayer player : players) {
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

    public boolean checkIfLooking(EntityPlayer player, BlockPos thisPos) {
        //float playerPitch = player.rotationPitch;
        //float playerYaw = player.rotationYawHead;

        RayTraceResult rayPos = rayTrace(player, 50, 1F);
        BlockPos pos = rayPos.getBlockPos();

        return thisPos.equals(pos);

    }

    /*
    For some reason EntityPlayer.raytrace and EntityPlayer.getPositionEyes
    are Client Only, so here are two slightly modified functions for use on servers.
    */
    public RayTraceResult rayTrace(EntityPlayer playerIn, double blockReachDistance, float partialTicks) {
        Vec3d vec3d = getPositionEyes(playerIn, partialTicks);
        Vec3d vec3d1 = playerIn.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return this.world.rayTraceBlocks(vec3d, vec3d2, RayTraceFluidMode.NEVER, false, true);
    }

    public Vec3d getPositionEyes(EntityPlayer playerIn, float partialTicks) {
        if (partialTicks == 1.0F) {
            return new Vec3d(playerIn.posX, playerIn.posY + (double) playerIn.getEyeHeight(), playerIn.posZ);
        } else {
            double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * (double) partialTicks;
            double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * (double) partialTicks + (double) playerIn.getEyeHeight();
            double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * (double) partialTicks;
            return new Vec3d(d0, d1, d2);
        }
    }
}
