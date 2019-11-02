package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.client.music.MovingSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemPortableJukebox extends EFItem {

    private List<ItemStack> jukeboxes = null;

    public ItemPortableJukebox(String registryName, ItemGroup tab) {
        super(registryName, tab);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        CompoundNBT tag = context.getItem().getOrCreateChildTag("Disc");

        MusicDiscItem disc = (MusicDiscItem) ItemStack.read(tag).getItem();

        if (disc == Items.AIR)
            return ActionResultType.PASS;

        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (player.isSneaking()) {
            context.getItem().removeChildTag("Disc");
            context.getItem().getOrCreateTag().put("Disc", ItemStack.EMPTY.serializeNBT());
            player.addItemStackToInventory(new ItemStack(disc));

            if (world.isRemote)
                Minecraft.getInstance().getSoundHandler().stop();

            return ActionResultType.SUCCESS;
        }

        if (world.isRemote) {
            Minecraft.getInstance().getSoundHandler().stop();
            playSound(player, disc);
        }
        return ActionResultType.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    private void playSound(PlayerEntity playerIn, MusicDiscItem recordIn) {
        Minecraft.getInstance().getSoundHandler().play(new MovingSound(playerIn, recordIn.getSound()));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT tag = stack.getOrCreateChildTag("Disc");

        ItemStack discStack = ItemStack.read(tag);

        if (discStack.getItem() != Items.AIR)
            tooltip.add(new StringTextComponent("Disc: ").appendSibling(((MusicDiscItem) discStack.getItem()).getRecordDescription()));
        else
            tooltip.add(new StringTextComponent("Empty"));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            items.add(new ItemStack(this));
            items.addAll(getJukeboxes());
        }
    }


    private List<ItemStack> getJukeboxes() {
        if (jukeboxes == null) {
            jukeboxes = new ArrayList<>();
            ItemTags.getCollection().getOrCreate(new ResourceLocation("minecraft:music_discs")).getAllElements().forEach(it -> {
                System.out.println(it.getName());
                ItemStack stack = new ItemStack(ModItems.PORTABLE_JUKEBOX);
                stack.getOrCreateTag().put("Disc", new ItemStack(it).serializeNBT());
                jukeboxes.add(stack);
            });
        }
        if (jukeboxes.size() == 0) {
            jukeboxes = null;
            return new ArrayList<>();
        }
        return jukeboxes;
    }

}
