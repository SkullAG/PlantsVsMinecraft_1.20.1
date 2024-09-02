package net.skullag.plantsvsminecraft.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;

import java.util.List;

public abstract class PlantEntity extends MobEntity {
    protected final float HOSTILITY_RANGE = 20;

    protected PlantEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    protected static <T extends PlantEntity> FabricEntityTypeBuilder<T> builder(EntityType.EntityFactory<T> entityFactory) {
        return FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, entityFactory);
    }

    @Override
    protected void initGoals() {
        initGoals(1);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {
            if (this.getWorld().getBiome(this.getBlockPos()).isIn(BiomeTags.SNOW_GOLEM_MELTS)) {
                this.damage(this.getDamageSources().onFire(), 1.0F);
            }

            if (this.isOnGround()) {
                Vec3d pos = this.getPos();
                this.setPos(MathHelper.floor(pos.x) + 0.5f, pos.y, MathHelper.floor(pos.z) + 0.5f);
            }

            if (this.isSubmergedInWater()) {
                this.damage(this.getDamageSources().drown(), getMaxHealth() / 8);
            }

            var world = this.getWorld();

            if (world.getBiome(this.getBlockPos()).getKey().toString().contains("snowy") &&
                    world.isSkyVisible(this.getBlockPos())) {
                PlantsVsMinecraft.LOGGER.info("cold!");

                if (world.isRaining()) {
                    this.setInPowderSnow(true);
                }
                if (world.isThundering()) {
                    this.setInPowderSnow(true);
                    this.setFrozenTicks(Math.min(this.getMinFreezeDamageTicks(), this.getFrozenTicks() + 3));
                }
            }

            makeMonstersHostile();
        }
    }

    protected void makeMonstersHostile() {
        Vec3d pos = this.getPos();
        Vec3d radVec = new Vec3d(HOSTILITY_RANGE, HOSTILITY_RANGE, HOSTILITY_RANGE);

        List<HostileEntity> enemies = this.getWorld().getEntitiesByClass(HostileEntity.class,
                new Box(pos.subtract(radVec), pos.add(radVec)),
                this::isEntityHostilityTarget);

        for (HostileEntity enemy : enemies) {
            enemy.setTarget(this);
        }
    }

    protected boolean isEntityHostilityTarget(HostileEntity entity) {

        LivingEntity actualTarget = entity.getTarget();

        return !(entity instanceof EndermanEntity) &&
                (actualTarget == null || (actualTarget != this && actualTarget.distanceTo(entity) > this.distanceTo(entity)));
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return super.isInvulnerableTo(damageSource) || damageSource.isOf(DamageTypes.CACTUS);
    }

    protected int initGoals(int initIndex) {
        this.goalSelector.add(initIndex, new LookAtEntityGoal(this, HostileEntity.class, 100));
        this.goalSelector.add(initIndex++, new LookAtEntityGoal(this, PlayerEntity.class, 20));
        this.goalSelector.add(initIndex++, new LookAroundGoal(this));

        return initIndex;
    }
}
