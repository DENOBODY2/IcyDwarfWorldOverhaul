package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.entity.ModBoatEntity;
import net.denobody2.icydwarfworldmod.common.item.*;
import net.denobody2.icydwarfworldmod.util.ModFoodProperties;
import net.denobody2.icydwarfworldmod.util.ModToolTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IcyDwarfWorldMod.MOD_ID);

    public static final Rarity RARITY_SHADOW = Rarity.create("icydwarfworldmod:shadow", ChatFormatting.DARK_PURPLE);
    public static final RegistryObject<Item> MANDARIN_SIGN = ITEMS.register("mandarin_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MANDARIN_SIGN.get(), ModBlocks.MANDARIN_WALL_SIGN.get()));
    public static final RegistryObject<Item> MANDARIN_HANGING_SIGN = ITEMS.register("mandarin_hanging_sign",
            () -> new HangingSignItem(ModBlocks.MANDARIN_HANGING_SIGN.get(), ModBlocks.MANDARIN_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MANDARIN_BOAT = ITEMS.register("mandarin_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MANDARIN, new Item.Properties()));

    public static final RegistryObject<Item> MANDARIN_DOOR = ITEMS.register("mandarin_door",
            () -> new DoubleHighBlockItem(ModBlocks.MANDARIN_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANDARIN_CHEST_BOAT = ITEMS.register("mandarin_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MANDARIN, new Item.Properties()));

    public static final RegistryObject<Item> MANDARIN_FLOWER = ITEMS.register("mandarin_flower",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEIRUM = ITEMS.register("deirum",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> SHADOW_GEM = ITEMS.register("shadow_gem",
            () -> new Item(new Item.Properties().fireResistant().rarity(RARITY_SHADOW)));
    public static final RegistryObject<Item> DEIRUM_SWORD = ITEMS.register("deirum_sword",
            () -> new DeirumSwordItem(ModToolTiers.DEIRUM, 3, -2.2f, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DEIRUM_AXE = ITEMS.register("deirum_axe",
            () -> new AxeItem(ModToolTiers.DEIRUM, 4f, -2.7f, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SHADOW_SWORD = ITEMS.register("shadow_sword",
            () -> new ShadowWeaponItem(ModToolTiers.SHADOW, 3, -2.2f, new Item.Properties().fireResistant().rarity(RARITY_SHADOW)));
    public static final RegistryObject<Item> SHADOW_GREAT_HAMMER = ITEMS.register("shadow_great_hammer",
            () -> new ShadowHammerWeaponItem(ModToolTiers.SHADOW, 4, -2.7f, new Item.Properties().fireResistant().rarity(RARITY_SHADOW)));

    public static final RegistryObject<Item> RIFTLING_SPAWN_EGG = ITEMS.register("riftling_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.RIFTLING, 0x2d174a, 0x770d9e, new Item.Properties()));

    public static final RegistryObject<Item> GOOBLINO_SPAWN_EGG = ITEMS.register("gooblino_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.GOOBLINO, 0x614d4d, 0x685757, new Item.Properties()));
    public static final RegistryObject<Item> SHADOW_BALL = ITEMS.register("shadow_ball",
            () -> new ShadowBallItem(new Item.Properties().fireResistant().stacksTo(16).rarity(RARITY_SHADOW)));
    public static final RegistryObject<Item> MYSTERY_MEAT = ITEMS.register("mystery_meat",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.MYSTERY_MEAT)));
    public static void register(IEventBus eventBus){

        ITEMS.register(eventBus);

    }
}
