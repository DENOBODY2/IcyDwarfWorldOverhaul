package net.denobody2.icydwarfworldmod.event;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.client.model.layer.ModModelLayers;
import net.denobody2.icydwarfworldmod.client.particle.FallingAshParticle;
import net.denobody2.icydwarfworldmod.client.renderer.GooblinoRenderer;
import net.denobody2.icydwarfworldmod.client.renderer.ModBoatRenderer;
import net.denobody2.icydwarfworldmod.client.renderer.RiftlingRenderer;
import net.denobody2.icydwarfworldmod.registry.ModBlockEntities;
import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.denobody2.icydwarfworldmod.registry.ModParticles;
import net.denobody2.icydwarfworldmod.util.ModWoodTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IcyDwarfWorldMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.MANDARIN);
            EntityRenderers.register(ModEntities.GOOBLINO.get(), GooblinoRenderer::new);
            EntityRenderers.register(ModEntities.RIFTLING.get(), RiftlingRenderer::new);
            EntityRenderers.register(ModEntities.SHADOW_BALL_PROJECTILE.get(), ThrownItemRenderer::new);
        });
    }
    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
        EntityRenderers.register(ModEntities.FALLING_ASH.get(), FallingBlockRenderer::new);
    }
    @SubscribeEvent
    public static void setUpParticles(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ModParticles.FALLING_ASH.get(), FallingAshParticle.Factory::new);
    }
    @SubscribeEvent
    public static void registerLyaers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.MANDARIN_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.MANDARIN_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }
}
