package net.anti_quark.EssentialFeatures.common.item;

import javax.annotation.Nullable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCereal extends ItemFood {

	public ItemCereal(String registryName, float saturation, int healAmount) {
		super(healAmount, saturation, false);
        this.setCreativeTab(CreativeTabs.FOOD);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());;
        GameRegistry.register(this);
    }
	
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }

}