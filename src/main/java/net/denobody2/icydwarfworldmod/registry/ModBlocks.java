package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IcyDwarfWorldMod.MOD_ID);

    public static final RegistryObject<Block> WOOD_ONE = registerBlock("wood_one", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> WOOD_ONE_STRIP = registerBlock("wood_one_strip", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> LEAF_ONE = registerBlock("leaf_one", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> LEAF_TWO = registerBlock("leaf_two", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MUSHROOM_ONE = registerBlock("mushroom_one", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MUSHROOM_TWO = registerBlock("mushroom_two", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> MUSHROOM = registerBlock("mushroom", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
            .sound(SoundType.STONE)));

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
