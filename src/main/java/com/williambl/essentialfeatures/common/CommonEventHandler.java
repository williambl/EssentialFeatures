package com.williambl.essentialfeatures.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class CommonEventHandler {

    @SubscribeEvent
    public void OnPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
        e.getPlayer().world.addParticle(ParticleTypes.EXPLOSION, e.getPlayer().posX, e.getPlayer().posY, e.getPlayer().posZ, 1.0D, 0.0D, 0.0D);
        e.getPlayer().world.playSound(null, e.getPlayer().posX, e.getPlayer().posY, e.getPlayer().posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 4.0F, (1.0F + (e.getPlayer().world.rand.nextFloat() - e.getPlayer().world.rand.nextFloat()) * 0.2F) * 0.7F);
    }

    @SubscribeEvent
    public void OnPlayerOpenChest(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Random rand = world.rand;
        TileEntity t = world.getTileEntity(pos);

        if (t instanceof ChestTileEntity) {
            if (world.getFluidState(pos.add(0, 1, 0)).isTagged(FluidTags.WATER)) {
                for (int i = 0; i < world.rand.nextInt(50); i++) {
                    world.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + rand.nextFloat(), pos.getY() + 0.6, pos.getZ() + rand.nextFloat(), 0, 0.5, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public void OnEntityDeath(LivingDeathEvent e) {
        Entity entity = e.getEntity();
        World world = entity.world;

        if (world.isRemote)
            return;

        if (entity instanceof WitchEntity) {
            System.out.println("witch died! make effects!");
            Random rand = world.rand;
            for (int i = 0; i < 10; i++) {

                //Spawn a bat
                BatEntity bat = new BatEntity(EntityType.BAT, world);
                bat.setPosition(
                        entity.posX + rand.nextDouble() - 0.5,
                        entity.posY + rand.nextDouble(),
                        entity.posZ + rand.nextDouble() - 0.5
                );

                world.addEntity(bat);
            }

            System.out.println(e.getSource().getDamageType());
            if (rand.nextDouble() < 0.05 && e.getSource().getDamageType().equals("player")) {
                OcelotEntity ocelot = new OcelotEntity(EntityType.OCELOT, world);
                ocelot.setPosition(entity.posX, entity.posY, entity.posZ);

                //TODO: Get this to work, the method is gone, might need an AT
                //ocelot.setTamed((PlayerEntity) Objects.requireNonNull(e.getSource().getTrueSource()));

                world.addEntity(ocelot);
            }
            //Play a sound

            world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.HOSTILE, 1f, 1f);
        }
    }
}
