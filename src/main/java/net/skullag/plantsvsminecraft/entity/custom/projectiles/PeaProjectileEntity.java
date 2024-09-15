package net.skullag.plantsvsminecraft.entity.custom.projectiles;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.damagetype.ModDamageTypes;
import net.skullag.plantsvsminecraft.entity.ModEntities;
import net.skullag.plantsvsminecraft.entity.custom.SunFlowerEntity;
import net.skullag.plantsvsminecraft.item.ModItems;
import net.skullag.plantsvsminecraft.item.custom.PeaItem;
import net.skullag.plantsvsminecraft.sound.ModSounds;

import java.util.random.RandomGenerator;

public class PeaProjectileEntity extends ThrownItemEntity {
    public float DAMAGE = 3;

    public PeaProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PeaProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.PEA_PROJECTILE, livingEntity, world);
    }

    public PeaProjectileEntity(LivingEntity livingEntity, World world, float damage) {
        super(ModEntities.PEA_PROJECTILE, livingEntity, world);

        DAMAGE = damage;
    }

    public static FabricEntityTypeBuilder<PeaProjectileEntity> builder() {
        return FabricEntityTypeBuilder.<PeaProjectileEntity>create(SpawnGroup.MISC, PeaProjectileEntity::new)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f));
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PEA;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        this.playHitEffects();
        this.discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if(entityHitResult.getEntity() instanceof  LivingEntity entity) {
            if(entity.damage(ModDamageTypes.of(this.getWorld(), ModDamageTypes.THROWNCROP,this, null), DAMAGE) &&
                    entityHitResult.getEntity() instanceof  LivingEntity owner) {
                entity.setAttacker(owner);
            }

            this.playHitEffects();
            this.discard();
        }
    }

    private ParticleEffect getParticleParameters() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack());
    }

    protected void playHitEffects() {
        ParticleEffect particleEffect = this.getParticleParameters();

        for (int i = 0; i < 8; i++) {
            this.getWorld().addParticle(
                    particleEffect,
                    true,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    ((double)this.random.nextFloat() - 0.5) * 0.08,
                    ((double)this.random.nextFloat() - 0.5) * 0.08,
                    ((double)this.random.nextFloat() - 0.5) * 0.08
            );
        }

        this.playSound(ModSounds.PEA_HIT_SOUND, 1f, RandomGenerator.getDefault().nextFloat(0.8f, 2f));
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected boolean canHit(Entity entity) {
        if(entity instanceof HostileEntity) {
            return super.canHit(entity);
        }

        return false;
    }
}
