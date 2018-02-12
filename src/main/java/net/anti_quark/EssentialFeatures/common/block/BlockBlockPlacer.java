package net.anti_quark.EssentialFeatures.common.block;

import javax.annotation.Nullable;

import net.anti_quark.EssentialFeatures.common.tileentity.TileEntityBlockPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class BlockBlockPlacer extends BlockDispenser {
	
	private final IBehaviorDispenseItem dropBehavior = new BehaviorDefaultDispenseItem();

	protected BlockBlockPlacer(String registryName, Material material, float hardness, float resistance) {
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.FALSE));
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
	}
	
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
    protected IBehaviorDispenseItem getBehavior(@Nullable ItemStack stack)
    {
        return this.dropBehavior;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityBlockPlacer();
    }

    protected void dispense(World worldIn, BlockPos pos)
    {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
        TileEntityBlockPlacer tileentityblockplacer = blocksourceimpl.getBlockTileEntity();

        if (tileentityblockplacer != null)
        {
            int i = tileentityblockplacer.getDispenseSlot();

            if (i < 0)
            {
                worldIn.playEvent(1001, pos, 0);
            }
            else
            {
                ItemStack itemstack = tileentityblockplacer.getStackInSlot(i);

                if (itemstack != null && net.minecraftforge.items.VanillaInventoryCodeHooks.dropperInsertHook(worldIn, pos, tileentityblockplacer, i, itemstack))
                {
                    EnumFacing enumfacing = worldIn.getBlockState(pos).getValue(FACING);
                    BlockPos blockpos = pos.offset(enumfacing);
                	ItemStack itemstack1;
                    
                    if (itemstack.getItem() instanceof ItemBlock) {
                    	if (worldIn.getBlockState(blockpos).getMaterial() != Material.AIR)
                    		worldIn.destroyBlock(blockpos, true);
                    	itemstack1 = itemstack.copy();
                    	itemstack = itemstack1.splitStack(1);
                    	place(itemstack, blockpos, worldIn);
                    }
                    else {
                    	itemstack1 = this.dropBehavior.dispense(blocksourceimpl, itemstack);

                    	if (itemstack1 != null && itemstack1.getCount() <= 0)
                    	{
                    		itemstack1 = null;
                    	}
                    }
                    tileentityblockplacer.setInventorySlotContents(i, itemstack1);
                }
            }
        }
    }
    
    public void place (ItemStack stack, BlockPos bpos, World worldIn) {
    	Block block = ((ItemBlock) stack.getItem()).getBlock();
    	IBlockState state = block.getStateFromMeta(stack.getMetadata());
    	worldIn.setBlockState(bpos, state);
    }

}
