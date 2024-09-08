package net.skullag.plantsvsminecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.skullag.plantsvsminecraft.entity.custom.PlantEntity;
import net.skullag.plantsvsminecraft.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class SeedPack extends Item {
    private final EntityType<?> type;


    public SeedPack(EntityType<? extends MobEntity> type, Item.Settings settings) {
        super(settings);

        this.type = type;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!(world instanceof ServerWorld)) {
            return ActionResult.SUCCESS;
        } else {
            ItemStack itemStack = context.getStack();
            BlockPos blockPos = context.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.SPAWNER)) {
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                if (blockEntity instanceof MobSpawnerBlockEntity mobSpawnerBlockEntity) {
                    EntityType<?> entityType = this.getEntityType(itemStack.getNbt());
                    mobSpawnerBlockEntity.setEntityType(entityType, world.getRandom());
                    blockEntity.markDirty();
                    world.updateListeners(blockPos, blockState, blockState, Block.NOTIFY_ALL);
                    world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockPos);
                    itemStack.decrement(1);
                    return ActionResult.CONSUME;
                }
            }

            BlockPos blockPos2;
            if (blockState.getCollisionShape(world, blockPos).isEmpty()) {
                blockPos2 = blockPos;
            } else {
                blockPos2 = blockPos.add(0, 1, 0);
            }

            EntityType<?> entityType2 = this.getEntityType(itemStack.getNbt());
            if (world.getEntitiesByClass(PlantEntity.class, new Box(blockPos2), plantEntity -> true /*TODO check if can be mounted*/).isEmpty() &&
                    entityType2.spawnFromItemStack(
                            (ServerWorld)world,
                            itemStack,
                            context.getPlayer(),
                            blockPos2,
                            SpawnReason.SPAWN_EGG,
                            true,
                            false
                    )
                            != null) {
                itemStack.decrement(1);

                world.playSound(null, blockPos2, SoundEvents.ITEM_CROP_PLANT,
                        SoundCategory.NEUTRAL, 2f, 1f);

                world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
            }

            return ActionResult.CONSUME;
        }
    }

    public EntityType<?> getEntityType(@Nullable NbtCompound nbt) {
        if (nbt != null && nbt.contains("EntityTag", NbtElement.COMPOUND_TYPE)) {
            NbtCompound nbtCompound = nbt.getCompound("EntityTag");
            if (nbtCompound.contains("id", NbtElement.STRING_TYPE)) {
                return EntityType.get(nbtCompound.getString("id")).orElse(this.type);
            }
        }

        return this.type;
    }
}
