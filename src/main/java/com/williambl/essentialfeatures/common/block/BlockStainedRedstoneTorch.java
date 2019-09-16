package com.williambl.essentialfeatures.common.block;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class BlockStainedRedstoneTorch extends RedstoneTorchBlock {
    //TODO: Add wall-torch variant

    static final String[] names = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};
    int colour;
    private static final Map<World, List<Toggle>> BURNED_TORCHES = new java.util.WeakHashMap<World, List<Toggle>>(); // FORGE - fix vanilla MC-101233

    public BlockStainedRedstoneTorch(String registryName, int colourIn) {
        super(Properties.create(Material.REDSTONE_LIGHT));
        colour = colourIn;
        this.setRegistryName(registryName);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(BlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.STAINED_REDSTONE_TORCHES[colour]);
    }

    public static void update(BlockState p_196527_0_, World p_196527_1_, BlockPos p_196527_2_, Random p_196527_3_, boolean p_196527_4_) {
        List<BlockStainedRedstoneTorch.Toggle> list = BURNED_TORCHES.get(p_196527_1_);

        while(list != null && !list.isEmpty() && p_196527_1_.getGameTime() - (list.get(0)).time > 60L) {
            list.remove(0);
        }

        if (p_196527_0_.get(LIT)) {
            if (p_196527_4_) {
                p_196527_1_.setBlockState(p_196527_2_, p_196527_0_.with(LIT, Boolean.FALSE), 3);
                if (isBurnedOut(p_196527_1_, p_196527_2_, true)) {
                    p_196527_1_.playSound(null, p_196527_2_, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5F, 2.6F + (p_196527_1_.rand.nextFloat() - p_196527_1_.rand.nextFloat()) * 0.8F);

                    for(int i = 0; i < 5; ++i) {
                        double d0 = (double)p_196527_2_.getX() + p_196527_3_.nextDouble() * 0.6D + 0.2D;
                        double d1 = (double)p_196527_2_.getY() + p_196527_3_.nextDouble() * 0.6D + 0.2D;
                        double d2 = (double)p_196527_2_.getZ() + p_196527_3_.nextDouble() * 0.6D + 0.2D;
                        p_196527_1_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }

                    p_196527_1_.getPendingBlockTicks().scheduleTick(p_196527_2_, p_196527_1_.getBlockState(p_196527_2_).getBlock(), 160);
                }
            }
        } else if (!p_196527_4_ && !isBurnedOut(p_196527_1_, p_196527_2_, false)) {
            p_196527_1_.setBlockState(p_196527_2_, p_196527_0_.with(LIT, Boolean.TRUE), 3);
        }

    }

    @Override
    protected boolean shouldBeOff(World worldIn, BlockPos pos, BlockState state) {
        return worldIn.isSidePowered(pos.down(), Direction.DOWN);
    }

    private static boolean isBurnedOut(World p_176598_0_, BlockPos worldIn, boolean pos) {
        List<BlockStainedRedstoneTorch.Toggle> list = BURNED_TORCHES.computeIfAbsent(p_176598_0_, k -> Lists.newArrayList());

        if (pos) {
         list.add(new BlockStainedRedstoneTorch.Toggle(worldIn.toImmutable(), p_176598_0_.getGameTime()));
      }

      int i = 0;

      for(int j = 0; j < list.size(); ++j) {
         BlockStainedRedstoneTorch.Toggle blockredstonetorch$toggle = list.get(j);
         if (blockredstonetorch$toggle.pos.equals(worldIn)) {
            ++i;
            if (i >= 8) {
               return true;
            }
         }
      }

      return false;
   }

    static class Toggle
    {
        BlockPos pos;
        long time;

        public Toggle(BlockPos pos, long time)
        {
            this.pos = pos;
            this.time = time;
        }
    }
}
