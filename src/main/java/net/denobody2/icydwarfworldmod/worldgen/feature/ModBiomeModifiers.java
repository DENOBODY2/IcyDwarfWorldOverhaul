package net.denobody2.icydwarfworldmod.worldgen.feature;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {


    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_DEIRUM_ORE = registerKey("add_deepslate_deirum_ore");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);



        context.register(ADD_DEEPSLATE_DEIRUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_DEIRUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));




    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
    }
}
