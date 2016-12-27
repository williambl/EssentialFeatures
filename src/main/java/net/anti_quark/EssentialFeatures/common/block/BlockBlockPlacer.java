package net.anti_quark.EssentialFeatures.common.block;

import javax.annotation.Nullable;

import net.anti_quark.EssentialFeatures.common.tileentity.TileEntityViewedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryDefaulted;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBlockPlacer extends BlockDispenser {
	
	private final IBehaviorDispenseItem dropBehavior = new BehaviorDefaultDispenseItem();

	protected BlockBlockPlacer(String registryName, Material material, float hardness, float resistance) {
		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, Boolean.valueOf(false)));
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
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
        return new TileEntityDropper();
    }

    protected void dispense(World worldIn, BlockPos pos)
    {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)blocksourceimpl.getBlockTileEntity();

        if (tileentitydispenser != null)
        {
            int i = tileentitydispenser.getDispenseSlot();

            if (i < 0)
            {
                worldIn.playEvent(1001, pos, 0);
            }
            else
            {
                ItemStack itemstack = tileentitydispenser.getStackInSlot(i);

                if (itemstack != null && net.minecraftforge.items.VanillaInventoryCodeHooks.dropperInsertHook(worldIn, pos, tileentitydispenser, i, itemstack))
                {
                    EnumFacing enumfacing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING);
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

                    	if (itemstack1 != null && itemstack1.func_190916_E() <= 0)
                    	{
                    		itemstack1 = null;
                    	}
                    }
                    tileentitydispenser.setInventorySlotContents(i, itemstack1);
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
