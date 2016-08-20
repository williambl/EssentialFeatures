package net.anti_quark.EssentialFeatures.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class CommonEventHandler {
	
	@SubscribeEvent
	public void OnPlayerRespawn (PlayerRespawnEvent e)
	{
		e.player.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, e.player.posX, e.player.posY, e.player.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
		e.player.worldObj.playSound((EntityPlayer)null, e.player.posX, e.player.posY, e.player.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 4.0F, (1.0F + (e.player.worldObj.rand.nextFloat() - e.player.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
	}

}
