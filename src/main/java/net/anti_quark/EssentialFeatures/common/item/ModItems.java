package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.common.block.ModBlocks;

public class ModItems {
	
	public static ItemCereal itemCereal;
	
	public static void addItems () 
	{
		itemCereal = new ItemCereal("cereal", 1, 6);
	}
	
	public static void initModels ()
	{
		itemCereal.initModel();
	}
}
