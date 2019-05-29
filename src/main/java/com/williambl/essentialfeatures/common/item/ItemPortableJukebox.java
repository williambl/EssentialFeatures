package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.client.music.MovingSoundGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPortableJukebox extends EFItem {

    public ItemRecord record;

    public ItemPortableJukebox(String registryName, ItemGroup tab, ItemRecord recordIn) {
        super(registryName, tab);
        record = recordIn;
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        if (record == null)
            return EnumActionResult.PASS;

        EntityPlayer player = context.getPlayer();
        World world = context.getWorld();

        if (player.isSneaking()) {
            ItemStack itemstack = context.getItem();
            itemstack.shrink(1);

            player.addItemStackToInventory(new ItemStack(ModItems.PORTABLE_JUKEBOX));
            player.addItemStackToInventory(new ItemStack(record));

            if (world.isRemote)
                Minecraft.getInstance().getSoundHandler().stop();

            return EnumActionResult.SUCCESS;
        }

        if (world.isRemote) {
            Minecraft.getInstance().getSoundHandler().stop();
            playSound(player, record);
        }
        return EnumActionResult.SUCCESS;
    }

    @OnlyIn(Dist.CLIENT)
    private void playSound(EntityPlayer playerIn, ItemRecord recordIn) {
        Minecraft.getInstance().getSoundHandler().play(new MovingSoundGeneric(playerIn, recordIn.getSound()));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (record != null)
            tooltip.add(record.getName());
    }

}
