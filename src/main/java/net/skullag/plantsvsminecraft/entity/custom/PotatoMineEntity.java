package net.skullag.plantsvsminecraft.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.skullag.plantsvsminecraft.sound.ModSounds;

import java.util.Collection;
import java.util.random.RandomGenerator;

public class PotatoMineEntity extends PlantEntity {
    protected static final TrackedData<Boolean> TINY = DataTracker.registerData(PotatoMineEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected static final TrackedData<Boolean> GROWING = DataTracker.registerData(PotatoMineEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected static final TrackedData<Boolean> EXPLODING = DataTracker.registerData(PotatoMineEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected static final TrackedData<Boolean> NUTRIENTING = DataTracker.registerData(PotatoMineEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected float DAMAGE = 270;
    protected float EXPLOSION_RADIUS = 0.75F;

    protected int GROWING_TIME = 14000;
    protected float growTimer = GROWING_TIME;

    protected int GROW_ANIMATION_DURATION = 500;
    protected float growAnimationTimer = 0;

    protected int EXPLOSION_DURATION = 1000;
    protected float explosionTimer = 0;

    protected int NUTRIENT_DURATION = GROW_ANIMATION_DURATION;
    protected int POTATOS_PER_NUTRIENT = 3;
    protected float nutrientUseTimer = 0;
    protected int potatosShot = 0;

    public final AnimationState idleTinyState = new AnimationState();
    public final AnimationState growingState = new AnimationState();
    public final AnimationState idleGrownState = new AnimationState();
    public final AnimationState explodingState = new AnimationState();

    protected PotatoMineEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);

        this.setTiny(true);
    }

    public static FabricEntityTypeBuilder<PotatoMineEntity> builder() {
        return builder(PotatoMineEntity::new).dimensions(EntityDimensions.fixed(0.15f, 0.4f));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("GrowTimer")) {
            this.growTimer = nbt.getFloat("GrowTimer");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putFloat("GrowTimer", this.growTimer);
    }

    @Override
    protected int initGoals(int initIndex) {
        initIndex = super.initGoals(++initIndex);

        return initIndex;
    }

    @Override
    public void tick() {
        super.tick();

        if (isNutrienting()) {
            nutrientTimer();
        } else {
            growingTimer();
        }

        setAnimationStates();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TINY, true);
        this.dataTracker.startTracking(GROWING, false);
        this.dataTracker.startTracking(EXPLODING, false);
        this.dataTracker.startTracking(NUTRIENTING, false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
            if(isTiny()) {
                return super.damage(source, amount);
            }
            else if(!isGrowing() && !isNutrienting() && !isExploding()) {
                setExploding(true);
                explosionTimer = EXPLOSION_DURATION;
            }

            return false;
    }

    protected void setTiny(boolean value) {
        this.dataTracker.set(TINY, value);
    }

    protected boolean isTiny() {
        return this.dataTracker.get(TINY);
    }

    protected void setGrowing(boolean value) {
        this.dataTracker.set(GROWING, value);
    }

    protected boolean isGrowing() {
        return this.dataTracker.get(GROWING);
    }

    protected void setExploding(boolean value) {
        this.dataTracker.set(EXPLODING, value);
    }

    protected boolean isExploding() {
        return this.dataTracker.get(EXPLODING);
    }

    protected void setNutrienting(boolean value) {
        this.dataTracker.set(NUTRIENTING, value);
    }

    protected boolean isNutrienting() {
        return this.dataTracker.get(NUTRIENTING);
    }

    private void setAnimationStates() {
        if (isExploding()) {
            this.idleTinyState.stop();
            this.idleGrownState.stop();
            this.growingState.stop();
            this.explodingState.startIfNotRunning(this.age);

        } else if (isNutrienting()) {
            this.idleTinyState.stop();
            this.idleGrownState.stop();
            this.explodingState.stop();
            this.growingState.startIfNotRunning(this.age);

            this.generateNutrientParticles(1);
        } else if (isGrowing()) {
            this.idleTinyState.stop();
            this.idleGrownState.stop();
            this.explodingState.stop();
            this.growingState.startIfNotRunning(this.age);
        } else {
            this.explodingState.stop();
            this.growingState.stop();

            if (isTiny()) {
                this.idleGrownState.stop();
                this.idleTinyState.startIfNotRunning(this.age);
            } else {
                this.idleTinyState.stop();
                this.idleGrownState.startIfNotRunning(this.age);
            }
        }
    }

    protected void growingTimer() {
        if (this.getWorld().isClient()) {
            return;
        }

        if (growTimer > 0) {
            growTimer -= 1000 / 20;

            if (growTimer <= 0) {
                setGrowing(true);
                growAnimationTimer = GROW_ANIMATION_DURATION;
            }
        }

        if (growAnimationTimer > 0) {
            growAnimationTimer -= 1000 / 20;
        }

        if (explosionTimer > 0) {
            explosionTimer -= 1000 / 20;

            if (explosionTimer <= 0) {
                this.explode();
            }
        }

        if (growTimer <= 0 && isTiny()) {
            setTiny(false);
        }

        if (growAnimationTimer <= 0 && isGrowing()) {
            setGrowing(false);
        }
    }

    protected void nutrientTimer() {
        if (this.getWorld().isClient()) {
            return;
        }

        if (nutrientUseTimer > 0) {
            nutrientUseTimer -= 1000 / 20;

            while (potatosShot < POTATOS_PER_NUTRIENT) {
                shoot(45);

                potatosShot++;
            }
        } else {
            setNutrienting(false);
            potatosShot = 0;
        }
    }

    protected void explode() {
        if (!this.getWorld().isClient) {
            this.dead = true;
            this.getWorld().getEntitiesByClass(HostileEntity.class, Box.of(this.getPos().add(0, 0, 0), EXPLOSION_RADIUS, EXPLOSION_RADIUS, EXPLOSION_RADIUS), target -> {

                if(target.isAlive()) {
                    target.damage(getDamageSources().create(DamageTypes.EXPLOSION), DAMAGE);
                }

                return true;
            });

            this.generateExplosionClowd();

            this.playSound(ModSounds.POTATO_EXPLOSION, 1f, 1f);

            this.discard();
        }
    }

    private void generateExplosionClowd() {
        float delta = EXPLOSION_RADIUS;

        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
        areaEffectCloudEntity.setRadius(delta);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(0);
        areaEffectCloudEntity.setDuration(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setParticleType(ParticleTypes.EXPLOSION);

        this.getWorld().spawnEntity(areaEffectCloudEntity);
    }

    protected void shoot(float divergence) {
        // Todo potato projectile
//        if(this.getTarget() != null)
//        {
//            ShootHelper.shoot(new PeaProjectileEntity(this, this.getWorld(), this.DAMAGE), this, new Vec3d(0, 0.875f,0),
//                    this.getTarget(), 1.5f, 0);
//        } else {
//            ShootHelper.shoot(new PeaProjectileEntity(this, this.getWorld(), this.DAMAGE), this, new Vec3d(0, 0.875f,0),
//                    1.5f, divergence);
//
//            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(this.lookControl.getLookX(), this.lookControl.getLookY(), this.lookControl.getLookZ()));
//        }
//
//        this.playSound(ModSounds.PEA_THROW_SOUND, 10f, 1f);
//
//        cooldownTillNextShot = COOLDOWN_BETWEEN_SHOTS;
    }

    @Override
    public boolean nutrientUsed() {
        if (!isNutrienting() && !isExploding()) {
            setNutrienting(true);
            nutrientUseTimer = NUTRIENT_DURATION;

            growTimer = 0;
            growAnimationTimer = 0;

            return true;
        }
        return false;
    }

    public static DefaultAttributeContainer.Builder createPotatoMineAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ARMOR, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, Double.POSITIVE_INFINITY)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0);
    }
}
