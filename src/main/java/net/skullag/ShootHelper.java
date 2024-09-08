package net.skullag;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Vec3d;

public abstract class ShootHelper {

    public static <T extends ProjectileEntity> T shoot(T projectile, Entity owner, Vec3d originOffset, Vec3d direction, float speed, float divergence) {
        projectile.setPosition(owner.getPos().add(originOffset));
        projectile.setVelocity(direction.x, direction.y, direction.z, speed, divergence);
        projectile.setOwner(owner);
        owner.getWorld().spawnEntity(projectile);

        return projectile;
    }

    public static <T extends ProjectileEntity> T shoot(T projectile, Entity owner, Vec3d originOffset, float pitch, float yaw, float roll, float speed, float divergence) {
        projectile.setPosition(owner.getPos().add(originOffset));
        projectile.setVelocity(owner, pitch, yaw, roll, speed, divergence);
        projectile.setOwner(owner);
        owner.getWorld().spawnEntity(projectile);

        return projectile;
    }

    public static <T extends ProjectileEntity> T shoot(T projectile, Entity owner, Vec3d originOffset, Entity target, float speed, float divergence) {

        Vec3d direction = target.getEyePos().subtract(owner.getPos().add(originOffset));

        return shoot(projectile, owner, originOffset, direction, speed, divergence);
    }

    public static <T extends ProjectileEntity> T shoot(T projectile, Entity owner, Vec3d originOffset, float speed, float divergence) {

        return shoot(projectile, owner, originOffset, owner.getPitch(), owner.getYaw(), 0, speed, divergence);
    }
}
