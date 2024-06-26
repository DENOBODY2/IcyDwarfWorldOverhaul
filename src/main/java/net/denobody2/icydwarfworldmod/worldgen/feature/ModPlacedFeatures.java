package net.denobody2.icydwarfworldmod.worldgen.feature;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEEPSLATE_DEIRUM_ORE_PLACED_KEY = registerKey("deepslate_deirum_ore_placed");
    public static final ResourceKey<PlacedFeature> SHADOW_GEM_ORE_PLACED_KEY = registerKey("shadow_gem_ore_placed");
    public static final ResourceKey<PlacedFeature> ETHEREAL_GRASS_PLACED = registerKey("ethereal_grass_placed");
    public static final ResourceKey<PlacedFeature> GLOWER_BULB_PLACED = registerKey("glower_bulb_placed");

    public static final ResourceKey<PlacedFeature> ETHEREAL_GRASS_BONEMEAL = registerKey("ethereal_grass_bonemeal");
    public static final ResourceKey<PlacedFeature> VERDANT_STONE_VEIN_PLACED = registerKey("verdant_stone_vein");
    public static final ResourceKey<PlacedFeature> MANDARIN_TREE_PLACED_KEY = registerKey("mandarin_tree_placed");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);



        register(context, DEEPSLATE_DEIRUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_DEIRUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-12))));
        register(context, SHADOW_GEM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SHADOW_GEM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(14,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));

        register(context, MANDARIN_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MANDARIN_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.MANDARIN_SAPLING.get()));


    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
