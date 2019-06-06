package com.williambl.essentialfeatures.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

import java.util.Objects;
import java.util.Random;

public class CommonEventHandler {

    @SubscribeEvent
    public void OnPlayerRespawn(PlayerRespawnEvent e) {
        e.getPlayer().world.spawnParticle(Particles.EXPLOSION, e.getPlayer().posX, e.getPlayer().posY, e.getPlayer().posZ, 1.0D, 0.0D, 0.0D);
        e.getPlayer().world.playSound(null, e.getPlayer().posX, e.getPlayer().posY, e.getPlayer().posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 4.0F, (1.0F + (e.getPlayer().world.rand.nextFloat() - e.getPlayer().world.rand.nextFloat()) * 0.2F) * 0.7F);
    }

    @SubscribeEvent
    public void OnPlayerOpenChest(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Random rand = world.rand;
        TileEntity t = world.getTileEntity(pos);

        if (t instanceof TileEntityChest) {
            if (world.getFluidState(pos.add(0, 1, 0)).isTagged(FluidTags.WATER)) {
                for (int i = 0; i < world.rand.nextInt(50); i++) {
                    world.spawnParticle(Particles.BUBBLE_COLUMN_UP, pos.getX()+rand.nextFloat(), pos.getY()+0.6, pos.getZ()+rand.nextFloat(), 0, 0.5, 0);
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

        if (entity instanceof EntityWitch) {
            System.out.println("witch died! make effects!");
            Random rand = world.rand;
            for (int i = 0; i < 10; i++) {

                //Spawn a bat
                EntityBat bat = new EntityBat(world);
                bat.setPosition(
                        entity.posX + rand.nextDouble() - 0.5,
                        entity.posY + rand.nextDouble(),
                        entity.posZ + rand.nextDouble() - 0.5
                );

                world.spawnEntity(bat);
            }

            System.out.println(e.getSource().getDamageType());
            if (rand.nextDouble() < 0.05 && e.getSource().getDamageType().equals("player")) {
                EntityOcelot ocelot = new EntityOcelot(world);
                ocelot.setPosition(entity.posX, entity.posY, entity.posZ);

                ocelot.setTamedBy((EntityPlayer) Objects.requireNonNull(e.getSource().getTrueSource()));

                world.spawnEntity(ocelot);
            }
            //Play a sound

            world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.HOSTILE, 1f, 1f);
        }
    }
}
