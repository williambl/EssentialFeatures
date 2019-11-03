package com.williambl.essentialfeatures.common.item;

import com.williambl.essentialfeatures.common.networking.ModPackets;
import com.williambl.essentialfeatures.common.networking.PortableJukeboxMessage;
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
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PortableJukeboxItem extends EFItem {

    private List<ItemStack> jukeboxes = null;

    public PortableJukeboxItem(String registryName, ItemGroup tab) {
        super(registryName, tab);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        CompoundNBT tag = context.getItem().getOrCreateChildTag("Disc");

        Item item = ItemStack.read(tag).getItem();

        if (!(item instanceof MusicDiscItem))
            return ActionResultType.PASS;
        MusicDiscItem disc = (MusicDiscItem) item;

        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (player.isSneaking()) {
            context.getItem().removeChildTag("Disc");
            context.getItem().getOrCreateTag().put("Disc", ItemStack.EMPTY.serializeNBT());
            player.addItemStackToInventory(new ItemStack(disc));

            if (!world.isRemote) {
                ModPackets.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(context::getPlayer), new PortableJukeboxMessage(false, context.getPlayer().getUniqueID(), disc.getSound().getRegistryName()));
            }

            return ActionResultType.SUCCESS;
        }

        if (!world.isRemote) {
            ModPackets.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(context::getPlayer), new PortableJukeboxMessage(true, context.getPlayer().getUniqueID(), disc.getSound().getRegistryName()));
        }
        return ActionResultType.SUCCESS;
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
