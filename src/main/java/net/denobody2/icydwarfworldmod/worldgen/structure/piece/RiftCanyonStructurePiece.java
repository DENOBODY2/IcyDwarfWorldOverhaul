package net.denobody2.icydwarfworldmod.worldgen.structure.piece;

import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.denobody2.icydwarfworldmod.util.ModMath;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class RiftCanyonStructurePiece extends AbstractCaveGenerationStructurePiece{
    public RiftCanyonStructurePiece(BlockPos chunkCorner, BlockPos holeCenter, int bowlHeight, int bowlRadius) {
        super(ModStructurePieces.RIFT_CANYON.get(), chunkCorner, holeCenter, bowlHeight, bowlRadius);
    }

    public RiftCanyonStructurePiece(CompoundTag tag) {
        super(ModStructurePieces.RIFT_CANYON.get(), tag);
    }

    public RiftCanyonStructurePiece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag tag) {
        this(tag);
    }

    public void postProcess(WorldGenLevel level, StructureManager featureManager, ChunkGenerator chunkGen, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
        int cornerX = this.chunkCorner.getX();
        int cornerY = this.chunkCorner.getY();
        int cornerZ = this.chunkCorner.getZ();
        BlockPos.MutableBlockPos carve = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos carveBelow = new BlockPos.MutableBlockPos();
        carve.set(cornerX, cornerY, cornerZ);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                MutableBoolean doFloor = new MutableBoolean(false);
                for (int y = 15; y >= 0; y--) {
                    carve.set(cornerX + x, Mth.clamp(cornerY + y, level.getMinBuildHeight(), level.getMaxBuildHeight()), cornerZ + z);
                    if (inCircle(carve) && !checkedGetBlock(level, carve).is(Blocks.BEDROCK)) {
                        checkedSetBlock(level, carve, Blocks.CAVE_AIR.defaultBlockState());
                        surroundCornerOfLiquid(level, carve);
                        carveBelow.set(carve.getX(), carve.getY() - 1, carve.getZ());
                        doFloor.setTrue();
                    } else if (doFloor.isTrue()) {
                        break;
                    }
                }
                if (doFloor.isTrue() && !checkedGetBlock(level, carveBelow).isAir()) {
                    decorateFloor(level, random, carveBelow);
                    doFloor.setFalse();
                }
            }
        }
    }

    private void surroundCornerOfLiquid(WorldGenLevel level, BlockPos.MutableBlockPos center) {
        BlockPos.MutableBlockPos offset = new BlockPos.MutableBlockPos();
        for (Direction dir : Direction.values()) {
            offset.set(center);
            offset.move(dir);
            BlockState state = checkedGetBlock(level, offset);
            if (!state.getFluidState().isEmpty()) {
                checkedSetBlock(level, offset, ModBlocks.SHADOW_SHALE.get().defaultBlockState());
            }
        }
    }

    private boolean inCircle(BlockPos.MutableBlockPos carve) {
        float df1 = (ModMath.sampleNoise3D(carve.getX(), carve.getY(), carve.getZ(), 30) + 0.5F) * 0.6F;
        float df2 = ModMath.sampleNoise3D(carve.getX() - 1300, carve.getY() + 70, carve.getZ() + 80, 20) * 0.17F;
        double df1Smooth = ModMath.smin(df1 + df2, 1.0F, 0.1F);
        float pillarNoise = (ModMath.sampleNoise3D(carve.getX(), (int) (carve.getY() * 0.4F), carve.getZ(), 30) + 1.0F) * 0.5F;
        float verticalNoise = (ModMath.sampleNoise2D(carve.getX(), carve.getZ(), 30) + 0.7F) * 0.2F - (ModMath.smin(ModMath.sampleNoise2D(carve.getX(), carve.getZ(), 20), -0.5F, 0.1F) + 0.5F) * 0.7F;
        double distToCenter = carve.distToLowCornerSqr(this.holeCenter.getX(), carve.getY(), this.holeCenter.getZ());
        float f = getHeightOf(carve);
        float f1 = (float) Math.pow(firstStep(f, 11), 2.5F);
        float f2 = (float) Math.pow(secondStep(f, 8), 3F);
        float rawHeight = Math.abs(this.holeCenter.getY() - carve.getY()) / (float) (height * 0.6F);
        float reverseRawHeight = 1F - rawHeight;
        double yDist = ModMath.smin((float) Math.pow(reverseRawHeight, 0.3F), 1.0F, 0.1F);
        double targetRadius = (yDist * (radius * (df1Smooth/pillarNoise) * ((f1-f2)) * radius));
        return distToCenter < targetRadius && rawHeight < 1 - verticalNoise;
    }

    private float getHeightOf(BlockPos.MutableBlockPos carve) {
        int halfHeight = this.height / 2;
        if (carve.getY() > this.holeCenter.getY() + halfHeight + 1 || carve.getY() < this.holeCenter.getY() - halfHeight) {
            return 0.0F;
        } else {
            return 1F - ((this.holeCenter.getY() + halfHeight - carve.getY()) / (float) (height * 2));
        }
    }

    private float firstStep(float heightScale, int scaleTo) {
        int clampTo100 = (int) ((heightScale) * scaleTo * scaleTo);
        return Mth.clamp((float) (Math.round(clampTo100 / (float) scaleTo)) / (float) scaleTo, 0F, 1F);
    }
    private float secondStep(float heightScale, int scaleTo) {
        int clampTo50 = (int) ((heightScale) * scaleTo * scaleTo * 0.6) / 2;
        return Mth.clamp((float) (Math.round(clampTo50 / (float) scaleTo)) / (float) scaleTo / 1.5F, 0F, 1F);
    }

    private void decorateFloor(WorldGenLevel level, RandomSource rand, BlockPos.MutableBlockPos carveBelow) {
        float floorNoise = (ModMath.sampleNoise2D(carveBelow.getX(), carveBelow.getZ(), 50) + 1.0F) * 0.5F;
        BlockState grass = ModBlocks.MOSSY_SHADOW_SHALE.get().defaultBlockState();
        BlockState dirt = ModBlocks.SHADOW_SHALE.get().defaultBlockState();
        checkedSetBlock(level, carveBelow, grass);
        for (int i = 0; i < Math.ceil(floorNoise * 1); i++) {
            carveBelow.move(0, -1, 0);
            checkedSetBlock(level, carveBelow, dirt);
        }
    }
}
