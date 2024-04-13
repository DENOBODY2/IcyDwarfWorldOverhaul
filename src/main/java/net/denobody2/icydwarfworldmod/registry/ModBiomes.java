package net.denobody2.icydwarfworldmod.registry;

import com.github.alexthe666.citadel.server.world.ExpandedBiomes;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.util.BiomeSampler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.Vec3;

public class ModBiomes {
    public static final ResourceKey<Biome> RIFTLING_GROTTO = register("riftling_grotto");
    private static final Vec3 DEFAULT_LIGHT_COLOR = new Vec3(1, 1, 1);
    private static final Vec3 RIFTLING_GROTTO_LIGHT_COLOR = new Vec3(0.5, 0.1, 0.9);
    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
    }
    public static void init(){
        ExpandedBiomes.addExpandedBiome(ModBiomes.RIFTLING_GROTTO, LevelStem.OVERWORLD);
    }
    public static float getBiomeAmbientLight(Holder<Biome> value) {
        if (value.is(RIFTLING_GROTTO)) {
            return 0.1F;
        }
        return 0.0F;
    }
    public static float getBiomeFogNearness(Holder<Biome> value) {
        if (value.is(RIFTLING_GROTTO)) {
            return -0.36F;
        }
        return 1.0F;
    }

    public static float getBiomeWaterFogFarness(Holder<Biome> value) {
        /*if (value.is(ABYSSAL_CHASM)) {
            return 0.5F;
        }*/
        return 1.0F;
    }

    public static float getBiomeSkyOverride(Holder<Biome> value) {
        if (value.is(RIFTLING_GROTTO)) {
            return 1.0F;
        }
        return 0.0F;
    }

    public static Vec3 getBiomeLightColorOverride(Holder<Biome> value) {
        if (value.is(RIFTLING_GROTTO)) {
            return RIFTLING_GROTTO_LIGHT_COLOR;
        }
        return DEFAULT_LIGHT_COLOR;
    }


    public static float calculateBiomeSkyOverride(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return ModBiomes.getBiomeSkyOverride(player.level().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesFloat(player.level(), player.position(), ModBiomes::getBiomeSkyOverride);
        }
    }
}
