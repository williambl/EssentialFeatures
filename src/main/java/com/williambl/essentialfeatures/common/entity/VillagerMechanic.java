package com.williambl.essentialfeatures.common.entity;

public class VillagerMechanic {

    //TODO: Get all this to actually *work*, I've never actually played 1.14 so I have no clue what all this new villager stuff is. Leaving it be for now.
/*
    protected static final VillagerProfession MECHANIC_PROFESSION = new VillagerProfession("mechanic", new PointOfInterestType("mechanic", ImmutableSet.of(Blocks.REDSTONE_BLOCK.getDefaultState()), 1, null, 1), ImmutableSet.of(), ImmutableSet.of()).setRegistryName("mechanic");
    protected static final VillagerCareer MECHANIC_CAREER = new VillagerRegistry.VillagerCareer(MECHANIC_PROFESSION, "mechanic")
            .addTrade(1, new EmeraldForItems(Items.REDSTONE, new PriceInfo(20, 1)))
            .addTrade(2, new ListItemForEmeralds(new ItemStack(Blocks.REDSTONE_LAMP), new PriceInfo(1, 3)))
            .addTrade(2, new ListItemForEmeralds(new ItemStack(ModBlocks.STAINED_LAMPS[4]), new PriceInfo(1, 5)));

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerVillagers(RegistryEvent.Register<VillagerProfession> event) {

            event.getRegistry().register(
                    MECHANIC_PROFESSION
            );
        }
    }
    */
}
