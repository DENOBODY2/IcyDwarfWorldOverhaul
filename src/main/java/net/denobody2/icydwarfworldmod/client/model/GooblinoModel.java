package net.denobody2.icydwarfworldmod.client.model;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.entity.Gooblino;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class GooblinoModel extends GeoModel<Gooblino> {
    @Override
    public ResourceLocation getModelResource(Gooblino animatable) {
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "geo/entity/gooblino.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Gooblino animatable) {
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "textures/entity/gooblino.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Gooblino animatable) {
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "animations/entity/gooblino.animation.json");
    }

    @Override
    public void setCustomAnimations(Gooblino animatable, long instanceId, AnimationState<Gooblino> animationState) {

    }
}
