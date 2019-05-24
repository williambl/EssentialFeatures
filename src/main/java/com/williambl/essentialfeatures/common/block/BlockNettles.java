package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class BlockNettles extends BlockBush implements IShearable {

    protected static final VoxelShape NETTLES_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public BlockNettles(String registryName) {
        super(Properties.create(Material.VINE).hardnessAndResistance(0, 0).sound(SoundType.PLANT).doesNotBlockMovement());
        this.setRegistryName(registryName);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return NETTLES_SHAPE;
    }

    @Override
    protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() == Blocks.GRASS;
    }

    @Override
    public Item getItemDropped(IBlockState state, World world, BlockPos pos, int fortune) {
        return null;
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
            spawnAsEntity(worldIn, pos, new ItemStack(ModBlocks.NETTLES, 1, new NBTTagCompound()));
        } else {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
            player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20, 1));
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1);
        }
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IWorldReader world, BlockPos pos) {
        return true;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IWorld world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(ModBlocks.NETTLES));
    }

}
