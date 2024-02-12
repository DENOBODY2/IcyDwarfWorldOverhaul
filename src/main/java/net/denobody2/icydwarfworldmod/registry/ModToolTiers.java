package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier DEIRUM = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1800, 8.5f, 4f, 22,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.DEIRUM.get())),
            new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "deruim"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
    public static final Tier SHADOW = TierSortingRegistry.registerTier(
            new ForgeTier(4, 2200, 8.5f, 4.5f, 25,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.SHADOW_GEM.get())),
            new ResourceLocation(IcyDwarfWorldMod.MOD_ID, "shadow"), List.of(Tiers.NETHERITE), List.of());
}
