package net.denobody2.icydwarfworldmod.event;

import com.mojang.serialization.Dynamic;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.datafix.fixes.ChunkPalettedStorageFix;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IcyDwarfWorldMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {
}
