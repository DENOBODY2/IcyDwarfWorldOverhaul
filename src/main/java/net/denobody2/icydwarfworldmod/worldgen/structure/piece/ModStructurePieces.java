package net.denobody2.icydwarfworldmod.worldgen.structure.piece;

import net.denobody2.icydwarfworldmod.IcyDwarfWorldMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructurePieces {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER = DeferredRegister.create(Registries.STRUCTURE_PIECE, IcyDwarfWorldMod.MOD_ID);
    //public static final RegistryObject<StructurePieceType> RIFT_BLOB = STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER.register("rift_blob", () -> RiftBlobStructurePiece::new);
    public static final RegistryObject<StructurePieceType> RIFT_CANYON = STRUCTURE_PIECE_TYPE_DEFERRED_REGISTER.register("rift_canyon", () -> RiftCanyonStructurePiece::new);

}
