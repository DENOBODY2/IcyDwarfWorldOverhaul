package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IcyDwarfWorldMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("icydwarfworldmod", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.CREATIVE_TAB_ICON.get()))
            .title(Component.translatable("itemGroup.icydwarfworldmod"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModBlocks.CRATE.get());
                pOutput.accept(ModBlocks.MANDARIN_SAPLING.get());
                pOutput.accept(ModBlocks.MANDARIN_LOG.get());
                pOutput.accept(ModBlocks.MANDARIN_LEAVES.get());
                pOutput.accept(ModBlocks.FLOWERED_MANDARIN_LEAVES.get());
                pOutput.accept(ModItems.MANDARIN_FLOWER.get());
                pOutput.accept(ModBlocks.MANDARIN_WOOD.get());
                pOutput.accept(ModBlocks.STRIPPED_MANDARIN_LOG.get());
                pOutput.accept(ModBlocks.STRIPPED_MANDARIN_WOOD.get());
                pOutput.accept(ModBlocks.MANDARIN_PLANKS.get());
                pOutput.accept(ModBlocks.MANDARIN_STAIRS.get());
                pOutput.accept(ModBlocks.MANDARIN_SLAB.get());
                pOutput.accept(ModBlocks.MANDARIN_FENCE.get());
                pOutput.accept(ModBlocks.MANDARIN_FENCE_GATE.get());
                pOutput.accept(ModBlocks.MANDARIN_DOOR.get());
                pOutput.accept(ModBlocks.MANDARIN_TRAPDOOR.get());
                pOutput.accept(ModBlocks.MANDARIN_PRESSURE_PLATE.get());
                pOutput.accept(ModBlocks.MANDARIN_BUTTON.get());
                pOutput.accept(ModItems.MANDARIN_SIGN.get());
                pOutput.accept(ModItems.MANDARIN_HANGING_SIGN.get());
                pOutput.accept(ModItems.MANDARIN_BOAT.get());
                pOutput.accept(ModItems.MANDARIN_CHEST_BOAT.get());
                pOutput.accept(ModItems.GOOBLINO_SPAWN_EGG.get());
                pOutput.accept(ModItems.MYSTERY_MEAT.get());
                pOutput.accept(ModBlocks.ASHEN_DUST.get());
                pOutput.accept(ModBlocks.ASHEN_DUST_PILE.get());
                pOutput.accept(ModBlocks.DEEPSLATE_CHISELED_BOOKSHELF.get());
                pOutput.accept(ModBlocks.VERDANT_STONE.get());
                pOutput.accept(ModBlocks.POLISHED_VERDANT_STONE.get());
                pOutput.accept(ModBlocks.VERDANT_COBBLESTONE.get());
                pOutput.accept(ModBlocks.VERDANT_COBBLESTONE_STAIRS.get());
                pOutput.accept(ModBlocks.VERDANT_COBBLESTONE_SLAB.get());
                pOutput.accept(ModBlocks.VERDANT_COBBLESTONE_WALL.get());
                pOutput.accept(ModBlocks.VERDANT_STONE_BRICKS.get());
                pOutput.accept(ModBlocks.VERDANT_STONE_BRICK_STAIRS.get());
                pOutput.accept(ModBlocks.VERDANT_STONE_BRICK_SLAB.get());
                pOutput.accept(ModBlocks.VERDANT_STONE_BRICK_WALL.get());
                pOutput.accept(ModBlocks.DEIRUM_ORE.get());
                pOutput.accept(ModBlocks.DEEPSLATE_DEIRUM_ORE.get());
                pOutput.accept(ModBlocks.DEIRUM_BLOCK.get());
                pOutput.accept(ModItems.DEIRUM.get());
                pOutput.accept(ModItems.DEIRUM_SWORD.get());
                pOutput.accept(ModItems.DEIRUM_AXE.get());
                pOutput.accept(ModBlocks.SHADOW_SHALE.get());
                pOutput.accept(ModBlocks.AMALGASTONE.get());
                pOutput.accept(ModItems.RIFTLING_SPAWN_EGG.get());
                pOutput.accept(ModBlocks.SHADOW_GEM_BLOCK.get());
                pOutput.accept(ModItems.SHADOW_GEM.get());
                pOutput.accept(ModItems.SHADOW_BALL.get());
                pOutput.accept(ModItems.SHADOW_SWORD.get());
                pOutput.accept(ModItems.SHADOW_GREAT_HAMMER.get());






                /*for (var item : ModItems.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }*/
                /*for (var block: ModBlocks.BLOCKS.getEntries()){
                    if(!(block.equals(ModBlocks.MANDARIN_WALL_HANGING_SIGN))){
                        if(!(block.equals(ModBlocks.MANDARIN_WALL_SIGN))){
                            pOutput.accept(block.get());
                        }
                    }
                }*/
            }).build());
}
