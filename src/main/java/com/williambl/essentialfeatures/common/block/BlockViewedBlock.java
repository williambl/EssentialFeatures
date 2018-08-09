package com.williambl.essentialfeatures.common.block;

import com.williambl.essentialfeatures.common.tileentity.TileEntityViewedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockViewedBlock extends Block implements ITileEntityProvider {

    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockViewedBlock(String registryName, Material material, float hardness, float resistance) {
        super(material);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));
        this.hasTileEntity = true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityViewedBlock();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(POWERED, (meta & 1) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POWERED) ? 1 : 0;
    }

    public void activate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.withProperty(POWERED, Boolean.TRUE));
    }

    public void deactivate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.withProperty(POWERED, Boolean.FALSE));
    }

    public boolean isPowered(IBlockState blockstate) {
        return blockstate.getValue(POWERED);
    }
}
