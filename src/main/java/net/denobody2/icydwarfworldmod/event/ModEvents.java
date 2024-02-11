package net.denobody2.icydwarfworldmod.event;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.client.renderer.RiftlingRenderer;
import net.denobody2.icydwarfworldmod.common.entity.Gooblino;
import net.denobody2.icydwarfworldmod.common.entity.Riftling;
import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Mod.EventBusSubscriber(modid = IcyDwarfWorldMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent e) {
        e.put(ModEntities.GOOBLINO.get(), Gooblino.createAttributes().build());
        e.put(ModEntities.RIFTLING.get(), Riftling.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.GOOBLINO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Gooblino::checkGooblinoSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.RIFTLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Riftling::checkRiftlingSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
