package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockNettles extends BlockBush implements IShearable, IBlockColor {

    protected static final AxisAlignedBB NETTLES_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockNettles (String registryName) {
        super(Material.VINE);
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(0);
        this.setResistance(0);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return NETTLES_AABB;
    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS;
    }

    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    public int quantityDropped(Random random)
    {
        return random.nextInt(3);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.STICK;
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(ModBlocks.NETTLES, 1, 0));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
            player.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
        }
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 50, 1));
        }
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return java.util.Arrays.asList(new ItemStack(Blocks.DEADBUSH));
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return 0;
    }
}
