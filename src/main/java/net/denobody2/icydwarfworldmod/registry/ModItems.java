package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.denobody2.icydwarfworldmod.common.entity.ModBoatEntity;
import net.denobody2.icydwarfworldmod.common.item.DeirumSwordItem;
import net.denobody2.icydwarfworldmod.common.item.ModBoatItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IcyDwarfWorldMod.MOD_ID);
    public static final RegistryObject<Item> MANDARIN_SIGN = ITEMS.register("mandarin_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MANDARIN_SIGN.get(), ModBlocks.MANDARIN_WALL_SIGN.get()));
    public static final RegistryObject<Item> MANDARIN_HANGING_SIGN = ITEMS.register("mandarin_hanging_sign",
            () -> new HangingSignItem(ModBlocks.MANDARIN_HANGING_SIGN.get(), ModBlocks.MANDARIN_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MANDARIN_BOAT = ITEMS.register("mandarin_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MANDARIN, new Item.Properties()));
    public static final RegistryObject<Item> MANDARIN_CHEST_BOAT = ITEMS.register("mandarin_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MANDARIN, new Item.Properties()));
    public static final RegistryObject<Item> DEIRUM = ITEMS.register("deirum",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DEIRUM_SWORD = ITEMS.register("deirum_sword",
            () -> new DeirumSwordItem(ModToolTiers.DEIRUM, 3, -2.2f, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DEIRUM_WARAXE = ITEMS.register("deirum_waraxe",
            () -> new AxeItem(ModToolTiers.DEIRUM, 4f, -2.7f, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus){

        ITEMS.register(eventBus);

    }
}
