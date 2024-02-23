package net.denobody2.icydwarfworldmod.worldgen.structure;

import com.mojang.serialization.Codec;
import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.worldgen.structure.piece.RiftCanyonStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class RiftCanyonStructure extends AbstractCaveGenerationStructure{
    private static final int BOWL_WIDTH_RADIUS = 90;
    private static final int BOWL_HEIGHT_RADIUS = 50;
    public static final int BOWL_Y_CENTER = -15;

    public static final Codec<RiftCanyonStructure> CODEC = simpleCodec((settings) -> new RiftCanyonStructure(settings));

    public RiftCanyonStructure(Structure.StructureSettings settings) {
        super(settings, ModBiomes.RIFTLING_GROTTO);
    }

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return new RiftCanyonStructurePiece(offset, center, heightBlocks, widthBlocks);
    }

    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return BOWL_Y_CENTER;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return 100;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return 90;
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.RIFT_CANYON.get();
    }
}
