package com.williambl.essentialfeatures.common;

import com.williambl.essentialfeatures.common.config.Config;
import com.williambl.essentialfeatures.common.item.ModItems;
import com.williambl.essentialfeatures.common.networking.ModPackets;
import com.williambl.essentialfeatures.common.networking.PortableJukeboxMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Objects;
import java.util.Random;

public class CommonEventHandler {

    @SubscribeEvent
    public void onPlayerDropItem(ItemTossEvent event) {
        ItemStack stack = event.getEntityItem().getItem();

        if (stack.getItem() == ModItems.PORTABLE_JUKEBOX) {
            PlayerEntity player = event.getPlayer();
            if (!player.world.isRemote) {
                ModPackets.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PortableJukeboxMessage(false, player.getUniqueID(), ItemStack.read(stack.getOrCreateChildTag("Disc")).getItem().getRegistryName()));
            }
        }
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
            Random rand = world.rand;
            if (Config.witchBats) {
                for (int i = 0; i < 10; i++) {

                    //Spawn a bat
                    BatEntity bat = new BatEntity(EntityType.BAT, world);
                    bat.setPosition(
                            entity.getPosX() + rand.nextDouble() - 0.5,
                            entity.getPosY() + rand.nextDouble(),
                            entity.getPosZ() + rand.nextDouble() - 0.5
                    );

                    world.addEntity(bat);
                }
            }

            if (rand.nextDouble() < Config.witchCatChance && e.getSource().getDamageType().equals("player")) {
                CatEntity cat = new CatEntity(EntityType.CAT, world);
                cat.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());

                cat.setTamedBy((PlayerEntity) Objects.requireNonNull(e.getSource().getTrueSource()));
                cat.setSitting(true);

                world.addEntity(cat);
            }
            world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.HOSTILE, 1f, 1f);
        }
    }
}
