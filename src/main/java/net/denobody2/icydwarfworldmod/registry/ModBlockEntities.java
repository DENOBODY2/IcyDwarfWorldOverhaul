package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.blocks.entity.CrateBlockEntity;
import net.denobody2.icydwarfworldmod.common.blocks.entity.ModHangingSignBlockEntity;
import net.denobody2.icydwarfworldmod.common.blocks.entity.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IcyDwarfWorldMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChiseledBookShelfBlockEntity>> DEEPSLATE_CHISELED_BOOKSHELF_BE =
            BLOCK_ENTITIES.register("deepslate_chiseled_bookshelf_block_entity", () ->
                    BlockEntityType.Builder.of(ChiseledBookShelfBlockEntity::new,
                            ModBlocks.DEEPSLATE_CHISELED_BOOKSHELF.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrateBlockEntity>> CRATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crate_block_entity", () ->
                    BlockEntityType.Builder.of(CrateBlockEntity::new,
                            ModBlocks.CRATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.MANDARIN_SIGN.get(), ModBlocks.MANDARIN_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.MANDARIN_HANGING_SIGN.get(), ModBlocks.MANDARIN_WALL_HANGING_SIGN.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
