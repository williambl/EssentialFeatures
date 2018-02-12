package net.anti_quark.EssentialFeatures.common.entity;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.config.ModConfig;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.entity.passive.EntityVillager.EmeraldForItems;
import net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerMechanic {

	protected static final VillagerRegistry.VillagerProfession MECHANIC_PROFESSION = new VillagerRegistry.VillagerProfession("mechanic", "minecraft:textures/entity/villager/villager.png", "");
    protected static final VillagerRegistry.VillagerCareer MECHANIC_CAREER = new VillagerRegistry.VillagerCareer(MECHANIC_PROFESSION, "mechanic")
    		.addTrade(1, new EmeraldForItems(Items.REDSTONE, new PriceInfo(20, 1)))
    		.addTrade(2, new ListItemForEmeralds(new ItemStack(Blocks.REDSTONE_LAMP), new PriceInfo(1, 3)))
    		.addTrade(2, new ListItemForEmeralds(new ItemStack(ModBlocks.STAINED_LAMP), new PriceInfo(1, 5)));
        
    @Mod.EventBusSubscriber
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerSoundEvents(RegistryEvent.Register<VillagerProfession> event) {
			if (!ModConfig.villagers)
				return;

			event.getRegistry().registerAll(
					MECHANIC_PROFESSION
			);
		}
	}
}
