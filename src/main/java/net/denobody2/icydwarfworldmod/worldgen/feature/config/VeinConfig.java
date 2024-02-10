package net.denobody2.icydwarfworldmod.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
public record VeinConfig(BlockStateProvider blockStateProvider) implements FeatureConfiguration {
    public static final Codec<VeinConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(BlockStateProvider.CODEC.fieldOf("state")
                    .forGetter(VeinConfig::blockStateProvider))
                    .apply(instance, VeinConfig::new));
}