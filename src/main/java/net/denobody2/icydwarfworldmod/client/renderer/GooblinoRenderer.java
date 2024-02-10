package net.denobody2.icydwarfworldmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.client.model.GooblinoModel;
import net.denobody2.icydwarfworldmod.common.entity.Gooblino;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GooblinoRenderer extends GeoEntityRenderer<Gooblino> {
    public GooblinoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GooblinoModel());
    }
    @Override
    public ResourceLocation getTextureLocation(Gooblino animatable) {
        //String s = ChatFormatting.stripFormatting(animatable.getName().getString());
        /*if ("Gooblington".equals(s) || "Sir Gooblington".equals(s)) {
            return new ResourceLocation(IcyDwarfWorldMod.MOD_ID,"textures/entity/gooblino_british.png");
        } else{
            return new ResourceLocation(IcyDwarfWorldMod.MOD_ID,"textures/entity/gooblino.png");
        }*/
        return new ResourceLocation(IcyDwarfWorldMod.MOD_ID,"textures/entity/gooblino.png");
    }
    @Override
    public void render(Gooblino entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        poseStack.pushPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }

}
