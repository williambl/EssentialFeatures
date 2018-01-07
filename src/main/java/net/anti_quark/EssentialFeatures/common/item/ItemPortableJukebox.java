package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.client.music.ModSound;
import net.anti_quark.EssentialFeatures.client.music.MovingSoundGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPortableJukebox extends EFItem {

    public ItemPortableJukebox (String registryName, CreativeTabs tab) {
        super(registryName, tab);
    }

     /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Minecraft.getMinecraft().getSoundHandler().playSound(new MovingSoundGeneric(player, ModSound.RECORD_SCARLET));
        return EnumActionResult.SUCCESS;
    }
}
