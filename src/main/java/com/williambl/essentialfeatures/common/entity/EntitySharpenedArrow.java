package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Arrays;

public class EntitySharpenedArrow extends EntityArrow {

    private int xTile;
    private int yTile;
    private int zTile;
    private IBlockState inBlockState;

    private int ticksInAir;

    private double damage;
    private int knockbackStrength;

    private Material[] breakableMaterials = {
            Material.GLASS,
            Material.ICE,
            Material.VINE,
            Material.WEB
    };

    public EntitySharpenedArrow(World worldIn) {
        super(ModEntities.SHARPENED_ARROW, worldIn);
        this.setIsCritical(true);
    }

    public EntitySharpenedArrow(double x, double y, double z, World worldIn) {
        super(ModEntities.SHARPENED_ARROW, x, y, z, worldIn);
        this.setIsCritical(true);
    }

    public EntitySharpenedArrow(EntityLivingBase shooter, World worldIn) {
        super(ModEntities.SHARPENED_ARROW, shooter, worldIn);
        this.setIsCritical(true);
    }

    protected void onHit(RayTraceResult raytraceResultIn) {
        if (raytraceResultIn.entity != null) {
            this.onHitEntity(raytraceResultIn);
        } else {
            BlockPos blockpos = raytraceResultIn.getBlockPos();
            this.xTile = blockpos.getX();
            this.yTile = blockpos.getY();
            this.zTile = blockpos.getZ();
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            this.inBlockState = iblockstate;

            if (!Arrays.asList(breakableMaterials).contains(iblockstate.getMaterial())) {
                this.motionX = (double)((float)(raytraceResultIn.hitVec.x - this.posX));
                this.motionY = (double)((float)(raytraceResultIn.hitVec.y - this.posY));
                this.motionZ = (double)((float)(raytraceResultIn.hitVec.z - this.posZ));
                float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ) * 20.0F;
                this.posX -= this.motionX / (double)f;
                this.posY -= this.motionY / (double)f;
                this.posZ -= this.motionZ / (double)f;
                this.playSound(this.getHitGroundSound(), 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.inGround = true;
                this.arrowShake = 7;
                this.setIsCritical(false);
                if (!iblockstate.isAir(world, blockpos)) {
                    this.inBlockState.onEntityCollision(this.world, blockpos, this);
                }
            } else {
                if (iblockstate.getMaterial() != Material.AIR)
                    this.inBlockState.onEntityCollision(this.world, blockpos, this);

                if (iblockstate.getMaterial() == Material.GLASS || iblockstate.getMaterial() == Material.ICE)
                    this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                else if (iblockstate.getMaterial() == Material.VINE || iblockstate.getMaterial() == Material.WEB)
                    this.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                world.setBlockState(new BlockPos(this.xTile, this.yTile, this.zTile), Blocks.AIR.getDefaultState());
            }

            this.setIsCritical(false);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.SHARPENED_ARROW);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.SHARPENED_ARROW;
    }
}
