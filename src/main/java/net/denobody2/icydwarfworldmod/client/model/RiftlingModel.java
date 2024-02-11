package net.denobody2.icydwarfworldmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.entity.Gooblino;
import net.denobody2.icydwarfworldmod.common.entity.Riftling;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class RiftlingModel extends GeoModel<Riftling> {
    @Override
    public ResourceLocation getModelResource(Riftling animatable) {
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "geo/entity/riftling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Riftling animatable) {
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "textures/entity/riftling.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Riftling animatable) {
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "animations/entity/riftling.animation.json");
    }

    @Override
    public void setCustomAnimations(Riftling animatable, long instanceId, AnimationState<Riftling> animationState) {

    }
}
