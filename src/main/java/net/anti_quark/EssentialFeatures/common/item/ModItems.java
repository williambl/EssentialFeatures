package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;

public class ModItems {
	
	public static ItemCereal itemCereal;
	public static ItemCereal itemIronCereal;
	
	public static void addItems () 
	{
		itemCereal = new ItemCereal("cereal", 1, 6, false);
		itemIronCereal = new ItemCereal("iron_cereal", 3, 6, true);
	}
	
	public static void initModels ()
	{
		itemCereal.initModel();
		itemIronCereal.initModel();
	}
}
