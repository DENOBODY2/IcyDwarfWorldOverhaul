package net.denobody2.icydwarfworldmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AdjustableGlowingGeoLayer<T extends GeoAnimatable> extends AutoGlowingGeoLayer<T> {
    private float alpha = 1.0F;
    private float red = 1.0F;
    private float green = 1.0F;
    private float blue = 1.0F;


    public AdjustableGlowingGeoLayer(GeoRenderer<T> renderer) {
        super(renderer);
    }
    public AdjustableGlowingGeoLayer(GeoRenderer<T> renderer, float a) {
        super(renderer);
        alpha = a;
    }
    public AdjustableGlowingGeoLayer(GeoRenderer<T> renderer, float a, float r, float g, float b) {
        super(renderer);
        alpha = a;
        red = r;
        green = g;
        blue = b;
    }

    @Override
    public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType emissiveRenderType = this.getRenderType(animatable);
        this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, emissiveRenderType, bufferSource.getBuffer(emissiveRenderType), partialTick, 15728640, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
    }
}
