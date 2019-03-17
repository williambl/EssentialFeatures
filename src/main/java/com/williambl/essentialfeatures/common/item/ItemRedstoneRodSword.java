package com.williambl.essentialfeatures.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRedstoneRodSword extends ItemSword {

    public ItemRedstoneRodSword(String registryName, ToolMaterial material) {
        super(material);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
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

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
