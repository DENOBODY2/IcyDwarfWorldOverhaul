package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.blocks.*;
import net.denobody2.icydwarfworldmod.common.item.DeirumBlockItem;
import net.denobody2.icydwarfworldmod.util.ModBlockSetTypes;
import net.denobody2.icydwarfworldmod.util.ModWoodTypes;
import net.denobody2.icydwarfworldmod.worldgen.feature.tree.MandarinTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IcyDwarfWorldMod.MOD_ID);



    public static final RegistryObject<Block> MANDARIN_LOG = registerBlock("mandarin_log", () -> new ModFlammableLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
            .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MANDARIN_SAPLING = registerBlock("mandarin_sapling", () -> new SaplingBlock(new MandarinTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
            ));
    public static final RegistryObject<Block> MANDARIN_WOOD = registerBlock("mandarin_wood", () -> new ModFlammableLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_MANDARIN_LOG = registerBlock("stripped_mandarin_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_MANDARIN_WOOD = registerBlock("stripped_mandarin_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MANDARIN_PLANKS = registerBlock("mandarin_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_TRAPDOOR = registerBlock("mandarin_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
            .sound(SoundType.WOOD), ModBlockSetTypes.MANDARIN){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_DOOR = BLOCKS.register("mandarin_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
            .sound(SoundType.WOOD), ModBlockSetTypes.MANDARIN){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_STAIRS = registerBlock("mandarin_stairs", () -> new StairBlock(ModBlocks.MANDARIN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_SLAB = registerBlock("mandarin_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_FENCE = registerBlock("mandarin_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
            .sound(SoundType.WOOD)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_FENCE_GATE = registerBlock("mandarin_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
            .sound(SoundType.WOOD), ModWoodTypes.MANDARIN){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_PRESSURE_PLATE = registerBlock("mandarin_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
            .sound(SoundType.WOOD), ModBlockSetTypes.MANDARIN){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });
    public static final RegistryObject<Block> MANDARIN_SIGN = BLOCKS.register("mandarin_sign", () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN)
            .sound(SoundType.WOOD), ModWoodTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_WALL_SIGN = BLOCKS.register("mandarin_wall_sign", () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)
            .sound(SoundType.WOOD), ModWoodTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_HANGING_SIGN = BLOCKS.register("mandarin_hanging_sign", () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN)
            .sound(SoundType.WOOD), ModWoodTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_WALL_HANGING_SIGN = BLOCKS.register("mandarin_wall_hanging_sign", () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN)
            .sound(SoundType.WOOD), ModWoodTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_BUTTON = registerBlock("mandarin_button", () -> woodenButton(ModBlockSetTypes.MANDARIN));


    public static final RegistryObject<Block> MANDARIN_LEAVES = registerBlock("mandarin_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .sound(SoundType.GRASS)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    });
    public static final RegistryObject<Block> FLOWERED_MANDARIN_LEAVES = registerBlock("flowered_mandarin_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .sound(SoundType.GRASS)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    });

    public static final RegistryObject<Block> VERDANT_STONE = registerBlock("verdant_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE = registerBlock("verdant_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE_STAIRS = registerBlock("verdant_cobblestone_stairs", () -> new StairBlock(ModBlocks.VERDANT_COBBLESTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE_SLAB = registerBlock("verdant_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_SLAB)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE_WALL = registerBlock("verdant_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_STONE_BRICKS = registerBlock("verdant_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_STONE_BRICK_STAIRS = registerBlock("verdant_stone_brick_stairs", () -> new StairBlock(ModBlocks.VERDANT_STONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_STONE_BRICK_SLAB = registerBlock("verdant_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> VERDANT_STONE_BRICK_WALL = registerBlock("verdant_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));
    public static final RegistryObject<Block> POLISHED_VERDANT_STONE = registerBlock("polished_verdant_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)));

    public static final RegistryObject<Block> CREATIVE_TAB_ICON = registerBlock("creative_tab_icon", () -> new ModDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().noOcclusion()));

    //riftling biome!
    public static final RegistryObject<Block> SHADOW_SHALE = registerBlock("shadow_shale", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_PURPLE)));

    public static final RegistryObject<Block> MOSSY_SHADOW_SHALE = registerBlock("mossy_shadow_shale", () -> new MossyShaleBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_PURPLE)));

    public static final RegistryObject<Block> ETHEREAL_MOSS = registerBlock("ethereal_moss", () -> new RiftMossBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.MOSS)
            .mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> ETHEREAL_GRASS = registerBlock("ethereal_grass", () -> new EtherealPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
            .sound(SoundType.GRASS)
            .mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> ETHEREAL_TALL_GRASS = registerBlock("ethereal_tall_grass", () -> new BoneMealableDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
            .sound(SoundType.GRASS)
            .mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> ETHEREAL_LONG_GRASS = registerBlock("ethereal_long_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)
            .sound(SoundType.GRASS)
            .mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> GLOWER_BULB = registerBlock("glower_bulb", ()-> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.GRASS)
            .mapColor(MapColor.COLOR_CYAN)
            .replaceable().instabreak().offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY).noCollission()
            .lightLevel(state -> state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? 8 : 0)));

    public static final RegistryObject<Block> SHADOW_LAMP = registerBlock("shadow_lamp", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SEA_LANTERN)
            .sound(SoundType.STONE)
            .mapColor(MapColor.COLOR_PURPLE).lightLevel((p_152688_) -> {
                return 15;
            })));

    public static final RegistryObject<Block> SHADOW_GEM_ORE = registerFireResBlock("shadow_gem_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
            .sound(SoundType.ANCIENT_DEBRIS)
            .strength(15.0F, 900.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE), UniformInt.of(3, 4)));

    public static final RegistryObject<Block> AMALGASTONE = registerBlock("amalgastone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_PURPLE)));


    //deirum

    public static final RegistryObject<Block> DEIRUM_ORE = registerFireResBlock("deirum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
            .sound(SoundType.ANCIENT_DEBRIS)
            .strength(25.0F, 900.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE), UniformInt.of(3, 5)));
    public static final RegistryObject<Block> DEEPSLATE_DEIRUM_ORE = registerFireResBlock("deepslate_deirum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
            .sound(SoundType.ANCIENT_DEBRIS)
            .strength(30.0F, 1200.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE),UniformInt.of(3, 6)));
    public static final RegistryObject<Block> DEIRUM_BLOCK = registerDeirumBlock("deirum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
            .requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHADOW_GEM_BLOCK = registerShadowGemBlock("shadow_gem_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
            .requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    //ash
    public static final RegistryObject<Block> ASHEN_DUST = registerBlock("ashen_dust", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)
            .sound(SoundType.SAND)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> ASHEN_DUST_PILE = registerBlock("ashen_dust_pile", () ->new AshPileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SAND).isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
        return p_187417_.getValue(AshPileBlock.LAYERS) >= 8;
    }).pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));

    public static final RegistryObject<Block> DEEPSLATE_CHISELED_BOOKSHELF = registerBlock("deepslate_chiseled_bookshelf", () -> new DeepSlateChiseledBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_BOOKSHELF)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .sound(SoundType.CHISELED_BOOKSHELF).mapColor(MapColor.COLOR_GRAY)));

    public static final RegistryObject<Block> ASH_BRICKS = registerBlock("ash_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_BRICK_STAIRS = registerBlock("ash_brick_stairs", () -> new StairBlock(ModBlocks.ASH_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_BRICK_SLAB = registerBlock("ash_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_BRICK_WALL = registerBlock("ash_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_TILES = registerBlock("ash_tiles", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_TILE_STAIRS = registerBlock("ash_tile_stairs", () -> new StairBlock(ModBlocks.ASH_TILES.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_TILE_SLAB = registerBlock("ash_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> ASH_TILE_WALL = registerBlock("ash_tile_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> CRATE = registerBlock("crate",
            () -> new CrateBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5f)));

    private static ButtonBlock woodenButton(BlockSetType pSetType, FeatureFlag... pRequiredFeatures) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (pRequiredFeatures.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(pRequiredFeatures);
        }

        return new ButtonBlock(blockbehaviour$properties, pSetType, 30, true);
    }
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }
    private static <T extends Block>RegistryObject<T> registerDeirumBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerDeirumBlockItem(name, toReturn);

        return toReturn;
    }
    private static <T extends Block>RegistryObject<T> registerShadowGemBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerShadowGemBlockItem(name, toReturn);

        return toReturn;
    }
    /*private static <T extends Block>RegistryObject<T> registerFuelBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        //registerFuelBlockItem(name, toReturn);
        return toReturn;
    }*/

    private static <T extends Block>RegistryObject<T> registerFireResBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItemWithFireRes(name, toReturn);
        return toReturn;
    }



    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    /*private static <T extends Block> RegistryObject<Item> registerFuelBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new ModFuelBlockItem(block.get(), new Item.Properties(), 28800));
    }*/
    private static <T extends Block>RegistryObject<Item> registerBlockItemWithFireRes(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant()));
    }
    private static <T extends Block>RegistryObject<Item> registerShadowGemBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant().rarity(ModItems.RARITY_SHADOW)));
    }
    private static <T extends Block>RegistryObject<Item> registerDeirumBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new DeirumBlockItem(block.get(), new Item.Properties().fireResistant()));
    }

}
