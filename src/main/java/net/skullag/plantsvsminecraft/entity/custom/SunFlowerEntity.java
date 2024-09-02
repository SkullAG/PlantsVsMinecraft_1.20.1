package net.skullag.plantsvsminecraft.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.skullag.plantsvsminecraft.item.ModItems;

import java.util.random.RandomGenerator;

public class SunFlowerEntity extends PlantEntity{
    protected static final TrackedData<Boolean> PRODUCING_SUN = DataTracker.registerData(SunFlowerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected int COOLDOWN_BETWEEN_SUNS = 24000;
    protected int SUN_DELAY = 400;
    protected int SUN_PRODUCTION_DURATION = 500;
    protected int SUNS_PER_BATCH = 5;
    protected int BATCHES_PER_NUTRIENT = 3;
    protected int cooldownTillNextSun = COOLDOWN_BETWEEN_SUNS;
    protected float sunProdTimer = 0;

    public final AnimationState idleState = new AnimationState();
    public final AnimationState sunProductionState = new AnimationState();
    public final AnimationState nutrientState = new AnimationState();

    public SunFlowerEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public static FabricEntityTypeBuilder<SunFlowerEntity> builder() {
        return builder(SunFlowerEntity::new).dimensions(EntityDimensions.fixed(0.8f, 1.2f));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("SunProductionCooldown")) {
            this.cooldownTillNextSun = nbt.getInt("SunProductionCooldown");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("SunProductionCooldown", this.cooldownTillNextSun);
    }

    @Override
    protected int initGoals(int initIndex) {
        initIndex = super.initGoals(initIndex);
        return initIndex;
    }

    @Override
    public void tick() {
        super.tick();

        sunProductionTimer();
        setAnimationStates();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(PRODUCING_SUN, false);
    }

    protected void setProducingSun(boolean value) {
        this.dataTracker.set(PRODUCING_SUN, value);
    }

    protected boolean isProducingSun() {
        return this.dataTracker.get(PRODUCING_SUN);
    }

    private void setAnimationStates() {
        //PlantsVsMinecraft.LOGGER.info(String.valueOf(this.sunProdTimer));
        if (this.nutrientState.isRunning()) {

        } else if (isProducingSun()) {
            this.sunProductionState.startIfNotRunning(this.age);
        } else {
            ///PlantsVsMinecraft.LOGGER.info(String.valueOf(actualStateTime));
            this.sunProductionState.stop();
            this.idleState.startIfNotRunning(this.age);
        }
    }

    protected void sunProductionTimer()
    {
        if(this.getWorld().isClient()) { return; }

        if(cooldownTillNextSun > 0) {
            cooldownTillNextSun -= 1000 / 20;
            if(cooldownTillNextSun <= 0)
            {
                this.startSunProduction();
            }
        }

        if (sunProdTimer > 0) {
            sunProdTimer -= 1000 / 20;
            if (cooldownTillNextSun <= 0 && sunProdTimer <= (SUN_PRODUCTION_DURATION - SUN_DELAY)) {
                dropSun();
            }
            if (sunProdTimer <= 0) {
                setProducingSun(false);
            }
        }
    }

    protected void startSunProduction() {
        this.sunProductionState.start(this.age);

        setProducingSun(true);
        sunProdTimer = SUN_DELAY;
    }

    protected void dropSun()
    {
        ItemEntity itemEntity = this.dropStack(new ItemStack(ModItems.SunPoint, SUNS_PER_BATCH), 0.5f);
        if (itemEntity != null) {
            itemEntity.setVelocity(
                    itemEntity.getVelocity()
                            .add(
                                    ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F),
                                    (this.random.nextFloat() * 0.05F),
                                    ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)
                            )
            );
        }

        this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, RandomGenerator.getDefault().nextFloat(0.01f, 0.04f));

        cooldownTillNextSun = COOLDOWN_BETWEEN_SUNS;
    }

    public static DefaultAttributeContainer.Builder createSunFlowerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ARMOR, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, Double.POSITIVE_INFINITY);
    }
}
