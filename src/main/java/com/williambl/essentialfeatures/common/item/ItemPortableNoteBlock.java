package com.williambl.essentialfeatures.common.item;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNote;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ItemPortableNoteBlock extends EFItem {

    private static final List<SoundEvent> INSTRUMENTS = Lists.newArrayList(SoundEvents.BLOCK_NOTE_HARP, SoundEvents.BLOCK_NOTE_BASEDRUM, SoundEvents.BLOCK_NOTE_SNARE, SoundEvents.BLOCK_NOTE_HAT, SoundEvents.BLOCK_NOTE_BASS, SoundEvents.BLOCK_NOTE_FLUTE, SoundEvents.BLOCK_NOTE_BELL, SoundEvents.BLOCK_NOTE_GUITAR, SoundEvents.BLOCK_NOTE_CHIME, SoundEvents.BLOCK_NOTE_XYLOPHONE);

    public ItemPortableNoteBlock(String registryName) {
        super(registryName, CreativeTabs.TOOLS);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        worldIn.playSound((EntityPlayer)null, player.getPosition(), getInstrumentFromBlock(worldIn, pos), SoundCategory.RECORDS, 3.0F, getPitchFromPosition(pos));
        return EnumActionResult.SUCCESS;
    }

    private float getPitchFromPosition(BlockPos pos) {
        return (float)pos.getY() / 128;
    }

    private SoundEvent getInstrumentFromBlock (World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Material material = iblockstate.getMaterial();
        int i = 0;

        if (material == Material.ROCK)
            i = 1;

        if (material == Material.SAND)
            i = 2;

        if (material == Material.GLASS)
            i = 3;

        if (material == Material.WOOD)
            i = 4;

        Block block = iblockstate.getBlock();

        if (block == Blocks.CLAY)
            i = 5;

        if (block == Blocks.GOLD_BLOCK)
            i = 6;

        if (block == Blocks.WOOL)
            i = 7;

        if (block == Blocks.PACKED_ICE)
            i = 8;

        if (block == Blocks.BONE_BLOCK)
            i = 9;

        return getInstrument(i);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.playSound((EntityPlayer)null, playerIn.getPosition(), getInstrument(0), SoundCategory.RECORDS, 3.0F, getPitchFromPosition(playerIn.getPosition()));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private SoundEvent getInstrument(int eventId) {
        if (eventId < 0 || eventId >= INSTRUMENTS.size())
            eventId = 0;
        return INSTRUMENTS.get(eventId);
    }
}
