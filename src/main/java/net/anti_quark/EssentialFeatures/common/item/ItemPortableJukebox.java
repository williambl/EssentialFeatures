package net.anti_quark.EssentialFeatures.common.item;

import net.anti_quark.EssentialFeatures.client.music.MovingSoundGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class ItemPortableJukebox extends EFItem {

    public ItemRecord record;

    public ItemPortableJukebox (String registryName, CreativeTabs tab, ItemRecord recordIn) {
        super(registryName, tab);
        record = recordIn;
    }

     /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (record == null)
            return EnumActionResult.PASS;

        if (worldIn.isRemote)
            Minecraft.getMinecraft().getSoundHandler().playSound(new MovingSoundGeneric(player, record.getSound()));
        return EnumActionResult.SUCCESS;
    }
}
