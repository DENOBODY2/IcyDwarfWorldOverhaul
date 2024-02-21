package net.denobody2.icydwarfworldmod.worldgen.surface;

import com.github.alexthe666.citadel.server.generation.SurfaceRulesManager;
import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.denobody2.icydwarfworldmod.registry.ModSurfaceRuleRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules extends SurfaceRules {

    public static void setup() {
        //SurfaceRulesManager.registerOverworldSurfaceRule(SurfaceRules.isBiome(ModBiomes.TEST_BIOME), createTestSurfaceRules());
    }
    public static SurfaceRules.RuleSource createTestSurfaceRules() {
        SurfaceRules.RuleSource galena = SurfaceRules.state(ModBlocks.MANDARIN_PLANKS.get().defaultBlockState());
        SurfaceRules.RuleSource scarlet = SurfaceRules.state(ModBlocks.SHADOW_GEM_BLOCK.get().defaultBlockState());
        SurfaceRules.RuleSource azure = SurfaceRules.state(ModBlocks.VERDANT_STONE.get().defaultBlockState());
        SurfaceRules.RuleSource neutral = SurfaceRules.state(ModBlocks.DEIRUM_BLOCK.get().defaultBlockState());
        SurfaceRules.ConditionSource azureCondition = ModSurfaceRuleRegistry.simplexCondition(-0.025F, 0.025F, 90, 1F, 0);
        SurfaceRules.ConditionSource scarletCondition = ModSurfaceRuleRegistry.simplexCondition(-0.025F, 0.025F, 90, 1F, 1);
        return SurfaceRules.sequence(bedrock(), SurfaceRules.ifTrue(azureCondition, SurfaceRules.ifTrue(scarletCondition, neutral)), SurfaceRules.ifTrue(scarletCondition, scarlet), SurfaceRules.ifTrue(azureCondition, azure), galena);
    }
    private static SurfaceRules.RuleSource bedrock() {
        SurfaceRules.RuleSource bedrock = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());
        SurfaceRules.ConditionSource bedrockCondition = SurfaceRules.verticalGradient("bedrock", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5));
        return SurfaceRules.ifTrue(bedrockCondition, bedrock);
    }
    private static SurfaceRules.RuleSource createBands(int layers, int layerThickness, int layerDistance) {
        SurfaceRules.RuleSource sandstone = SurfaceRules.state(Blocks.SANDSTONE.defaultBlockState());
        SurfaceRules.RuleSource[] ruleSources = new SurfaceRules.RuleSource[layers];
        for (int i = 1; i <= layers; i++) {
            int yDown = i * layerDistance;
            int extra = i % 3 == 0 ? 1 : 0;
            SurfaceRules.ConditionSource layer1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62 - yDown), -1);
            SurfaceRules.ConditionSource layer2 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62 + extra + layerThickness - yDown), 0);
            ruleSources[i - 1] = SurfaceRules.ifTrue(layer1, SurfaceRules.ifTrue(SurfaceRules.not(layer2), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.7D, 0.8D), sandstone)));
        }
        return SurfaceRules.sequence(ruleSources);
    }
}
