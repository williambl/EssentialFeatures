package net.anti_quark.EssentialFeatures.common.tileentity;

import java.util.List;
import net.anti_quark.EssentialFeatures.common.block.BlockViewedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class TileEntityViewedBlock extends TileEntity implements ITickable {
	
	int radius = 50;
	boolean wasLookingLastTime;

	@Override
	public void update() {
		
		IBlockState blockstate = getWorld().getBlockState(getPos());
		BlockViewedBlock block = (BlockViewedBlock) getWorld().getBlockState(getPos()).getBlock();
		wasLookingLastTime = block.isPowered(blockstate);
		BlockPos thisPos = getPos();

		boolean isNowLooking = false;
		List<EntityPlayer> players = getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX()-radius,this.pos.getY()-radius,this.pos.getZ()-radius,this.pos.getX()+radius,this.pos.getY()+radius,this.pos.getZ()+radius));
		

		for (EntityPlayer player : players) 
		{
			if (!isNowLooking) 
			{
			isNowLooking = checkIfLooking(player, thisPos);
			}
		}
		
		if (wasLookingLastTime != isNowLooking) 
		{
			if (isNowLooking) {block.activate(getWorld(), thisPos, blockstate);} else {block.deactivate(getWorld(), thisPos, blockstate);}
		}
	}
	
	public boolean checkIfLooking(EntityPlayer player, BlockPos thisPos) {
		//float playerPitch = player.rotationPitch;
		//float playerYaw = player.rotationYawHead;
		
		RayTraceResult rayPos = rayTrace(player, 50, 1F);
		BlockPos pos = rayPos.getBlockPos();
		
		if (thisPos.equals(pos))
        {
            return true;
        }
		
		return false;
		
	}
	
	/*
	For some reason EntityPlayer.raytrace and EntityPlayer.getPositionEyes
	are Client Only, so here are two slightly modified functions for use on servers.
	*/
    public RayTraceResult rayTrace(EntityPlayer playerIn, double blockReachDistance, float partialTicks)
    {
        Vec3d vec3d = getPositionEyes(playerIn, partialTicks);
        Vec3d vec3d1 = playerIn.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * blockReachDistance, vec3d1.yCoord * blockReachDistance, vec3d1.zCoord * blockReachDistance);
        return this.getWorld().rayTraceBlocks(vec3d, vec3d2, false, false, true);
    }
    
    public Vec3d getPositionEyes(EntityPlayer playerIn, float partialTicks)
    {
        if (partialTicks == 1.0F)
        {
            return new Vec3d(playerIn.posX, playerIn.posY + (double)playerIn.getEyeHeight(), playerIn.posZ);
        }
        else
        {
            double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * (double)partialTicks;
            double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * (double)partialTicks + (double)playerIn.getEyeHeight();
            double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * (double)partialTicks;
            return new Vec3d(d0, d1, d2);
        }
    }
}
