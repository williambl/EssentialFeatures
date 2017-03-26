package net.anti_quark.EssentialFeatures.common.item;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EFItem extends Item {

	/*
	 * This can be used if you just want an item that does nothing special,
	 * instead of creating a new class for it.
	*/
	public EFItem(String registryName, CreativeTabs tab) {
		super();
        this.setCreativeTab(tab);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());;
        GameRegistry.register(this);
    }
	
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}