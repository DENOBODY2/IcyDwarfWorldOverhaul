package net.denobody2.icydwarfworldmod.worldgen.surface;

import com.github.alexthe666.citadel.server.generation.SurfaceRulesManager;
import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.denobody2.icydwarfworldmod.registry.ModSurfaceRuleRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules extends SurfaceRules {

    public static void setup() {
        SurfaceRulesManager.registerOverworldSurfaceRule(SurfaceRules.isBiome(ModBiomes.RIFTLING_GROTTO), createTestSurfaceRules());
    }
    public static SurfaceRules.RuleSource createTestSurfaceRules() {
        SurfaceRules.RuleSource mainStone = SurfaceRules.state(Blocks.DEEPSLATE.defaultBlockState());
        SurfaceRules.RuleSource scarlet = SurfaceRules.state(ModBlocks.SHADOW_GEM_BLOCK.get().defaultBlockState());
        SurfaceRules.RuleSource azure = SurfaceRules.state(ModBlocks.VERDANT_STONE.get().defaultBlockState());
        SurfaceRules.ConditionSource scarletCondition = ModSurfaceRuleRegistry.simplexCondition(-0.070F, 0.070F, 150, 1F, 0);
        return SurfaceRules.sequence(bedrock(), SurfaceRules.ifTrue(scarletCondition, scarlet), mainStone);
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
