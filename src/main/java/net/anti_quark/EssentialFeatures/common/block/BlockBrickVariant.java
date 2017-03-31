package net.anti_quark.EssentialFeatures.common.block;

import mcjty.lib.compat.CompatBlock;
import mcjty.lib.tools.ItemStackList;
import net.anti_quark.EssentialFeatures.common.item.ItemBlockWithSubtypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBrickVariant extends CompatBlock {
	
    private static final String[] names = new String[]{"cream_bricks", "london_bricks", "roman_bricks", "blue_bricks", "mixed_bricks"};
	public static final PropertyInteger variant = PropertyInteger.create("variant", 0, 4);

	public BlockBrickVariant(String registryName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setSoundType(blockSoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(variant, 0));
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlockWithSubtypes(this, true, names), getRegistryName());
	}
	
    public void initModel() {
        for (Integer integer : variant.getAllowedValues())
        {
        	if (integer == 0)
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), integer, new ModelResourceLocation(getRegistryName(), "inventory"));
        	else
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), integer, new ModelResourceLocation(getRegistryName(), "inventory_" + integer.toString()));
        }
    }
    
    @Override
    protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]{variant});
	}
	
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(variant, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(variant).intValue();
    }
    
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, ItemStackList list)
    {
        for (Integer integer : variant.getAllowedValues())
        {
            list.add(new ItemStack(itemIn, 1, integer));
            System.out.println(integer);
        }
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(variant).intValue();
    }
	
}
