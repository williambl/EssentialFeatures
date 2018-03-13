package com.williambl.essentialfeatures.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class CommonEventHandler {
	
	@SubscribeEvent
	public void OnPlayerRespawn (PlayerRespawnEvent e)
	{
		e.player.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, e.player.posX, e.player.posY, e.player.posZ, 1.0D, 0.0D, 0.0D);
		e.player.world.playSound(null, e.player.posX, e.player.posY, e.player.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 4.0F, (1.0F + (e.player.world.rand.nextFloat() - e.player.world.rand.nextFloat()) * 0.2F) * 0.7F);
	}

	@SubscribeEvent
	public void OnEntityDeath (LivingDeathEvent e) {
		Entity entity = e.getEntity();
		World world = entity.world;

		if (world.isRemote)
			return;

		if (entity instanceof EntityWitch) {
			System.out.println("witch died! make effects!");
		}
	}
}
