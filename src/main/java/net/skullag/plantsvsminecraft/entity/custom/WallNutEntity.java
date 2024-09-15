package net.skullag.plantsvsminecraft.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.item.ModItems;

public class WallNutEntity extends PlantEntity{
    protected static float MAX_HEALTH = 600;
    protected static float INITIAL_HEALTH = 300;

    public static Item getOwnGenerator() {
        return ModItems.WALLNUT_SEED_PACK;
    }

    public WallNutEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);

        this.setHealth(INITIAL_HEALTH);
    }

    public static FabricEntityTypeBuilder<WallNutEntity> builder() {
        return builder(WallNutEntity::new).dimensions(EntityDimensions.fixed(0.8f, 1f));
    }

    @Override
    protected int initGoals(int initIndex) {
        initIndex = super.initGoals(initIndex);
        return initIndex;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.isOf(getOwnGenerator()) && this.getHealth() < MAX_HEALTH / 4) {
            if (!this.getWorld().isClient) {
                resetHealth();
                itemStack.decrement(1);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void resetHealth() {
        this.setHealth(INITIAL_HEALTH);
    }

    @Override
    public boolean nutrientUsed() {
        if(this.getHealth() < (MAX_HEALTH / 4) * 3) {
            this.setHealth(MAX_HEALTH);

            this.generateNutrientParticles(20);

            return true;
        }
        return false;
    }

    public static DefaultAttributeContainer.Builder createWallNutAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, MAX_HEALTH)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ARMOR, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, Double.POSITIVE_INFINITY);
    }
}
