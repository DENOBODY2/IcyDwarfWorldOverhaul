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
            .icon(() -> new ItemStack(ModItems.DEIRUM.get()))
            .title(Component.translatable("itemGroup.icydwarfworldmod"))
            .displayItems((pParameters, pOutput) -> {
                for (var item : ModItems.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }
                /*for (var block: ModBlocks.BLOCKS.getEntries()){
                    if(!(block.equals(ModBlocks.MANDARIN_WALL_HANGING_SIGN))){
                        if(!(block.equals(ModBlocks.MANDARIN_WALL_SIGN))){
                            pOutput.accept(block.get());
                        }
                    }
                }*/
            }).build());
}
