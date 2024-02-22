package net.denobody2.icydwarfworldmod.worldgen.structure;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, IcyDwarfWorldMod.MOD_ID);
    public static final RegistryObject<StructureType<RiftBlobStructure>> RIFT_BLOB = STRUCTURE_TYPES.register("rift_blob", () -> () -> RiftBlobStructure.CODEC);
}
