package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IcyDwarfWorldMod.MOD_ID);

    public static final RegistryObject<EntityType<Gooblino>> GOOBLINO =
            ENTITY_TYPES.register("gooblino", () -> EntityType.Builder.of(Gooblino::new, MobCategory.CREATURE)
                    .sized(1.5F, 1.0F)
                    .setTrackingRange(16)
                    .updateInterval(1)
                    .build("gooblino"));
    public static final RegistryObject<EntityType<Riftling>> RIFTLING =
            ENTITY_TYPES.register("riftling", () -> EntityType.Builder.of(Riftling::new, MobCategory.MONSTER)
                    .sized(1.4F, 1.5F)
                    .setTrackingRange(16)
                    .updateInterval(1)
                    .build("riftling"));
    public static final RegistryObject<EntityType<ShadowBallProjectile>> SHADOW_BALL_PROJECTILE =
            ENTITY_TYPES.register("shadow_ball_projectile",
                    () -> EntityType.Builder.<ShadowBallProjectile>of(ShadowBallProjectile::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(1)
                            .setCustomClientFactory((spawnEntity, level) -> new ShadowBallProjectile(level))
                            .build("shadow_ball_projectile"));

    public static final RegistryObject<EntityType<RiftEntity>> RIFT = ENTITY_TYPES.register("rift", () -> EntityType.Builder.<RiftEntity>of(RiftEntity::new, MobCategory.MISC)
            .sized(1.0F, 1.0F)
            .fireImmune()
            .clientTrackingRange(10).
            updateInterval(Integer.MAX_VALUE)
            .build("rift"));
    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));

    public static final RegistryObject<EntityType<FallingAshEntity>> FALLING_ASH = ENTITY_TYPES.register("falling_ash", () -> (EntityType) EntityType.Builder.<FallingAshEntity>of(FallingAshEntity::new, MobCategory.MISC).sized(0.8F, 0.9F).setCustomClientFactory(FallingAshEntity::new).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).updateInterval(10).clientTrackingRange(20).build("falling_ash"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
