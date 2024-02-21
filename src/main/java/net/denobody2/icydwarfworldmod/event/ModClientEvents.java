package net.denobody2.icydwarfworldmod.event;

import net.denobody2.icydwarfworldmod.ClientProxy;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.client.model.layer.ModModelLayers;
import net.denobody2.icydwarfworldmod.client.particle.FallingAshParticle;
import net.denobody2.icydwarfworldmod.client.renderer.GooblinoRenderer;
import net.denobody2.icydwarfworldmod.client.renderer.ModBoatRenderer;
import net.denobody2.icydwarfworldmod.client.renderer.RiftRenderer;
import net.denobody2.icydwarfworldmod.client.renderer.RiftlingRenderer;
import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.registry.ModBlockEntities;
import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.denobody2.icydwarfworldmod.registry.ModParticles;
import net.denobody2.icydwarfworldmod.util.BiomeSampler;
import net.denobody2.icydwarfworldmod.util.ModWoodTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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
            EntityRenderers.register(ModEntities.RIFT.get(), RiftRenderer::new);
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
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                ClientProxy.lastBiomeLightColorPrev = ClientProxy.lastBiomeLightColor;
                ClientProxy.lastBiomeLightColor = calculateBiomeLightColor(player);
                ClientProxy.lastBiomeAmbientLightAmountPrev = ClientProxy.lastBiomeAmbientLightAmount;
                ClientProxy.lastBiomeAmbientLightAmount = calculateBiomeAmbientLight(player);
            }
        }
    }
    @SubscribeEvent
    public static void setUpParticles(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ModParticles.FALLING_ASH.get(), FallingAshParticle.Factory::new);
    }
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.MANDARIN_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.MANDARIN_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }


    public static float calculateBiomeAmbientLight(Player player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return ModBiomes.getBiomeAmbientLight(player.level().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesFloat(player.level(), player.position(), ModBiomes::getBiomeAmbientLight);
        }
    }

    public static Vec3 calculateBiomeLightColor(Player player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return ModBiomes.getBiomeLightColorOverride(player.level().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesVec3(player.level(), player.position(), ModBiomes::getBiomeLightColorOverride);
        }
    }
    @SubscribeEvent
    public void fogColor(ViewportEvent.ComputeFogColor event) {
        Entity player = Minecraft.getInstance().player;
        BlockState blockState = player.level().getBlockState(event.getCamera().getBlockPosition());
        if (event.getCamera().getFluidInCamera() == FogType.NONE) {
            int i = Minecraft.getInstance().options.biomeBlendRadius().get();
            float override;
            if (i == 0) {
                override = ModBiomes.getBiomeSkyOverride(player.level().getBiome(player.blockPosition()));
            } else {
                override = BiomeSampler.sampleBiomesFloat(player.level(), player.position(), ModBiomes::getBiomeSkyOverride);
            }
            float setR = event.getRed();
            float setG = event.getGreen();
            float setB = event.getBlue();

            boolean flag = false;
            if (override != 0.0F) {
                flag = true;
                Vec3 vec3;
                if (i == 0) {
                    vec3 = ((ClientLevel) player.level()).effects().getBrightnessDependentFogColor(Vec3.fromRGB24(player.level().getBiomeManager().getNoiseBiomeAtPosition(player.blockPosition()).value().getFogColor()), 1.0F);
                } else {
                    vec3 = ((ClientLevel) player.level()).effects().getBrightnessDependentFogColor(BiomeSampler.sampleBiomesVec3(player.level(), player.position(), biomeHolder -> Vec3.fromRGB24(biomeHolder.value().getFogColor())), 1.0F);
                }
                setR = (float) (vec3.x - setR) * override + setR;
                setG = (float) (vec3.y - setG) * override + setG;
                setB = (float) (vec3.z - setB) * override + setB;
            }
            if (flag) {
                event.setRed(setR);
                event.setGreen(setG);
                event.setBlue(setB);
            }
        } else if (event.getCamera().getFluidInCamera() == FogType.WATER) {
            int i = Minecraft.getInstance().options.biomeBlendRadius().get();
            float override;
            if (i == 0) {
                override = ModBiomes.getBiomeSkyOverride(player.level().getBiome(player.blockPosition()));
            } else {
                override = BiomeSampler.sampleBiomesFloat(player.level(), player.position(), ModBiomes::getBiomeSkyOverride);
            }
            if (override != 0) {
                Vec3 vec3 = Vec3.fromRGB24(player.level().getBiomeManager().getNoiseBiomeAtPosition(player.blockPosition()).value().getWaterFogColor());
                event.setRed((float) (event.getRed() + (vec3.x - event.getRed()) * override));
                event.setGreen((float) (event.getGreen() + (vec3.y - event.getGreen()) * override));
                event.setBlue((float) (event.getBlue() + (vec3.z - event.getBlue()) * override));
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void fogRender(ViewportEvent.RenderFog event) {
        Entity player = Minecraft.getInstance().getCameraEntity();
        FluidState fluidstate = player.level().getFluidState(event.getCamera().getBlockPosition());
        BlockState blockState = player.level().getBlockState(event.getCamera().getBlockPosition());
        if (event.getCamera().getFluidInCamera() == FogType.WATER) {
            int i = Minecraft.getInstance().options.biomeBlendRadius().get();
            float farness;
            if (i == 0) {
                farness = ModBiomes.getBiomeWaterFogFarness(player.level().getBiome(player.blockPosition()));
            } else {
                farness = BiomeSampler.sampleBiomesFloat(player.level(), player.position(), ModBiomes::getBiomeWaterFogFarness);
            }
            if (farness != 1.0F) {
                event.setCanceled(true);
                event.setFarPlaneDistance(event.getFarPlaneDistance() * farness);
            }
        } else if (event.getMode() == FogRenderer.FogMode.FOG_TERRAIN) {
            int i = Minecraft.getInstance().options.biomeBlendRadius().get();
            float nearness;
            if (i == 0) {
                nearness = ModBiomes.getBiomeFogNearness(player.level().getBiome(player.blockPosition()));
            } else {
                nearness = BiomeSampler.sampleBiomesFloat(player.level(), player.position(), ModBiomes::getBiomeFogNearness);
            }
            boolean flag = nearness != 1.0F;
            if (flag) {
                event.setCanceled(true);
                event.setNearPlaneDistance(event.getNearPlaneDistance() * nearness);
            }
        }
    }

}
