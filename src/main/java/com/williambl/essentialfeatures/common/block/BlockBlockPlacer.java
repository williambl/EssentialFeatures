package com.williambl.essentialfeatures.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Collection;

public class BlockBlockPlacer extends BlockDispenser {

    private final IBehaviorDispenseItem dropBehavior = new BehaviorPlaceBlock();

    protected BlockBlockPlacer(String registryName, Material material, float hardness, float resistance) {
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.FALSE));
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDispenser();
    }

    @Override
    protected void dispense(World worldIn, BlockPos pos) {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
        TileEntityDispenser tileentityblockplacer = blocksourceimpl.getBlockTileEntity();

        if (tileentityblockplacer != null) {
            int i = tileentityblockplacer.getDispenseSlot();

            if (i < 0) {
                worldIn.playEvent(1001, pos, 0);
            } else {
                ItemStack itemstack = tileentityblockplacer.getStackInSlot(i);

                if (!itemstack.isEmpty()) {
                    ItemStack itemstack1;

                    itemstack1 = this.dropBehavior.dispense(blocksourceimpl, itemstack);

                    tileentityblockplacer.setInventorySlotContents(i, itemstack1);
                }
            }
        }
    }

    public class BehaviorPlaceBlock implements IBehaviorDispenseItem {
        @Override
        public ItemStack dispense(IBlockSource source, ItemStack stack) {
            Block block = Block.getBlockFromItem(stack.getItem());

            if (block == Blocks.AIR)
                return stack;

            EnumFacing facing = source.getBlockState().getValue(BlockDispenser.FACING);
            EnumFacing.Axis axis = facing.getAxis();

            BlockPos pos = source.getBlockPos().offset(facing);
            World world = source.getWorld();

            if (world.isAirBlock(pos) && block.canPlaceBlockAt(world, pos)) {
                int meta = stack.getItem().getMetadata(stack.getItemDamage());
                IBlockState state = block.getStateFromMeta(meta);
                Collection<IProperty<?>> props = state.getPropertyKeys();

                world.setBlockState(pos, state);
                SoundType soundtype = block.getSoundType();
                world.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
            }

            return stack;
        }
    }

}
