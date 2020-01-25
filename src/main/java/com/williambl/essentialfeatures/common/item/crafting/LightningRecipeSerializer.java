package com.williambl.essentialfeatures.common.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class LightningRecipeSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<com.williambl.essentialfeatures.common.item.crafting.LightningRecipe> {
    private final ILightningRecipeFactory factory;

    public LightningRecipeSerializer(ILightningRecipeFactory factory) {
        this.factory = factory;
    }

    public LightningRecipe read(@Nonnull ResourceLocation recipeName, @Nonnull JsonObject recipeJson) {
        String s = JSONUtils.getString(recipeJson, "group", "");
        JsonElement jsonelement = JSONUtils.isJsonArray(recipeJson, "ingredient") ? JSONUtils.getJsonArray(recipeJson, "ingredient") : JSONUtils.getJsonObject(recipeJson, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(jsonelement);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!recipeJson.has("result"))
            throw new JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (recipeJson.get("result").isJsonObject())
            itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(recipeJson, "result"));
        else {
            String s1 = JSONUtils.getString(recipeJson, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            if (!ForgeRegistries.ITEMS.containsKey(resourcelocation)) throw new IllegalStateException("Item: " + s1 + " does not exist");
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }
        return this.factory.create(recipeName, s, ingredient, itemstack);
    }

    public LightningRecipe read(ResourceLocation recipeName, PacketBuffer recipeBuf) {
        String s = recipeBuf.readString(32767);
        Ingredient ingredient = Ingredient.read(recipeBuf);
        ItemStack itemstack = recipeBuf.readItemStack();
        return this.factory.create(recipeName, s, ingredient, itemstack);
    }

    public void write(PacketBuffer recipeBuf, LightningRecipe recipe) {
        recipeBuf.writeString(recipe.getGroup());
        recipe.getIngredients().get(0).write(recipeBuf);
        recipeBuf.writeItemStack(recipe.getRecipeOutput());
        recipeBuf.writeFloat(recipe.getExperience());
    }

    interface ILightningRecipeFactory {
        LightningRecipe create(ResourceLocation p_create_1_, String p_create_2_, Ingredient p_create_3_, ItemStack p_create_4_);
    }
}
