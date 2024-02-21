package net.denobody2.icydwarfworldmod;

import net.denobody2.icydwarfworldmod.event.ModClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {
    public static Vec3 lastBiomeLightColor = Vec3.ZERO;
    public static float lastBiomeAmbientLightAmount = 0;
    public static Vec3 lastBiomeLightColorPrev = Vec3.ZERO;
    public static float lastBiomeAmbientLightAmountPrev = 0;
    public static Vec3 processSkyColor(Vec3 colorIn, float partialTick){
        return colorIn;
    }
    public void clientInit(){
        MinecraftForge.EVENT_BUS.register(new ModClientEvents());
    }
    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }
}
