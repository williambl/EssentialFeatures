package com.williambl.essentialfeatures.common.item;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class PortableNoteBlockItem extends EFItem {

    private static final List<SoundEvent> INSTRUMENTS = Lists.newArrayList(SoundEvents.BLOCK_NOTE_BLOCK_HARP, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM, SoundEvents.BLOCK_NOTE_BLOCK_SNARE, SoundEvents.BLOCK_NOTE_BLOCK_HAT, SoundEvents.BLOCK_NOTE_BLOCK_BASS, SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, SoundEvents.BLOCK_NOTE_BLOCK_BELL, SoundEvents.BLOCK_NOTE_BLOCK_GUITAR, SoundEvents.BLOCK_NOTE_BLOCK_CHIME, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE);

    public PortableNoteBlockItem(String registryName) {
        super(registryName, ItemGroup.TOOLS);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getPos();

        world.playSound(null, player.getPosition(), getInstrumentFromBlock(world, pos), SoundCategory.RECORDS, 3.0F, getPitchFromPosition(pos));
        world.addParticle(ParticleTypes.NOTE, player.getPosX(), player.getPosY() + player.getEyeHeight(), player.getPosZ(), 1.0F, 0F, 0F);
        return ActionResultType.SUCCESS;
    }

    private float getPitchFromPosition(BlockPos pos) {
        return (float) pos.getY() / 128;
    }

    private SoundEvent getInstrumentFromBlock(World worldIn, BlockPos pos) {
        return NoteBlockInstrument.byState(worldIn.getBlockState(pos)).getSound();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        worldIn.playSound(null, playerIn.getPosition(), getInstrument(0), SoundCategory.RECORDS, 3.0F, getPitchFromPosition(playerIn.getPosition()));
        worldIn.addParticle(ParticleTypes.NOTE, playerIn.getPosX(), playerIn.getPosY() + playerIn.getEyeHeight(), playerIn.getPosZ(), 1.0F, 0F, 0F);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private SoundEvent getInstrument(int eventId) {
        if (eventId < 0 || eventId >= INSTRUMENTS.size())
            eventId = 0;
        return INSTRUMENTS.get(eventId);
    }
}
