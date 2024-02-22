package net.denobody2.icydwarfworldmod.config;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.event.EventReplaceBiome;
import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.worldgen.biome.BiomeGenerationNoiseCondition;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BiomeGenerationConfig {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    private static final String OVERWORLD = "minecraft:overworld";

    private static final BiomeGenerationNoiseCondition RIFTLING_GROTTO_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(OVERWORLD).distanceFromSpawn(350).idwmRarityOffset(0).continentalness(0.4F, 1F).depth(0.6F, 1.5F).build();
    private static Map<ResourceKey<Biome>, BiomeGenerationNoiseCondition> biomes = new HashMap<>();

    public static void reloadConfig() {
        biomes.put(ModBiomes.RIFTLING_GROTTO, getConfigData("riftling_grotto", RIFTLING_GROTTO_CONDITION));
    }

    @Nullable
    public static ResourceKey<Biome> getBiomeForEvent(EventReplaceBiome event) {
        for (Map.Entry<ResourceKey<Biome>, BiomeGenerationNoiseCondition> condition : biomes.entrySet()) {
            if (condition.getValue().test(event)) {
                return condition.getKey();
            }
        }
        return null;
    }

    public static int getBiomeCount() {
        if(biomes.size() != 0){
            return biomes.size();
        }
        return 1;
    }


    private static <T> T getOrCreateConfigFile(File configDir, String configName, T defaults, Type type, Predicate<T> isInvalid) {
        File configFile = new File(configDir, configName + ".json");
        if (!configFile.exists()) {
            try {
                FileUtils.write(configFile, GSON.toJson(defaults));
            } catch (IOException e) {
                Citadel.LOGGER.error("Biome Generation Config: Could not write " + configFile, e);
            }
        }
        try {
            T found = GSON.fromJson(FileUtils.readFileToString(configFile), type);
            if (isInvalid.test(found)) {
                Citadel.LOGGER.warn("Old Biome Generation Config format found for " + configName + ", replacing with new one.");
                try {
                    FileUtils.write(configFile, GSON.toJson(defaults));
                } catch (IOException e) {
                    Citadel.LOGGER.error("Biome Generation Config: Could not write " + configFile, e);
                }
            } else {
                return found;
            }
        } catch (Exception e) {
            Citadel.LOGGER.error("Biome Generation Config: Could not load " + configFile, e);
        }

        return defaults;
    }

    private static File getConfigDirectory() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path jsonPath = Paths.get(configPath.toAbsolutePath().toString(),"idwm_biome_generation");
        return jsonPath.toFile();
    }

    private static BiomeGenerationNoiseCondition getConfigData(String fileName, BiomeGenerationNoiseCondition defaultConfigData) {
        BiomeGenerationNoiseCondition configData = getOrCreateConfigFile(getConfigDirectory(), fileName, defaultConfigData, new TypeToken<BiomeGenerationNoiseCondition>() {
        }.getType(), BiomeGenerationNoiseCondition::isInvalid);
        return configData;
    }
}
