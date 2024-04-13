package net.denobody2.icydwarfworldmod.util;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> RIFTLING_IGNITERS = tag("riftling_igniters");
        public static final TagKey<Item> RIFTLING_DIGESTS = tag("riftling_digests");
        public static final TagKey<Item> MANDARIN_LOGS = tag("mandarin_logs");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
    public static class Blocks {

        public static final TagKey<Block> GOOBLINO_SPAWNABLE_ON = tag("gooblino_spawnable_on");
        public static final TagKey<Block> RIFTLING_SPAWNABLE_ON = tag("riftling_spawnable_on");
        public static final TagKey<Block> VERDANT_STONE_REPLACES = tag("verdant_stone_replaces");

        public static final TagKey<Block> SHADOW_GEM_ORE_REPLACEABLES = tag("shadow_gem_ore_replaceables");
        public static final TagKey<Block> MANDARIN_LOGS = tag("mandarin_logs");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
    public static class Biomes {
        public static final TagKey<Biome> MANDARIN_SPAWNS = tag("mandarin_spawns");
        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(IcyDwarfWorldMod.MOD_ID, name));
        }
    }
    //access transformer
}
