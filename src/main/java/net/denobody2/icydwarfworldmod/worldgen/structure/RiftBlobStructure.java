package net.denobody2.icydwarfworldmod.worldgen.structure;

import com.mojang.serialization.Codec;
import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.worldgen.structure.piece.RiftBlobStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class RiftBlobStructure extends AbstractCaveGenerationStructure{
    private static final int BOWL_WIDTH_RADIUS = 50;
    private static final int BOWL_HEIGHT_RADIUS = 35;

    public static final int BOWL_Y_CENTER = -1;

    public static final Codec<RiftBlobStructure> CODEC = simpleCodec((settings) -> new RiftBlobStructure(settings));

    public RiftBlobStructure(StructureSettings settings) {
        super(settings, ModBiomes.RIFTLING_GROTTO);
    }

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return new RiftBlobStructurePiece(offset, center, heightBlocks, widthBlocks);
    }

    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return BOWL_Y_CENTER;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return BOWL_WIDTH_RADIUS;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return BOWL_HEIGHT_RADIUS;
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.RIFT_BLOB.get();
    }
}
