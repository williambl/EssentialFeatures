package com.williambl.essentialfeatures.common.item.crafting;

import com.williambl.essentialfeatures.EssentialFeatures;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(EssentialFeatures.MODID)
public class ModCrafting {

    @ObjectHolder("crafting_special_portable_jukebox_load")
    public static IRecipeSerializer PORTABLE_JUKEBOX_LOAD;

    @ObjectHolder("lightning_smelting")
    public static IRecipeSerializer LIGHTNING_SMELTING;

    public static IRecipeType<LightningRecipe> LIGHTNING_SMELTING_TYPE;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
            final IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();

            registry.registerAll(
                    new SpecialRecipeSerializer<>(PortableJukeboxLoadRecipe::new).setRegistryName("crafting_special_portable_jukebox_load"),
                    new LightningRecipeSerializer(LightningRecipe::new).setRegistryName("lightning_smelting")
            );
        }

        public static void registerRecipeTypes() {
            LIGHTNING_SMELTING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(EssentialFeatures.MODID, "lightning_smelting"), new IRecipeType<LightningRecipe>() {
                public String toString() {
                    return "lightning_smelting";
                }
            });
        }
    }
}
