package net.denobody2.icydwarfworldmod.common.entity;

import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class ShadowBallProjectile extends ThrowableItemProjectile {

    protected LivingEntity owner;
    public ShadowBallProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ShadowBallProjectile(Level pLevel) {
        this(ModEntities.SHADOW_BALL_PROJECTILE.get(), pLevel);
    }

    public ShadowBallProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.SHADOW_BALL_PROJECTILE.get(), livingEntity, pLevel);
        this.owner = livingEntity;
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onHit(HitResult pResult) {
        if(!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().explode(this, blockPosition().getX(), blockPosition().getY(), blockPosition().getZ(), 1.7F, Level.ExplosionInteraction.NONE);
        }
        this.spawnRift(blockPosition());
        this.discard();
        super.onHit(pResult);
    }
    private void spawnRift(BlockPos blockPos) {
        if(owner != null){
            this.level().addFreshEntity(new RiftEntity(this.level(), blockPos.getX(), blockPos.getY()+1, blockPos.getZ(), this.owner, 140, 1.7F));

        }
    }
}
