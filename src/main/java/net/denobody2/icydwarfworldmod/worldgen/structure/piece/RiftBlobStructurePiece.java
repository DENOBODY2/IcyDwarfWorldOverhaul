package net.denobody2.icydwarfworldmod.worldgen.structure.piece;

import net.denobody2.icydwarfworldmod.registry.ModBiomes;
import net.denobody2.icydwarfworldmod.util.ModMath;
import net.denobody2.icydwarfworldmod.util.VoronoiGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class RiftBlobStructurePiece extends AbstractCaveGenerationStructurePiece{
    private VoronoiGenerator voronoiGenerator;

    public RiftBlobStructurePiece(BlockPos chunkCorner, BlockPos holeCenter, int bowlHeight, int bowlRadius) {
        super(ModStructurePieces.RIFT_BLOB.get(), chunkCorner, holeCenter, bowlHeight, bowlRadius);
    }

    public RiftBlobStructurePiece(CompoundTag tag) {
        super(ModStructurePieces.RIFT_BLOB.get(), tag);
    }

    public RiftBlobStructurePiece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag tag) {
        this(tag);
    }

    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        if (voronoiGenerator == null) {
            voronoiGenerator = new VoronoiGenerator(level.getSeed());
            voronoiGenerator.setOffsetAmount(0.6F);
        }
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();
        boolean flag = false;
        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveAbove = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();
        carve.set(cornerX, cornerY, cornerZ);
        carveAbove.set(cornerX, cornerY, cornerZ);
        carveBelow.set(cornerX, cornerY, cornerZ);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 15; y >= 0; y--) {
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);
                    if (inCircle(carve) && !checkedGetBlock(level, carve).is(Blocks.BEDROCK)) {
                        flag = true;
                        checkedSetBlock(level, carve, Blocks.CAVE_AIR.defaultBlockState());
                        surroundCornerOfLiquid(level, carve);
                        carveBelow.set(carve.getX(), carve.getY() - 1, carve.getZ());
                        doFloor.setTrue();
                    }
                }
                if (doFloor.isTrue()) {
                    BlockState floor = checkedGetBlock(level, carveBelow);
                    if (!floor.isAir()) {
                        decorateFloor(level, random, carveBelow.immutable());
                    }
                    doFloor.setFalse();
                }
            }
        }
        if (flag) {
            replaceBiomes(level, ModBiomes.RIFTLING_GROTTO, 32);
        }
    }

    private void surroundCornerOfLiquid(WorldGenLevel level, Vec3i center) {
        BlockPos.MutableBlockPos offset = new BlockPos.MutableBlockPos();
        for (Direction dir : Direction.values()) {
            offset.set(center);
            offset.move(dir);
            BlockState state = checkedGetBlock(level, offset);
            if (!state.getFluidState().isEmpty()) {
                checkedSetBlock(level, offset, Blocks.SANDSTONE.defaultBlockState());
            }
        }
    }

    private boolean inCircle(BlockPos carve) {
        float wallNoise = (ModMath.sampleNoise3D(carve.getX(), (int) (carve.getY() * 0.1F), carve.getZ(), 40) + 1.0F) * 0.5F;
        double yDist = ModMath.smin(1F - Math.abs(this.holeCenter.getY() - carve.getY()) / (float) (height * 0.5F), 1.0F, 0.3F);
        double distToCenter = carve.distToLowCornerSqr(this.holeCenter.getX(), carve.getY(), this.holeCenter.getZ());
        double targetRadius = yDist * (radius * wallNoise) * radius;
        return distToCenter < targetRadius;
    }

    private void decorateFloor(WorldGenLevel level, RandomSource rand, BlockPos carveBelow) {
        BlockState grass = Blocks.GRASS_BLOCK.defaultBlockState();
        BlockState dirt = Blocks.DIRT.defaultBlockState();
        checkedSetBlock(level, carveBelow, grass);
        for (int i = 0; i < 1 + rand.nextInt(2); i++) {
            carveBelow = carveBelow.below();
            checkedSetBlock(level, carveBelow, dirt);
        }
    }
}
