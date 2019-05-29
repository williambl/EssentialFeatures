package com.williambl.essentialfeatures.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;

public class ItemRedstoneRodSword extends ItemSword {

    public ItemRedstoneRodSword(String registryName, IItemTier material) {
        super(material, 6, 1f, new Properties().group(ItemGroup.COMBAT));
        this.setRegistryName(registryName);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if (target.world.canBlockSeeSky(new BlockPos(target.posX, target.posY, target.posZ))) {
            stack.damageItem(5, attacker);
            EntityLightningBolt bolt = new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, false);
            target.world.addWeatherEffect(bolt);
        }
        stack.damageItem(1, attacker);
        return true;
    }
}
