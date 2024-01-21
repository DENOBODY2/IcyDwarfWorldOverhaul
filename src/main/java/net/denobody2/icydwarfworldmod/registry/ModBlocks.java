package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IcyDwarfWorldMod.MOD_ID);



    public static final RegistryObject<Block> MANDARIN_LOG = registerBlock("mandarin_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MANDARIN_WOOD = registerBlock("mandarin_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> STRIPPED_MANDARIN_LOG = registerBlock("stripped_mandarin_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> STRIPPED_MANDARIN_WOOD = registerBlock("stripped_mandarin_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MANDARIN_PLANKS = registerBlock("mandarin_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MANDARIN_TRAPDOOR = registerBlock("mandarin_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
            .sound(SoundType.WOOD), ModBlockSetTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_DOOR = registerBlock("mandarin_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
            .sound(SoundType.WOOD), ModBlockSetTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_STAIRS = registerBlock("mandarin_stairs", () -> new StairBlock(ModBlocks.MANDARIN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MANDARIN_SLAB = registerBlock("mandarin_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MANDARIN_FENCE = registerBlock("mandarin_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MANDARIN_FENCE_GATE = registerBlock("mandarin_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
            .sound(SoundType.STONE), ModWoodTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_PRESSURE_PLATE = registerBlock("mandarin_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
            .sound(SoundType.STONE), ModBlockSetTypes.MANDARIN));
    public static final RegistryObject<Block> MANDARIN_BUTTON = registerBlock("mandarin_button", () -> woodenButton(ModBlockSetTypes.MANDARIN));


    public static final RegistryObject<Block> MANDARIN_LEAVES = registerBlock("mandarin_leaves", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> FLOWERED_MANDARIN_LEAVES = registerBlock("flowered_mandarin_leaves", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> GLOOPSHROOM_TOP = registerBlock("gloopshroom_top", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> GLOOPSHROOM_SIDE = registerBlock("gloopshroom_side", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> GLOOPSHROOM = registerBlock("gloopshroom", () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
            .sound(SoundType.STONE)));

    private static ButtonBlock woodenButton(BlockSetType pSetType, FeatureFlag... pRequiredFeatures) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (pRequiredFeatures.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(pRequiredFeatures);
        }

        return new ButtonBlock(blockbehaviour$properties, pSetType, 30, true);
    }
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if(name.equals("fire_res") || name.equals("no_fire")){
            registerBlockItemWithFireRes(name, toReturn);
        }
        /*else if(name.equals("black_donut_block")){
            registerFuelBlockItem(name, toReturn);
        }*/ else {
            registerBlockItem(name, toReturn);
        }
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
