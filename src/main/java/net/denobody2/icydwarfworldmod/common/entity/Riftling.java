package net.denobody2.icydwarfworldmod.common.entity;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.entity.ai.RiftlingSwellGoal;
import net.denobody2.icydwarfworldmod.common.item.DeirumBlockItem;
import net.denobody2.icydwarfworldmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Collection;
import java.util.List;

public class Riftling extends PathfinderMob implements GeoEntity {
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.defineId(Riftling.class, EntityDataSerializers.INT);
    private float digestProgress;
    private float prevDigestProgress;
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 3;
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(Riftling.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(Riftling.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(Riftling.class, EntityDataSerializers.BOOLEAN);

    public static final ResourceLocation BARTER_LOOT = new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "gameplay/riftling_barter");

    public static final ResourceLocation CONVERT_LOOT = new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "gameplay/riftling_convert");
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
        this.entityData.define(DATA_IS_POWERED, false);
        this.entityData.define(DATA_IS_IGNITED, false);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.entityData.get(DATA_IS_POWERED)) {
            pCompound.putBoolean("powered", true);
        }
        pCompound.putShort("Fuse", (short)this.maxSwell);
        pCompound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        pCompound.putBoolean("ignited", this.isIgnited());
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_IS_POWERED, pCompound.getBoolean("powered"));
        if (pCompound.contains("Fuse", 99)) {
            this.maxSwell = pCompound.getShort("Fuse");
        }
        if (pCompound.contains("ExplosionRadius", 99)) {
            this.explosionRadius = pCompound.getByte("ExplosionRadius");
        }
        if (pCompound.getBoolean("ignited")) {
            this.ignite();
        }
    }
    public Riftling(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RiftlingSwellGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
    }
    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ZOMBIE_AMBIENT, 0.1F, 2.0F);
    }
    protected float getSoundVolume() {
        return 0.14F;
    }
    @Override
    protected void playHurtSound(DamageSource pSource) {
        this.playSound(SoundEvents.ZOMBIE_HURT, 0.3F, 2.0F);
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return 2.0F;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            Entity entity = pSource.getEntity();
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                pAmount = (pAmount + 1.0F) / 2.0F;
            }
            return super.hurt(pSource, pAmount);
        }
    }
    public boolean doHurtTarget(Entity pEntity) {
        return true;
    }
    public float getSwelling(float pPartialTicks) {
        return Mth.lerp(pPartialTicks, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }
    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setSwellDir(int pState) {
        this.entityData.set(DATA_SWELL_DIR, pState);
    }
    public static boolean isDarkEnoughToSpawn(ServerLevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBrightness(LightLayer.SKY, pPos) > pRandom.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = pLevel.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && pLevel.getBrightness(LightLayer.BLOCK, pPos) > i) {
                return false;
            } else {
                int j = pLevel.getLevel().isThundering() ? pLevel.getMaxLocalRawBrightness(pPos, 10) : pLevel.getMaxLocalRawBrightness(pPos);
                return j <= dimensiontype.monsterSpawnLightTest().sample(pRandom);
            }
        }
    }
    private void digestItem() {
        if (!level().isClientSide) {
            this.setDeltaMovement(0.0F, this.getDeltaMovement().y, 0.0F);
            if(this.getMainHandItem().getItem() == Items.MAGMA_CREAM){
                LootTable loottable = level().getServer().getLootData().getLootTable(BARTER_LOOT);
                List<ItemStack> items = loottable.getRandomItems((new LootParams.Builder((ServerLevel) this.level())).withParameter(LootContextParams.THIS_ENTITY, this).create(LootContextParamSets.PIGLIN_BARTER));
                items.forEach(this::spawnAtLocation);
            }
            if(this.getMainHandItem().getItem() instanceof DeirumBlockItem){
                LootTable loottable = level().getServer().getLootData().getLootTable(CONVERT_LOOT);
                List<ItemStack> items = loottable.getRandomItems((new LootParams.Builder((ServerLevel) this.level())).withParameter(LootContextParams.THIS_ENTITY, this).create(LootContextParamSets.PIGLIN_BARTER));
                items.forEach(this::spawnAtLocation);
            }
        }
        this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
    }
    public boolean isDigesting() {
        return this.getItemInHand(InteractionHand.MAIN_HAND).is(ModTags.Items.RIFTLING_DIGESTS);
    }
    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        final ItemStack itemstack = pPlayer.getItemInHand(pHand);
        final InteractionResult type = super.mobInteract(pPlayer, pHand);
        if (itemstack.is(ModTags.Items.RIFTLING_DIGESTS) && !this.isDigesting() && !type.consumesAction()) {
            ItemStack copy = itemstack.copy();
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            copy.setCount(1);
            this.setItemInHand(InteractionHand.MAIN_HAND, copy);
            this.setDeltaMovement(0,this.getDeltaMovement().y,0);
            return InteractionResult.SUCCESS;
        }
        if (itemstack.is(ModTags.Items.RIFTLING_IGNITERS)) {
            SoundEvent soundevent = itemstack.is(Items.DRAGON_BREATH) ? SoundEvents.ENDER_DRAGON_SHOOT : SoundEvents.FLINTANDSTEEL_USE;
            this.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide) {
                this.ignite();
                if (!itemstack.isDamageableItem()) {
                    itemstack.shrink(1);
                } else {
                    itemstack.hurtAndBreak(1, pPlayer, (p_32290_) -> {
                        p_32290_.broadcastBreakEvent(pHand);
                    });
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return type;
    }
    public float getDigestProgress(float partialTick) {
        return Math.min(1.0F, (prevDigestProgress + (digestProgress - prevDigestProgress) * partialTick));
    }
    private void explodeRiftling() {
        if (!this.level().isClientSide) {
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, Level.ExplosionInteraction.MOB);
            this.discard();
            //this.spawnLingeringCloud();
        }

    }
    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

            for(MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }

            this.level().addFreshEntity(areaeffectcloud);
        }

    }
    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }
    @Override
    public void tick() {
        super.tick();
        prevDigestProgress = digestProgress;
        if (isDigesting() && digestProgress < 1.0F) {
            this.setDeltaMovement(0, 0,0);
            if(digestProgress == 0.0F){
                this.playSound(SoundEvents.ENDERMAN_TELEPORT);
            }
            digestProgress += 0.05F;
            if (digestProgress >= 1.0F) {
                digestProgress = 0;
                prevDigestProgress = 0;
                this.digestItem();
            }
        }
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.explodeRiftling();
            }
        }
    }
    public boolean isPowered() {
        return this.entityData.get(DATA_IS_POWERED);
    }
    public int getMaxSpawnClusterSize() {
        return 3;
    }

    public static boolean checkRiftlingSpawnRules(EntityType<Riftling> pRiftling, ServerLevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(ModTags.Blocks.RIFTLING_SPAWNABLE_ON) && isDarkEnoughToSpawn(pLevel, pPos, pRandom)  && pPos.below().getY() < 0;
    }
    public boolean canBeLeashed(Player pPlayer) {
        return super.canBeLeashed(pPlayer);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 2, this::predicate));
    }
    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else if(this.isDigesting()){
            state.getController().setAnimation(RawAnimation.begin().then("eat", Animation.LoopType.PLAY_ONCE));
        }
        else {
            state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
