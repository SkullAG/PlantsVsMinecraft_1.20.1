package net.skullag.plantsvsminecraft.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.skullag.ShootHelper;
import net.skullag.plantsvsminecraft.entity.custom.projectiles.PeaProjectileEntity;
import net.skullag.plantsvsminecraft.sound.ModSounds;

public class PeaShooterEntity extends PlantEntity {
    protected static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(PeaShooterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected static final TrackedData<Boolean> NUTRIENTING = DataTracker.registerData(PeaShooterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected float DAMAGE = 3;

    protected int COOLDOWN_BETWEEN_SHOTS = 600;
    protected int SHOOT_DELAY = 900;
    protected int SHOOTING_DURATION = 1000;
    protected int cooldownTillNextShot = COOLDOWN_BETWEEN_SHOTS;
    protected float shootTimer = 0;

    protected int NUTRIENT_DURATION = 2000;
    protected int SHOTS_PER_NUTRIENT = 60;
    protected float nutrientUseTimer = 0;
    protected int shotsShot = 0;

    public final AnimationState idleState = new AnimationState();
    public final AnimationState shooting = new AnimationState();
    public final AnimationState nutrientState = new AnimationState();

    public PeaShooterEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public static FabricEntityTypeBuilder<PeaShooterEntity> builder() {
        return builder(PeaShooterEntity::new).dimensions(EntityDimensions.fixed(0.8f, 1.3f));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("ShootCooldown")) {
            this.cooldownTillNextShot = nbt.getInt("ShootCooldown");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("ShootCooldown", this.cooldownTillNextShot);
    }

    @Override
    protected int initGoals(int initIndex) {
        this.targetSelector.add(++initIndex, new ActiveTargetGoal<>(this, HostileEntity.class, true));
        initIndex = super.initGoals(++initIndex);

        return initIndex;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getTarget() != null){
            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, this.getTarget().getEyePos());
        }

        if (isNutrienting()) {
            nutrientTimer();
        } else {
            shootingTimer();
        }
        setAnimationStates();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHOOTING, false);
        this.dataTracker.startTracking(NUTRIENTING, false);
    }

    protected void setShooting(boolean value) {
        this.dataTracker.set(SHOOTING, value);
    }

    protected boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
    }

    protected void setNutrienting(boolean value) {
        this.dataTracker.set(NUTRIENTING, value);
    }

    protected boolean isNutrienting() {
        return this.dataTracker.get(NUTRIENTING);
    }

    private void setAnimationStates() {
        if (isNutrienting()) {
            this.idleState.stop();
            this.shooting.stop();
            this.nutrientState.startIfNotRunning(this.age);

            this.generateNutrientParticles(1);
        } else if (isShooting()) {
            this.idleState.stop();
            this.shooting.startIfNotRunning(this.age);
        } else {
            this.nutrientState.stop();
            this.shooting.stop();
            this.idleState.startIfNotRunning(this.age);
        }
    }

    protected void shootingTimer()
    {
        if(this.getWorld().isClient()) { return; }

        if(cooldownTillNextShot > 0) {
            cooldownTillNextShot -= 1000 / 20;
        }

        if(cooldownTillNextShot <= 0 && this.getTarget() != null && this.getTarget().isAlive() && shootTimer <= 0)
        {
            this.startShooting();
        }

        if (shootTimer > 0) {
            shootTimer -= 1000 / 20;

            if (cooldownTillNextShot <= 0 && shootTimer <= (SHOOTING_DURATION - SHOOT_DELAY)) {
                shoot(0);
            }
            if (shootTimer <= 0) {
                setShooting(false);
            }
        }
    }

    protected void nutrientTimer()
    {
        if(this.getWorld().isClient()) { return; }

        if (nutrientUseTimer > 0) {
            nutrientUseTimer -= 1000 / 20;

            float delayTillNextBatch = (shotsShot + 1) * ((float) NUTRIENT_DURATION / (SHOTS_PER_NUTRIENT + 1));

            if (shotsShot < SHOTS_PER_NUTRIENT && nutrientUseTimer <= NUTRIENT_DURATION - delayTillNextBatch) {
                shotsShot++;
                shoot(2);
            }
        } else {
            setNutrienting(false);
            shotsShot = 0;
        }
    }

    protected void startShooting() {
        setShooting(true);
        shootTimer = SHOOTING_DURATION;
        shotsShot = 0;
    }

    protected void shoot(float divergence)
    {
        if(this.getTarget() != null)
        {
            ShootHelper.shoot(new PeaProjectileEntity(this, this.getWorld(), this.DAMAGE), this, new Vec3d(0, 0.875f,0),
                    this.getTarget(), 1.5f, 0);
        } else {
            ShootHelper.shoot(new PeaProjectileEntity(this, this.getWorld(), this.DAMAGE), this, new Vec3d(0, 0.875f,0),
                    1.5f, divergence);

            this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(this.lookControl.getLookX(), this.lookControl.getLookY(), this.lookControl.getLookZ()));
        }

        this.playSound(ModSounds.PEA_THROW_SOUND, 10f, 1f);

        cooldownTillNextShot = COOLDOWN_BETWEEN_SHOTS;
    }

    @Override
    public boolean nutrientUsed() {
        if(!isNutrienting()) {
            setNutrienting(true);
            nutrientUseTimer = NUTRIENT_DURATION;

            return true;
        }
        return false;
    }

    public static DefaultAttributeContainer.Builder createPeaShooterAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ARMOR, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, Double.POSITIVE_INFINITY)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0);
    }
}
