package com.williambl.essentialfeatures.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;

public class ItemRedstoneRodSword extends SwordItem {

    public ItemRedstoneRodSword(String registryName, IItemTier material) {
        super(material, 6, 1f, new Properties().group(ItemGroup.COMBAT));
        this.setRegistryName(registryName);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        if (target.world.canBlockSeeSky(new BlockPos(target.posX, target.posY, target.posZ))) {
            stack.damageItem(5, attacker, (entity) -> entity.sendBreakAnimation(attacker.getActiveHand()));
            LightningBoltEntity bolt = new LightningBoltEntity(target.world, target.posX, target.posY, target.posZ, false);
            ((ServerWorld) target.world).addLightningBolt(bolt);
        }
        stack.damageItem(1, attacker, (entity) -> entity.sendBreakAnimation(attacker.getActiveHand()));
        return true;
    }
}
