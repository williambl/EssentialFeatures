package com.williambl.essentialfeatures.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class RedstoneRodSwordItem extends SwordItem {

    public RedstoneRodSwordItem(String registryName, IItemTier material) {
        super(material, 7, -2.7f, new Properties().group(ItemGroup.COMBAT));
        this.setRegistryName(registryName);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        if (target.world.canBlockSeeSky(new BlockPos(target.getPosX(), target.getPosY(), target.getPosZ()))) {
            stack.damageItem(2, attacker, (entity) -> entity.sendBreakAnimation(attacker.getActiveHand()));
            LightningBoltEntity bolt = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, target.world);
            bolt.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
            bolt.setEffectOnly(false);
            bolt.setCaster(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
            target.world.addEntity(bolt);
        }
        stack.damageItem(1, attacker, (entity) -> entity.sendBreakAnimation(attacker.getActiveHand()));
        return true;
    }
}
