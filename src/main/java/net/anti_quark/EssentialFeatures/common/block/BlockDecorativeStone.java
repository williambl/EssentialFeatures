package net.anti_quark.EssentialFeatures.common.block;

import net.anti_quark.EssentialFeatures.common.item.ItemDecorativeStone;
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
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockDecorativeStone extends Block {
	
    private static final String[] names = new String[]{"carved_stone", "carved_granite", "carved_diorite", "carved_andesite"};
	public static final PropertyInteger variant = PropertyInteger.create("variant", 0, 3);

	public BlockDecorativeStone(String registryName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setSoundType(blockSoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(variant, 0));
        GameRegistry.register(this);
        GameRegistry.register(new ItemDecorativeStone(this, true, names), getRegistryName());
	}
	
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 1, new ModelResourceLocation(getRegistryName(), "inventory_1"));
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
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for (Integer integer : variant.getAllowedValues())
        {
            list.add(new ItemStack(itemIn, 1, integer));
        }
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(variant).intValue();
    }
	
}
