package net.anti_quark.EssentialFeatures.common.tileentity;

import java.util.List;
import java.util.function.Consumer;

import net.anti_quark.EssentialFeatures.common.block.BlockViewedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class TileEntityViewedBlock extends TileEntity implements ITickable {
	
	int radius = 50;

	@Override
	public void update() {
		List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX()-radius,this.pos.getY()-radius,this.pos.getZ()-radius,this.pos.getX()+radius,this.pos.getY()+radius,this.pos.getZ()+radius));
		

		for (EntityPlayer player : players) 
		{
			if (checkIfLooking(player)) {
			System.out.println(player.getPosition().toString());
			}
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
