package net.denobody2.icydwarfworldmod.registry;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType MANDARIN = WoodType.register(new WoodType(IcyDwarfWorldMod.MOD_ID + ":mandarin", ModBlockSetTypes.MANDARIN));
}
