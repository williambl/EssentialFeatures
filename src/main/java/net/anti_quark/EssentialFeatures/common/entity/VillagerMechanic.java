package net.anti_quark.EssentialFeatures.common.entity;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.entity.passive.EntityVillager.EmeraldForItems;
import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class VillagerMechanic {

	protected static final VillagerRegistry.VillagerProfession PROFESSION = new VillagerRegistry.VillagerProfession("mechanic", "minecraft:textures/entity/villager/villager.png", "");
    protected static final VillagerRegistry.VillagerCareer CAREER = new VillagerRegistry.VillagerCareer(PROFESSION, "mechanic")
    		.addTrade(1, new EmeraldForItems(Items.REDSTONE, new PriceInfo(20, 1)))
    		.addTrade(2, new ListItemForEmeralds(new ItemStack(Blocks.REDSTONE_LAMP), new PriceInfo(1, 3)))
    		.addTrade(2, new ListItemForEmeralds(new ItemStack(ModBlocks.stainedLamp), new PriceInfo(1, 5)));
        
    public static void addVillagers ()
    {
    	VillagerRegistry.instance().register(PROFESSION);
    }
}
