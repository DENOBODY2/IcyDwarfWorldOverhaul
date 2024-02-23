package net.denobody2.icydwarfworldmod.worldgen.biome;

import com.google.common.collect.ImmutableList;
import net.denobody2.icydwarfworldmod.config.BiomeGenerationConfig;
import net.denobody2.icydwarfworldmod.util.VoronoiGenerator;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class ModBiomeRarity {
    private static long lastTestedSeed = 0;
    private static PerlinSimplexNoise noiseX;
    private static PerlinSimplexNoise noiseZ;
    private static VoronoiGenerator voronoiGenerator;

    private static final List<Integer> BIOME_OCTAVES = ImmutableList.of(0);

    // ran for every chunk x z
    // gets the center of the nearest biome pertaining to the rarityOffset. Result is in quad coordinates.
    @Nullable
    public static Vec3 getRareBiomeCenterQuad(long worldSeed, int rarityOffset, int x, int z) {
        //start of code to initialize noise for world
        if (lastTestedSeed != worldSeed || voronoiGenerator == null || noiseX == null || noiseZ == null) {
            lastTestedSeed = worldSeed;
            noiseX = new PerlinSimplexNoise(new XoroshiroRandomSource(1234L + worldSeed), BIOME_OCTAVES);
            noiseZ = new PerlinSimplexNoise(new XoroshiroRandomSource(4321L + worldSeed), BIOME_OCTAVES);
            voronoiGenerator = new VoronoiGenerator(worldSeed);
            voronoiGenerator.setOffsetAmount(0.2);
        }
        //in blocks
        double biomeSize = 150 * 0.25D;
        double seperationDistance = biomeSize + 700 * 0.25D;
        double sampleX = x / seperationDistance;
        double sampleZ = z / seperationDistance;
        double positionOffsetX = 0.1 * noiseX.getValue(sampleX, sampleZ, false);
        double positionOffsetZ = 0.1 * noiseZ.getValue(sampleX, sampleZ, false);
        VoronoiGenerator.VoronoiInfo info = voronoiGenerator.get2(sampleX + positionOffsetX, sampleZ + positionOffsetZ);
        if (info.distance() < (biomeSize / seperationDistance)) {
            double scaledHash = ((info.hash() + 1D) * 0.5D) * (double) BiomeGenerationConfig.getBiomeCount();
            if((int) scaledHash == rarityOffset){
                return info.cellPos().scale(seperationDistance);
            }
        }
        return null;
    }
}
