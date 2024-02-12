package net.denobody2.icydwarfworldmod.common.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.UUID;

public class RiftEntity extends Entity {
    protected static final EntityDataAccessor<Integer> LIFESPAN = SynchedEntityData.defineId(RiftEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> STAGE = SynchedEntityData.defineId(RiftEntity.class, EntityDataSerializers.INT);

    private boolean madeOpenNoise = false;
    private boolean madeCloseNoise = false;
    private boolean madeParticle = false;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    public int ambientSoundTime;
    public float explosionRadius;
    public boolean wasPlayerOwned;
    private final ObjectArrayList<BlockPos> toBlow = new ObjectArrayList<>();
    public RiftEntity(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public RiftEntity(Level worldIn, double x, double y, double z, LivingEntity casterIn, int lifeSpan, float explosionRadius) {
        this(ModEntities.RIFT.get(), worldIn);
        this.setOwner(casterIn);
        this.owner = casterIn;
        this.ownerUUID = casterIn.getUUID();
        this.setLifespan(lifeSpan);
        this.setPos(x, y, z);
        this.explosionRadius = explosionRadius;
        wasPlayerOwned = casterIn instanceof Player;

    }
    public void tick() {
        this.owner = getOwner();
        this.tickStage();
        super.tick();
        if(!madeOpenNoise){
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 0.4F, 1 + random.nextFloat() * 0.2F);
            madeOpenNoise = true;
        }

        for (Entity entity : this.level().getEntities(this, this.getBoundingBox().inflate(10))) {
            if(wasPlayerOwned){
                if(owner != null){
                    if (entity != owner && entity.getUUID() != ownerUUID) {
                        if (!(entity instanceof Player && ((Player) entity).getAbilities().invulnerable)) {
                            if (!isAlliedTo(entity)){
                                Vec3 diff = entity.position().subtract(this.position().add(0, 0, 0));
                                if (entity instanceof LivingEntity) {
                                    diff = diff.normalize().scale( getStage() * 0.015);
                                    entity.setDeltaMovement(entity.getDeltaMovement().subtract(diff));
                                } else {
                                    diff = diff.normalize().scale(getStage() * 0.045);
                                    entity.setDeltaMovement(entity.getDeltaMovement().subtract(diff));
                                }
                            }
                        }
                    }
                } else {
                    this.owner = getOwner();
                }
            } else {
                this.owner = getOwner();
                if (entity != this.owner && entity.getUUID() != this.ownerUUID) {
                    if (!(entity instanceof Player && ((Player) entity).getAbilities().invulnerable)) {
                        if (!isAlliedTo(entity)){
                            Vec3 diff = entity.position().subtract(this.position().add(0, 0, 0));
                            if (entity instanceof LivingEntity) {
                                diff = diff.normalize().scale( getStage() * 0.015);
                                entity.setDeltaMovement(entity.getDeltaMovement().subtract(diff));
                            } else {
                                diff = diff.normalize().scale(getStage() * 0.045);
                                entity.setDeltaMovement(entity.getDeltaMovement().subtract(diff));
                            }
                        }
                    }
                }
            }
        }

        for (LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D))) {
            this.damage(livingentity);
        }


        if (this.random.nextInt(400) < this.ambientSoundTime++) {
            this.playSound(SoundEvents.PORTAL_AMBIENT, 0.4F, 1 + random.nextFloat() * 0.2F);
            this.resetAmbientSoundTime();
        }
        if(this.getLifespan() > 5){
            for(int p = 0; p < 6; p++){
                this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(1D), this.getRandomY() - 0.25D, this.getRandomZ(1D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }


        this.setLifespan(this.getLifespan() - 1);
        if(this.getLifespan() <= 25){
            if(!madeCloseNoise){
                this.gameEvent(GameEvent.ENTITY_PLACE);
                this.playSound(SoundEvents.END_PORTAL_SPAWN, 0.2F, 1 + random.nextFloat() * 0.2F);
                madeCloseNoise = true;
            }
            if(this.tickCount % 10 == 0){
                this.setStage(this.getStage() - 1);
            }

            if (this.getStage() <= 0) {
                if(!madeParticle){
                    if (this.level().isClientSide) {
                        //this.level().addParticle(ModParticle.SHOCK_WAVE.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                    }else{
                        this.level().explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), explosionRadius, false, Level.ExplosionInteraction.NONE);
                    }
                    madeParticle = true;
                }else{
                    this.discard();
                }
            }
        }
    }
    public void tickStage(){
        this.setStage((int) this.getLifespan() / 40);
    }
    private void damage(LivingEntity Hitentity) {
        LivingEntity livingentity = this.getOwner();
        if (Hitentity.isAlive() && !Hitentity.isInvulnerable() && Hitentity != livingentity) {
            if (this.tickCount % 5 == 0) {
                if (livingentity == null) {
                    Hitentity.hurt(damageSources().magic(), 0.5f);
                } else {
                    if (livingentity.isAlliedTo(Hitentity)) {
                        return;
                    }
                    Hitentity.hurt(damageSources().indirectMagic(this, livingentity), 0.5F);
                }
            }
        }
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
    public int getAmbientSoundInterval() {
        return 80;
    }

    private void resetAmbientSoundTime() {
        this.ambientSoundTime = -this.getAmbientSoundInterval();
    }

    public int getLifespan() {
        return this.entityData.get(LIFESPAN);
    }

    public void setLifespan(int i) {
        this.entityData.set(LIFESPAN, i);
    }

    public int getStage() {
        return this.entityData.get(STAGE);
    }

    public void setStage(int i) {
        this.entityData.set(STAGE, i);
    }


    public void setOwner(LivingEntity livingEntity) {
        if(livingEntity != null){
            this.owner = livingEntity;
            this.ownerUUID = livingEntity.getUUID();
        }
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }
        return this.owner;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(LIFESPAN, 300);
        this.entityData.define(STAGE, 0);
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setLifespan(compound.getInt("Lifespan"));
        this.setStage(compound.getInt("Stage"));
        if (compound.hasUUID("Owner")) {
            this.ownerUUID = compound.getUUID("Owner");
        }

    }

    public boolean shouldRenderAtSqrDistance(double p_36837_) {
        double d0 = this.getBoundingBox().getSize() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 *= 64.0D;
        return p_36837_ < d0 * d0;
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Lifespan", getLifespan());
        compound.putInt("Stage", getStage());
        if (this.ownerUUID != null) {
            compound.putUUID("Owner", this.ownerUUID);
        }

    }

}
