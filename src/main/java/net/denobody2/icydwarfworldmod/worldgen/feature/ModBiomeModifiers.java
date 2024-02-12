package net.denobody2.icydwarfworldmod.worldgen.feature;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.denobody2.icydwarfworldmod.util.ModTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {


    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_DEIRUM_ORE = registerKey("add_deepslate_deirum_ore");
    public static final ResourceKey<BiomeModifier> ADD_MANDARIN_TREE = registerKey("add_mandarin_tree");
    public static final ResourceKey<BiomeModifier> SPAWN_GOOBLINO = registerKey("spawn_gooblino");

    public static final ResourceKey<BiomeModifier> SPAWN_RIFTLING = registerKey("spawn_riftling");
    public static final ResourceKey<BiomeModifier> ADD_VERDANT_STONE_VEIN = registerKey("add_verdant_stone_vein");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);



        context.register(ADD_DEEPSLATE_DEIRUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_DEIRUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_VERDANT_STONE_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.ALLOWS_TROPICAL_FISH_SPAWNS_AT_ANY_HEIGHT),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VERDANT_STONE_VEIN_PLACED)),
                GenerationStep.Decoration.RAW_GENERATION));

        context.register(ADD_MANDARIN_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.MANDARIN_SPAWNS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MANDARIN_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(SPAWN_GOOBLINO, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.MANDARIN_SPAWNS),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GOOBLINO.get(), 12, 2, 3))));

        context.register(SPAWN_RIFTLING, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.RIFTLING.get(), 3, 1, 3))));




    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
    }
}
