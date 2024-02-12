package net.denobody2.icydwarfworldmod.worldgen.feature.feature;

import com.mojang.serialization.Codec;
import net.denobody2.icydwarfworldmod.util.ModTags;
import net.denobody2.icydwarfworldmod.util.FastNoise;
import net.denobody2.icydwarfworldmod.worldgen.feature.config.VeinConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class VeinFeature extends Feature<VeinConfig> {
    public VeinFeature(Codec<VeinConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<VeinConfig> featurePlaceContext) {
        WorldGenLevel world = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource randomSource = featurePlaceContext.random();
        int seed = (int) world.getSeed();
        FastNoise fastNoise = new FastNoise(seed);
        fastNoise.SetFractalOctaves(1);
        fastNoise.SetFrequency(0.01F);
        fastNoise.SetFractalLacunarity(0.21F);
        fastNoise.SetFractalType(FastNoise.FractalType.RigidMulti);
        fastNoise.SetNoiseType(FastNoise.NoiseType.SimplexFractal);
        int range = 16;
        int yRange = range / 2;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                for (int l = -yRange; l < yRange; l++) {
                    mutableBlockPos.set(blockPos.getX() + i, blockPos.getY() + l, blockPos.getZ() + j);
                    int y1 = mutableBlockPos.getY();
                    y1 /= 0.5D;
                    double noise = fastNoise.GetNoise(mutableBlockPos.getX(), y1, mutableBlockPos.getZ());
                    if (noise > 0.9D) {
                        BlockState blockState = world.getBlockState(mutableBlockPos);
                        boolean replaceable = blockState.is(ModTags.Blocks.VERDANT_STONE_REPLACES);
                        boolean dontReplace = blockState.is(BlockTags.GEODE_INVALID_BLOCKS);
                        if (replaceable && !dontReplace) {
                            world.setBlock(mutableBlockPos, featurePlaceContext.config().blockStateProvider().getState(randomSource, mutableBlockPos), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}
