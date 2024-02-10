package net.denobody2.icydwarfworldmod.worldgen.feature.tree;

import net.denobody2.icydwarfworldmod.worldgen.feature.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.lang.module.Configuration;

public class MandarinTreeGrower extends AbstractTreeGrower {
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers){
        return ModConfiguredFeatures.MANDARIN_TREE_KEY;
    }
}
