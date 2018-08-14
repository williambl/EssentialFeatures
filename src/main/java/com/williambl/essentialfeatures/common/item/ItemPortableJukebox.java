package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.client.music.MovingSoundGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPortableJukebox extends EFItem {

    public ItemRecord record;

    public ItemPortableJukebox(String registryName, CreativeTabs tab, ItemRecord recordIn) {
        super(registryName, tab);
        record = recordIn;
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (record == null)
            return EnumActionResult.PASS;

        if (player.isSneaking()) {
            ItemStack itemstack = player.getHeldItem(hand);
            itemstack.shrink(1);

            player.addItemStackToInventory(new ItemStack(ModItems.PORTABLE_JUKEBOX));
            player.addItemStackToInventory(new ItemStack(record));

            if (worldIn.isRemote)
                Minecraft.getMinecraft().getSoundHandler().stopSounds();

            return EnumActionResult.SUCCESS;
        }

        if (worldIn.isRemote) {
            Minecraft.getMinecraft().getSoundHandler().stopSounds();
            playSound(player, record);
        }
        return EnumActionResult.SUCCESS;
    }

    @SideOnly(Side.CLIENT)
    private void playSound(EntityPlayer playerIn, ItemRecord recordIn) {
        Minecraft.getMinecraft().getSoundHandler().playSound(new MovingSoundGeneric(playerIn, recordIn.getSound()));
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (record != null)
            tooltip.add(record.getRecordNameLocal());
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(ModItems.PORTABLE_JUKEBOX.getRegistryName(), "inventory"));
    }
}
