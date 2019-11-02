package com.williambl.essentialfeatures.common.entity;

import com.williambl.essentialfeatures.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

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

        if (target.world.canBlockSeeSky(new BlockPos(target.posX, target.posY, target.posZ))) {
            LightningBoltEntity bolt = new LightningBoltEntity(target.world, target.posX, target.posY, target.posZ, false);
            ((ServerWorld) target.world).addLightningBolt(bolt);
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
