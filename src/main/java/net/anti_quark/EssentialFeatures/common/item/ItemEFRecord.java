package net.anti_quark.EssentialFeatures.common.item;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEFRecord extends ItemRecord {

	private static final Map records = new HashMap();
	public final String recordName;

	public ItemEFRecord(String recordName, SoundEvent soundEvent)
	{
		super(recordName, soundEvent);
	 	this.recordName = recordName;
	 	this.maxStackSize = 1;
	 	records.put("records." + recordName, this);
	 	this.setRegistryName(recordName);
	 	GameRegistry.register(this);
	}
	 
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
}