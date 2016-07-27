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

public class TileEntityViewedBlock extends TileEntity implements ITickable {
	
	int radius = 50;
	boolean wasLookingLastTime;

	@Override
	public void update() {
		
		IBlockState blockstate = worldObj.getBlockState(getPos());
		BlockViewedBlock block = (BlockViewedBlock) worldObj.getBlockState(getPos()).getBlock();
		wasLookingLastTime = block.isPowered(blockstate);

		boolean isNowLooking = false;
		List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX()-radius,this.pos.getY()-radius,this.pos.getZ()-radius,this.pos.getX()+radius,this.pos.getY()+radius,this.pos.getZ()+radius));
		

		for (EntityPlayer player : players) 
		{
			if (!isNowLooking) 
			{
			isNowLooking = checkIfLooking(player);
			}
		}
		
		if (wasLookingLastTime != isNowLooking) 
		{
			if (isNowLooking) {block.activate(worldObj, getPos(), blockstate);} else {block.deactivate(worldObj, getPos(), blockstate);}
		}
	}
	
	public boolean checkIfLooking(EntityPlayer player) {
		//float playerPitch = player.rotationPitch;
		//float playerYaw = player.rotationYawHead;
		
		RayTraceResult rayPos = player.rayTrace(50, 1F);
		BlockPos pos = rayPos.getBlockPos();
		
		if (worldObj.getBlockState(pos).getBlock() instanceof BlockViewedBlock)
        {
           return true;
        }
		
		return false;
		
	}

}
