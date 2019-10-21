package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.client.music.MovingSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPortableJukebox extends EFItem {

    public MusicDiscItem record;

    public ItemPortableJukebox(String registryName, ItemGroup tab, MusicDiscItem recordIn) {
        super(registryName, tab);
        record = recordIn;
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (record == null)
            return ActionResultType.PASS;

        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (player.isSneaking()) {
            //TODO: Fix this from giving air
            ItemStack itemstack = context.getItem();
            itemstack.shrink(1);

            player.addItemStackToInventory(new ItemStack(ModItems.PORTABLE_JUKEBOX));
            player.addItemStackToInventory(new ItemStack(record));

            if (world.isRemote)
                Minecraft.getInstance().getSoundHandler().stop();

            return ActionResultType.SUCCESS;
        }

        if (world.isRemote) {
            Minecraft.getInstance().getSoundHandler().stop();
            playSound(player, record);
        }
        return ActionResultType.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    private void playSound(PlayerEntity playerIn, MusicDiscItem recordIn) {
        Minecraft.getInstance().getSoundHandler().play(new MovingSound(playerIn, recordIn.getSound()));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (record != null)
            tooltip.add(record.getName());
    }

}
