package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;

public class ModItems {
	
	public static ItemCereal CEREAL;
	public static ItemCereal IRON_CEREAL;
	
	public static void addItems () 
	{
		CEREAL = new ItemCereal("cereal", 1, 6, false);
		IRON_CEREAL = new ItemCereal("iron_cereal", 3, 6, true);
	}
	
	public static void initModels ()
	{
		CEREAL.initModel();
		IRON_CEREAL.initModel();
	}
}
