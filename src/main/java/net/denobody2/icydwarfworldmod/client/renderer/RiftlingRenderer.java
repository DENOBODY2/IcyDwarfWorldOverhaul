package net.denobody2.icydwarfworldmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.client.model.GooblinoModel;
import net.denobody2.icydwarfworldmod.client.model.RiftlingModel;
import net.denobody2.icydwarfworldmod.common.entity.Gooblino;
import net.denobody2.icydwarfworldmod.common.entity.Riftling;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.example.entity.ReplacedCreeperEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RiftlingRenderer extends GeoEntityRenderer<Riftling> {
    private ItemInHandRenderer itemInHandRenderer;
    public RiftlingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RiftlingModel());
        this.itemInHandRenderer = renderManager.getItemInHandRenderer();
    }
    @Override
    public ResourceLocation getTextureLocation(Riftling animatable) {
        String s = ChatFormatting.stripFormatting(animatable.getName().getString());
        if ("oscar".equals(s) || "Oscar".equals(s)) {
            return new ResourceLocation(IcyDwarfWorldMod.MOD_ID,"textures/entity/riftling_oscar.png");
        } else{
            return new ResourceLocation(IcyDwarfWorldMod.MOD_ID,"textures/entity/riftling.png");
        }
    }
    @Override
    public void render(Riftling entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isDigesting()) {
            ItemStack itemStack = entity.getItemInHand(InteractionHand.MAIN_HAND);
            float progress = entity.getDigestProgress(partialTick);
            float invProgress = 1F - progress;
            float scaleProgress = 1.5F - progress*1.5f;
            poseStack.pushPose();
            poseStack.translate(0F,  1.2+invProgress*0.4, 0F);
            poseStack.scale(scaleProgress, scaleProgress, scaleProgress);
            this.itemInHandRenderer.renderItem(entity, itemStack, ItemDisplayContext.GROUND, false, poseStack, bufferSource, packedLight);
            poseStack.popPose();
        }
        poseStack.scale(1.0F,1.0F,1.0F);
        poseStack.pushPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
    public int getPackedOverlay(Riftling animatable, float u, float partialTick) {
        return super.getPackedOverlay(animatable, this.getSwellOverlay((Riftling)this.animatable, u), partialTick);
    }

    protected float getSwellOverlay(Riftling entity, float u) {
        float swell = entity.getSwelling(u);
        return (int)(swell * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(swell, 0.5F, 1.0F);
    }
    public void preRender(PoseStack poseStack, Riftling animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        float swellFactor = ((Riftling)this.animatable).getSwelling(partialTick);
        float swellMod = 1.0F + Mth.sin(swellFactor * 100.0F) * swellFactor * 0.01F;
        swellFactor = (float)Math.pow((double)Mth.clamp(swellFactor, 0.0F, 1.0F), 3.0);
        float horizontalSwell = (1.0F + swellFactor * 0.4F) * swellMod;
        float verticalSwell = (1.0F + swellFactor * 0.1F) / swellMod;
        poseStack.scale(horizontalSwell, verticalSwell, horizontalSwell);
    }

}
