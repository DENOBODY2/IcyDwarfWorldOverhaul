package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.blocks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IcyDwarfWorldMod.MOD_ID);



    public static final RegistryObject<Block> MANDARIN_LOG = registerBlock("mandarin_log", () -> new ModFlammableLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
            .sound(SoundType.WOOD)));
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
    public static final RegistryObject<Block> MANDARIN_DOOR = registerBlock("mandarin_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
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


    public static final RegistryObject<Block> MANDARIN_LEAVES = registerBlock("mandarin_leaves", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
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
    public static final RegistryObject<Block> FLOWERED_MANDARIN_LEAVES = registerBlock("flowered_mandarin_leaves", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
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
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE = registerBlock("verdant_cobblestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE_STAIRS = registerBlock("verdant_cobblestone_stairs", () -> new StairBlock(ModBlocks.VERDANT_COBBLESTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE_SLAB = registerBlock("verdant_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_SLAB)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_COBBLESTONE_WALL = registerBlock("verdant_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_STONE_BRICKS = registerBlock("verdant_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_STONE_BRICK_STAIRS = registerBlock("verdant_stone_brick_stairs", () -> new StairBlock(ModBlocks.VERDANT_STONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_STAIRS)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_STONE_BRICK_SLAB = registerBlock("verdant_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_STONE_BRICK_WALL = registerBlock("verdant_stone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_VERDANT_STONE = registerBlock("polished_verdant_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));



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
    public static final RegistryObject<Block> DEIRUM_BLOCK = registerFireResBlock("deirum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
            .requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> ASHEN_DUST = registerBlock("ashen_dust", () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)
            .sound(SoundType.SAND)));
    public static final RegistryObject<Block> ASHEN_DUST_PILE = registerBlock("ashen_dust_pile", () ->new AshPileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SAND).isViewBlocking((p_187417_, p_187418_, p_187419_) -> {
        return p_187417_.getValue(AshPileBlock.LAYERS) >= 8;
    }).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> DEEPSLATE_CHISELED_BOOKSHELF = registerBlock("deepslate_chiseled_bookshelf", () -> new DeepSlateChiseledBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_BOOKSHELF)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .sound(SoundType.CHISELED_BOOKSHELF)));
    public static final RegistryObject<Block> CRATE = registerBlock("crate",
            () -> new CrateBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));

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

}
