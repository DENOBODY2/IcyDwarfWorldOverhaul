package net.denobody2.icydwarfworldmod.worldgen.feature;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.denobody2.icydwarfworldmod.util.ModTags;
import net.denobody2.icydwarfworldmod.worldgen.feature.config.VeinConfig;
import net.denobody2.icydwarfworldmod.worldgen.feature.feature.VeinFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_DEIRUM_ORE_KEY = registerKey("deepslate_deirum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ETHEREAL_GRASS = registerKey("ethereal_grass");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWER_BULB = registerKey("glower_bulb");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_PIECE_OF_ETHEREAL_GRASS = registerKey("single_piece_of_ethereal_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHADOW_GEM_ORE_KEY = registerKey("shadow_gem_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANDARIN_TREE_KEY = registerKey("mandarin_tree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest shadowReplaceabeles = new TagMatchTest(ModTags.Blocks.SHADOW_GEM_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreConfiguration.TargetBlockState> overworldDeirumOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        Blocks.STONE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_DEIRUM_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> shadowGemOres = List.of(OreConfiguration.target(shadowReplaceabeles,
                        ModBlocks.SHADOW_GEM_ORE.get().defaultBlockState()));



        register(context, DEEPSLATE_DEIRUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldDeirumOres, 9, 0.0f));
        register(context, SHADOW_GEM_ORE_KEY, Feature.ORE, new OreConfiguration(shadowGemOres, 9, 0.0f));
        register(context, MANDARIN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MANDARIN_LOG.get()),
                new ForkingTrunkPlacer(3, 4, 3),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(ModBlocks.MANDARIN_LEAVES.get().defaultBlockState(), 3).add(ModBlocks.FLOWERED_MANDARIN_LEAVES.get().defaultBlockState(), 1)),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 2)).build());




    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
