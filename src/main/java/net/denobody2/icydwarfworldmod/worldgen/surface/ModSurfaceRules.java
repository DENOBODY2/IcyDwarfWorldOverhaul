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
        SurfaceRulesManager.registerOverworldSurfaceRule(SurfaceRules.isBiome(ModBiomes.RIFTLING_GROTTO), createRiftlinGrottoSurfaceRules());
    }
    public static SurfaceRules.RuleSource createRiftlinGrottoSurfaceRules() {
        SurfaceRules.RuleSource moss = SurfaceRules.state(ModBlocks.MOSSY_SHADOW_SHALE.get().defaultBlockState());
        SurfaceRules.RuleSource mainStone = SurfaceRules.state(ModBlocks.SHADOW_SHALE.get().defaultBlockState());
        SurfaceRules.RuleSource secondarystone = SurfaceRules.state(ModBlocks.AMALGASTONE.get().defaultBlockState());
        SurfaceRules.ConditionSource stoneCondition = ModSurfaceRuleRegistry.simplexCondition(-0.10F, 0.15F, 15, 4F, 3);
        return SurfaceRules.sequence(bedrock(), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, moss), SurfaceRules.ifTrue(stoneCondition, secondarystone), mainStone);
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
