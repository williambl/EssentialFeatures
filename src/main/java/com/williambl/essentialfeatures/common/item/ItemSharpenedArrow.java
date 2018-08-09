package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.common.entity.EntitySharpenedArrow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ItemSharpenedArrow extends ItemArrow {

    public ItemSharpenedArrow(String registryName) {
        super();
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        EntitySharpenedArrow arrow = new EntitySharpenedArrow(worldIn, shooter);
        return arrow;
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
