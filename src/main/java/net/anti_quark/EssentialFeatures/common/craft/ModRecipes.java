package net.anti_quark.EssentialFeatures.common.craft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;
import net.anti_quark.EssentialFeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {


	public static void addRecipes () 
	{
		setupDir();
		
		addShapedRecipe(new ItemStack(ModBlocks.VIEWED_BLOCK), new Object[] { "qrq", "r*r", "qrq", 'q', Items.QUARTZ, 'r', Items.REDSTONE, '*', Items.ENDER_EYE });
		
		GameRegistry.addSmelting(Blocks.GLOWSTONE, new ItemStack(ModBlocks.SMOOTH_GLOWSTONE), 1.0F);
		addShapedRecipe(new ItemStack(ModBlocks.POLISHED_GLOWSTONE), new Object[] { "ss", "ss", 's', ModBlocks.SMOOTH_GLOWSTONE});
		
		addShapedRecipe(new ItemStack(ModBlocks.SNOW_BRICK), new Object[] { "ss", "ss", 's', Blocks.SNOW});
		
		addShapedRecipe(new ItemStack(ModItems.CEREAL), new Object[] {"w", "m", "b", 'w', Items.WHEAT, 'm', Items.MILK_BUCKET, 'b', Items.BOWL});
		addShapedRecipe(new ItemStack(ModItems.IRON_CEREAL), new Object[] {"iii", "ici", "iii", 'i', Items.IRON_INGOT, 'c', ModItems.CEREAL});
		
		addShapedRecipe(new ItemStack(ModBlocks.BLOCK_BREAKER), new Object[] {"cpc", "crc", "crc", 'c', Blocks.COBBLESTONE, 'p', Items.DIAMOND_PICKAXE, 'r', Items.REDSTONE});
		addShapedRecipe(new ItemStack(ModBlocks.BLOCK_PLACER), new Object[] {"ccc", "cpc", "crc", 'c', Blocks.COBBLESTONE, 'p', Blocks.PISTON, 'r', Items.REDSTONE});
		
		addShapelessRecipe(new ItemStack(Blocks.DOUBLE_STONE_SLAB, 2, 8), new Object[] {new ItemStack(Blocks.STONE_SLAB, 2, 0)});
		addShapelessRecipe(new ItemStack(Blocks.DOUBLE_STONE_SLAB, 2, 9), new Object[] {new ItemStack(Blocks.STONE_SLAB, 2, 1)});
		addShapelessRecipe(new ItemStack(Blocks.DOUBLE_STONE_SLAB2, 2, 8), new Object[] {new ItemStack(Blocks.STONE_SLAB2, 2, 0)});
		
		addShapelessRecipe(new ItemStack(ModItems.LONDON_CLAY, 2), new Object[]{new ItemStack(Items.CLAY_BALL, 2), new ItemStack(Blocks.SAND), new ItemStack(Items.GUNPOWDER)});
		addShapelessRecipe(new ItemStack(ModItems.SAND_CLAY_MIXTURE, 1, 0), new Object[] {new ItemStack(Items.CLAY_BALL), new ItemStack(Blocks.SAND)});
		
		GameRegistry.addSmelting(ModItems.LONDON_CLAY, new ItemStack(ModItems.LONDON_BRICK), 1.0F);
		GameRegistry.addSmelting(ModItems.SAND_CLAY_MIXTURE, new ItemStack(ModItems.CREAM_BRICK), 1.0F);
		
		addShapedRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 0), new Object[] {"bb", "bb", 'b', ModItems.CREAM_BRICK});
		addShapedRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 1), new Object[] {"bb", "bb", 'b', ModItems.LONDON_BRICK});
		addShapelessRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 2, 2), new Object[] {Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK, Items.BRICK});
		addShapedRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 3), new Object[] {"bl", "lb", 'b', Items.BRICK, 'l', new ItemStack(Items.DYE, 1, 4)});
		addShapelessRecipe(new ItemStack(ModBlocks.BRICK_VARIANT, 1, 4), new Object[] {new ItemStack(Items.BRICK), new ItemStack(Items.NETHERBRICK), new ItemStack(ModItems.LONDON_BRICK), new ItemStack(ModItems.CREAM_BRICK)});
		
		addShapedRecipe(new ItemStack(ModBlocks.CRYING_OBSIDIAN), new Object[] {" l ", "lol", " l ", 'l', new ItemStack(Items.DYE, 1, 4), 'o', Blocks.OBSIDIAN});
		addShapedRecipe(new ItemStack(ModBlocks.SPIKE_BLOCK, 8), new Object[] {"i i", "iii", "iii", 'i', Items.IRON_INGOT});
		
		addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 0), new Object[] {" s ", "s s", " s ", 's', Blocks.STONE});
		addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 1), new Object[] {" s ", "s s", " s ", 's', new ItemStack(Blocks.STONE, 1, 1)});
		addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 2), new Object[] {" s ", "s s", " s ", 's', new ItemStack(Blocks.STONE, 1, 2)});
		addShapedRecipe(new ItemStack(ModBlocks.DECORATIVE_STONE, 4, 3), new Object[] {" s ", "s s", " s ", 's', new ItemStack(Blocks.STONE, 1, 3)});
		generateConstants();
	}
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static File RECIPE_DIR = null;
	private static final Set<String> USED_OD_NAMES = new TreeSet<>();

	private static void setupDir() {
		if (RECIPE_DIR == null) {
			RECIPE_DIR = new File("../recipes/");
		}

		if (!RECIPE_DIR.exists()) {
			RECIPE_DIR.mkdir();
		}
	}

	private static void addShapedRecipe(ItemStack result, Object... components) {
		setupDir();

		// GameRegistry.addShapedRecipe(result, components);

		Map<String, Object> json = new HashMap<>();

		List<String> pattern = new ArrayList<>();
		int i = 0;
		while (i < components.length && components[i] instanceof String) {
			pattern.add((String) components[i]);
			i++;
		}
		json.put("pattern", pattern);

		boolean isOreDict = false;
		Map<String, Map<String, Object>> key = new HashMap<>();
		Character curKey = null;
		for (; i < components.length; i++) {
			Object o = components[i];
			if (o instanceof Character) {
				if (curKey != null)
					throw new IllegalArgumentException("Provided two char keys in a row");
				curKey = (Character) o;
			} else {
				if (curKey == null)
					throw new IllegalArgumentException("Providing object without a char key");
				if (o instanceof String)
					isOreDict = true;
				key.put(Character.toString(curKey), serializeItem(o));
				curKey = null;
			}
		}
		json.put("key", key);
		json.put("type", isOreDict ? "forge:ore_shaped" : "minecraft:crafting_shaped");
		json.put("result", serializeItem(result));

		// names the json the same name as the output's registry name
		// repeatedly adds _alt if a file already exists
		// janky I know but it works
		String suffix = result.getItem().getHasSubtypes() ? "_" + result.getItemDamage() : "";
		File f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

		while (f.exists()) {
			suffix += "_alt";
			f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
		}

		try (FileWriter w = new FileWriter(f)) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addShapelessRecipe(ItemStack result, Object... components)
	{
		setupDir();

		// addShapelessRecipe(result, components);

		Map<String, Object> json = new HashMap<>();

		boolean isOreDict = false;
		List<Map<String, Object>> ingredients = new ArrayList<>();
		for (Object o : components) {
			if (o instanceof String)
				isOreDict = true;
			ingredients.add(serializeItem(o));
		}
		json.put("ingredients", ingredients);
		json.put("type", isOreDict ? "forge:ore_shapeless" : "minecraft:crafting_shapeless");
		json.put("result", serializeItem(result));

		// names the json the same name as the output's registry name
		// repeatedly adds _alt if a file already exists
		// janky I know but it works
		String suffix = result.getItem().getHasSubtypes() ? "_" + result.getItemDamage() : "";
		File f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");

		while (f.exists()) {
			suffix += "_alt";
			f = new File(RECIPE_DIR, result.getItem().getRegistryName().getResourcePath() + suffix + ".json");
		}


		try (FileWriter w = new FileWriter(f)) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Map<String, Object> serializeItem(Object thing) {
		if (thing instanceof Item) {
			return serializeItem(new ItemStack((Item) thing));
		}
		if (thing instanceof Block) {
			return serializeItem(new ItemStack((Block) thing));
		}
		if (thing instanceof ItemStack) {
			ItemStack stack = (ItemStack) thing;
			Map<String, Object> ret = new HashMap<>();
			ret.put("item", stack.getItem().getRegistryName().toString());
			if (stack.getItem().getHasSubtypes() || stack.getItemDamage() != 0) {
				ret.put("data", stack.getItemDamage());
			}
			if (stack.getCount() > 1) {
				ret.put("count", stack.getCount());
			}
			
			if (stack.hasTagCompound()) {
				ret.put("type", "minecraft:item_nbt");
				ret.put("nbt", stack.getTagCompound().toString());
			}

			return ret;
		}
		if (thing instanceof String) {
			Map<String, Object> ret = new HashMap<>();
			USED_OD_NAMES.add((String) thing);
			ret.put("item", "#" + ((String) thing).toUpperCase(Locale.ROOT));
			return ret;
		}

		throw new IllegalArgumentException("Not a block, item, stack, or od name");
	}

	// Call this after you are done generating
	private static void generateConstants() {
		List<Map<String, Object>> json = new ArrayList<>();
		for (String s : USED_OD_NAMES) {
			Map<String, Object> entry = new HashMap<>();
			entry.put("name", s.toUpperCase(Locale.ROOT));
			entry.put("ingredient", ImmutableMap.of("type", "forge:ore_dict", "ore", s));
			json.add(entry);
		}

		try (FileWriter w = new FileWriter(new File(RECIPE_DIR, "_constants.json"))) {
			GSON.toJson(json, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
