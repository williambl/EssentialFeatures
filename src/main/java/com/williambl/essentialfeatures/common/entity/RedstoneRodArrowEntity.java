package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.block.ModBlocks;
import com.williambl.essentialfeatures.common.item.ModItems;
import com.williambl.essentialfeatures.common.tileentity.TileEntityRedstoneRod;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Objects;

public class RedstoneRodArrowEntity extends AbstractArrowEntity {

    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;

    private int ticksInAir;

    private double damage;
    private int knockbackStrength;

    public RedstoneRodArrowEntity(EntityType entityType, World world) {
        super(entityType, world);
    }

    public RedstoneRodArrowEntity(World worldIn) {
        super(ModEntities.REDSTONE_ROD_ARROW, worldIn);
    }

    public RedstoneRodArrowEntity(double x, double y, double z, World worldIn) {
        super(ModEntities.REDSTONE_ROD_ARROW, x, y, z, worldIn);
    }

    public RedstoneRodArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        this(packet.getPosX(), packet.getPosY(), packet.getPosZ(), world);
        this.setHeadRotation(packet.getHeadYaw(), packet.getPitch());
        this.setUniqueId(packet.getUuid());
        this.setEntityId(packet.getEntityId());
        this.setVelocity(packet.getVelX(), packet.getVelY(), packet.getVelZ());
    }

    public RedstoneRodArrowEntity(LivingEntity shooter, World worldIn) {
        super(ModEntities.REDSTONE_ROD_ARROW, shooter, worldIn);
    }

    @Override
    protected void arrowHit(LivingEntity target) {
        super.arrowHit(target);

        if (!world.isRemote && target.world.canBlockSeeSky(new BlockPos(target.getPosX(), target.getPosY(), target.getPosZ()))) {
            Entity owner = func_234616_v_();
            LightningBoltEntity bolt = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, target.world);
            bolt.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
            bolt.setEffectOnly(false);
            bolt.setCaster(owner instanceof ServerPlayerEntity ? (ServerPlayerEntity) owner : null);
            target.world.addEntity(bolt);
        }
    }

    @Override
    protected void onImpact(RayTraceResult raytraceResultIn) {
        super.onImpact(raytraceResultIn);
        if (raytraceResultIn.getType() == RayTraceResult.Type.BLOCK) {
            if (world.getBlockState(((BlockRayTraceResult) raytraceResultIn).getPos()).getBlock() == ModBlocks.REDSTONE_ROD && world.canBlockSeeSky(((BlockRayTraceResult) raytraceResultIn).getPos())) {
                ((TileEntityRedstoneRod) Objects.requireNonNull(world.getTileEntity(((BlockRayTraceResult) raytraceResultIn).getPos()))).makeLightning(world.getBlockState(((BlockRayTraceResult) raytraceResultIn).getPos()));
            }
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.REDSTONE_ROD_ARROW);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.REDSTONE_ROD_ARROW;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
