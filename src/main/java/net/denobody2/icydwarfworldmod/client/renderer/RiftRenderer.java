package net.denobody2.icydwarfworldmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.client.model.RiftlingModel;
import net.denobody2.icydwarfworldmod.common.entity.RiftEntity;
import net.denobody2.icydwarfworldmod.common.entity.Riftling;
import net.denobody2.icydwarfworldmod.util.ModRenderTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RiftRenderer extends EntityRenderer<RiftEntity> {
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_one.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_one.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_two.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_three.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_four.png");
    private static final ResourceLocation TEXTURE_5 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_five.png");
    private static final ResourceLocation TEXTURE_6 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_six.png");
    private static final ResourceLocation TEXTURE_7 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_seven.png");
    private static final ResourceLocation TEXTURE_8 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_eight.png");
    private static final ResourceLocation TEXTURE_9 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_nine.png");
    private static final ResourceLocation TEXTURE_10 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_ten.png");
    private static final ResourceLocation TEXTURE_11 = new ResourceLocation("icydwarfworldmod:textures/entity/rift/rift_eleven.png");

    public RiftRenderer(EntityRendererProvider.Context mgr) {
        super(mgr);
    }
    public void render(RiftEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        float matrixScale = 0;
        ResourceLocation tex = this.getIdleTexture(entityIn.tickCount % 63);
        if(entityIn.getLifespan() > 200){
            matrixScale = 4;
        } else {
            matrixScale = entityIn.getLifespan() / 66F;
            if(matrixScale > 4){
                matrixScale = 4;
            }
        }
        matrixStackIn.pushPose();
        matrixStackIn.scale(matrixScale, matrixScale, matrixScale);
        matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = matrixStackIn.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = bufferIn.getBuffer(ModRenderTypes.getfullBright(tex));
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
        p_114090_.vertex(p_114091_, p_114094_ - 0.5F, (float)p_114095_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)p_114096_, (float)p_114097_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114093_).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
    }
    @Override
    public ResourceLocation getTextureLocation(RiftEntity entity) {
        return TEXTURE_0;
    }
    public ResourceLocation getIdleTexture(int age) {
        if(age > 0 && age <= 3){
            return TEXTURE_1;
        } else if(age > 3 && age <= 6){
            return TEXTURE_2;
        } else if(age > 6 && age <= 9){
            return TEXTURE_3;
        } else if(age > 9 && age <= 12){
            return TEXTURE_4;
        } else if(age > 12 && age <= 15){
            return TEXTURE_5;
        } else if(age > 15 && age <= 18){
            return TEXTURE_6;
        } else if(age > 18 && age <= 21){
            return TEXTURE_7;
        } else if(age > 21 && age <= 24){
            return TEXTURE_8;
        } else if(age > 24 && age <= 27){
            return TEXTURE_9;
        } else if(age > 27 && age <= 30){
            return TEXTURE_10;
        } else if(age > 30 && age <= 33){
            return TEXTURE_11;
        } else if(age > 33 && age <= 36){
            return TEXTURE_10;
        } else if(age > 36 && age <= 39){
            return TEXTURE_9;
        } else if(age > 39 && age <= 42){
            return TEXTURE_8;
        } else if(age > 42 && age <= 45){
            return TEXTURE_7;
        } else if(age > 45 && age <= 48){
            return TEXTURE_6;
        } else if(age > 48 && age <= 51){
            return TEXTURE_5;
        } else if(age > 51 && age <= 54){
            return TEXTURE_4;
        } else if(age > 54 && age <= 57){
            return TEXTURE_3;
        } else if(age > 57 && age <= 60){
            return TEXTURE_2;
        } else if(age > 60 && age <= 63){
            return TEXTURE_1;
        }
        else {
            return TEXTURE_1;
        }
    }

}
