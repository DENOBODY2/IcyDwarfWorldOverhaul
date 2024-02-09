package net.denobody2.icydwarfworldmod.common.blocks;

import net.denobody2.icydwarfworldmod.common.entity.FallingAshEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AshPileBlock extends SnowLayerBlock implements Fallable {
    public AshPileBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        levelAccessor.scheduleTick(pos, this, this.getDelayAfterPlace());
        return super.updateShape(blockState, direction, blockState1, levelAccessor, pos, pos1);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if(!context.getItemInHand().isEmpty()  && context.getItemInHand().is(this.asItem())){
            return (state.getBlock() instanceof SnowLayerBlock && state.getValue(LAYERS) < 8);
        }
        return false;
    }

    @Override
    public void tick(BlockState state, ServerLevel blockState, BlockPos blockPos, RandomSource randomSource) {
        if (isFree(blockState.getBlockState(blockPos.below())) && blockPos.getY() >= blockState.getMinBuildHeight()) {
            FallingAshEntity.fall(blockState, blockPos, state);
        }
    }
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState blockState, boolean b) {
        level.scheduleTick(pos, this, this.getDelayAfterPlace());
    }


    public static boolean isFree(BlockState belowState) {
        if (belowState.getBlock() instanceof SnowLayerBlock && belowState.getValue(LAYERS) < 8) {
            return true;
        } else if (belowState.getBlock() instanceof SnowLayerBlock && belowState.getValue(LAYERS) == 8){
            return false;
        }
        return belowState.isAir() || belowState.is(BlockTags.FIRE) || belowState.liquid() || belowState.canBeReplaced();
    }

    public void onBrokenAfterFall(Level level, BlockPos fallenOn, FallingBlockEntity fallingBlockEntity) {
    }

    protected int getDelayAfterPlace() {
        return 3;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
    }

    public void entityInside(BlockState state, Level level, BlockPos blockPos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this)) {
            if (entity instanceof Bat) {
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.9D, 1.0D, 0.9D));
            }
        }
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos blockPos, CollisionContext context) {
        return context instanceof EntityCollisionContext entityCollisionContext && entityCollisionContext.getEntity() != null && (entityCollisionContext.getEntity() instanceof Bat || entityCollisionContext.getEntity() instanceof FallingBlockEntity) ? super.getShape(state, level, blockPos, context) : super.getCollisionShape(state, level, blockPos, context);
    }

}
