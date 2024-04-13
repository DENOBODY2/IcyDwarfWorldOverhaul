package net.denobody2.icydwarfworldmod.common.blocks;

import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class BoneMealableDoublePlantBlock extends DoublePlantBlock implements BonemealableBlock {
    public BoneMealableDoublePlantBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        //pLevel.destroyBlock(pPos, false);
        pLevel.setBlock(pPos.below(), ModBlocks.ETHEREAL_LONG_GRASS.get().defaultBlockState(), 3);
        pLevel.setBlock(pPos, ModBlocks.ETHEREAL_LONG_GRASS.get().defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }
}
