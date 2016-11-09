package net.anti_quark.EssentialFeatures.common.tileentity;

import java.util.List;
import java.util.function.Consumer;

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
		
		IBlockState blockstate = worldObj.getBlockState(getPos());
		BlockViewedBlock block = (BlockViewedBlock) worldObj.getBlockState(getPos()).getBlock();
		wasLookingLastTime = block.isPowered(blockstate);
		BlockPos thisPos = getPos();

		boolean isNowLooking = false;
		List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX()-radius,this.pos.getY()-radius,this.pos.getZ()-radius,this.pos.getX()+radius,this.pos.getY()+radius,this.pos.getZ()+radius));
		

		for (EntityPlayer player : players) 
		{
			if (!isNowLooking) 
			{
			isNowLooking = checkIfLooking(player, thisPos);
			}
		}
		
		if (wasLookingLastTime != isNowLooking) 
		{
			if (isNowLooking) {block.activate(worldObj, thisPos, blockstate);} else {block.deactivate(worldObj, thisPos, blockstate);}
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
	
	
	//For some reason EntityPlayer.raytrace is Client Only, so here's a slightly modified version for use on servers
    public RayTraceResult rayTrace(EntityPlayer playerIn, double blockReachDistance, float partialTicks)
    {
        Vec3d vec3d = playerIn.getPositionEyes(partialTicks);
        Vec3d vec3d1 = playerIn.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * blockReachDistance, vec3d1.yCoord * blockReachDistance, vec3d1.zCoord * blockReachDistance);
        return this.worldObj.rayTraceBlocks(vec3d, vec3d2, false, false, true);
    }
}
