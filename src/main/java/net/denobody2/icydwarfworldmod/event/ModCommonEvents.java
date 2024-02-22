package net.denobody2.icydwarfworldmod.event;

import com.github.alexthe666.citadel.server.event.EventReplaceBiome;
import net.denobody2.icydwarfworldmod.config.BiomeGenerationConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCommonEvents {
    @SubscribeEvent
    public void onReplaceBiome(EventReplaceBiome event) {
        ResourceKey<Biome> biome = BiomeGenerationConfig.getBiomeForEvent(event);
        if (biome != null) {
            Holder<Biome> biomeHolder = event.getBiomeSource().getResourceKeyMap().get(biome);
            if (biomeHolder != null) {
                event.setResult(Event.Result.ALLOW);
                event.setBiomeToGenerate(biomeHolder);
            }
        }
    }
}
