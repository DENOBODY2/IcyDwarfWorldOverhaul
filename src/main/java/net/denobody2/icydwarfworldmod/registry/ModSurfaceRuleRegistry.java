package net.denobody2.icydwarfworldmod.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.util.ModMath;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSurfaceRuleRegistry {
    public static final DeferredRegister<Codec<? extends SurfaceRules.ConditionSource>> SURFACE_RULES = DeferredRegister.create(Registries.MATERIAL_CONDITION, IcyDwarfWorldMod.MOD_ID);

    public static final RegistryObject<Codec<? extends SurfaceRules.ConditionSource>> SIMPLEX_CONDITION = SURFACE_RULES.register("simplex", () -> SimplexConditionSource.CODEC.codec());
    public static SurfaceRules.ConditionSource simplexCondition(float noiseMin, float noiseMax, float noiseScale, float yScale, int offsetType) {
        return new SimplexConditionSource(noiseMin, noiseMax, noiseScale, yScale, offsetType);
    }

    private record SimplexConditionSource(float noiseMin, float noiseMax, float noiseScale, float yScale,
                                          int offsetType) implements SurfaceRules.ConditionSource {
        private static final KeyDispatchDataCodec<SimplexConditionSource> CODEC = KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec((group) -> {
            return group.group(Codec.floatRange(-1F, 1F).fieldOf("noise_min").forGetter(SimplexConditionSource::noiseMin), Codec.floatRange(-1F, 1F).fieldOf("noise_max").forGetter(SimplexConditionSource::noiseMax), Codec.floatRange(1F, 10000F).fieldOf("noise_scale").forGetter(SimplexConditionSource::noiseScale), Codec.floatRange(0F, 10000F).fieldOf("y_scale").forGetter(SimplexConditionSource::yScale), Codec.intRange(0, 128).fieldOf("offset_type").forGetter(SimplexConditionSource::offsetType)).apply(group, SimplexConditionSource::new);
        }));

        public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
            return CODEC;
        }

        public SurfaceRules.Condition apply(final SurfaceRules.Context contextIn) {
            class NoiseCondition implements SurfaceRules.Condition {

                private SurfaceRules.Context context;

                NoiseCondition(SurfaceRules.Context context) {
                    this.context = context;
                }

                public boolean test() {
                    double f = ModMath.sampleNoise3D(context.blockX + (offsetType * 1000), (int) ((context.blockY * yScale + offsetType * 2000)), context.blockZ - (offsetType * 3000), SimplexConditionSource.this.noiseScale);
                    return f > SimplexConditionSource.this.noiseMin && f <= SimplexConditionSource.this.noiseMax;
                }
            }
            return new NoiseCondition(contextIn);
        }
    }
}
