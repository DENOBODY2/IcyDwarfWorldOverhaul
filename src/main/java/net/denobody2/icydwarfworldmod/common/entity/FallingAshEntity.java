package net.denobody2.icydwarfworldmod.common.entity;

import net.denobody2.icydwarfworldmod.common.blocks.AshPileBlock;
import net.denobody2.icydwarfworldmod.registry.ModBlocks;
import net.denobody2.icydwarfworldmod.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class FallingAshEntity extends FallingBlockEntity {
    private BlockState ashState = ModBlocks.ASHEN_DUST_PILE.get().defaultBlockState();

    public FallingAshEntity(EntityType entityType, Level level) {
        super(entityType, level);
    }

    public FallingAshEntity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntities.FALLING_ASH.get(), level);
        this.setBoundingBox(this.makeBoundingBox());
    }

    private FallingAshEntity(Level level, double x, double y, double z, BlockState state) {
        this(ModEntities.FALLING_ASH.get(), level);
        this.ashState = state;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setStartPos(this.blockPosition());
    }

    public static FallingAshEntity fall(Level level, BlockPos pos, BlockState state) {
        FallingAshEntity fallingblockentity = new FallingAshEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)) : state);
        level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        level.addFreshEntity(fallingblockentity);
        return fallingblockentity;
    }


    public void tick() {
        if (this.getBlockState().isAir()) {
            this.discard();
        } else {
            Block block = this.getBlockState().getBlock();
            ++this.time;
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
            }
            this.move(MoverType.SELF, this.getDeltaMovement());
            if (!this.level().isClientSide) {
                BlockPos blockpos = this.blockPosition();
                if (!this.onGround()) {
                    if (!this.level().isClientSide && (this.time > 100 && (blockpos.getY() <= this.level().getMinBuildHeight() || blockpos.getY() > this.level().getMaxBuildHeight()) || this.time > 600)) {
                        if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            this.spawnAtLocation(block);
                        }

                        this.discard();
                    }
                } else {
                    BlockState blockstate = this.level().getBlockState(blockpos);
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
                    if (!blockstate.is(Blocks.MOVING_PISTON)) {
                        boolean flag2 = blockstate.canBeReplaced(new DirectionalPlaceContext(this.level(), blockpos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
                        boolean flag3 = FallingBlock.isFree(this.level().getBlockState(blockpos.below()));
                        boolean flag4 = this.ashState.canSurvive(this.level(), blockpos) && (!flag3 || this.level().getBlockState(blockpos.below()).is(ModBlocks.ASHEN_DUST_PILE.get()));
                        BlockState setState = this.getBlockState();
                        BlockState setAboveState = null;
                        if (blockstate.is(ModBlocks.ASHEN_DUST_PILE.get())) {
                            flag2 = true;
                            flag4 = true;
                            int belowLayers = blockstate.getValue(AshPileBlock.LAYERS);
                            int fallingLayers = ashState.is(ModBlocks.ASHEN_DUST_PILE.get()) ? ashState.getValue(AshPileBlock.LAYERS) : 8;
                            int together = belowLayers + fallingLayers;
                            setState = ModBlocks.ASHEN_DUST_PILE.get().defaultBlockState().setValue(AshPileBlock.LAYERS, Math.min(together, 8));
                            if (together > 8) {
                                int prev = 0;
                                if (level().getBlockState(blockpos.above()).is(ModBlocks.ASHEN_DUST_PILE.get())) {
                                    prev = level().getBlockState(blockpos.above()).getValue(AshPileBlock.LAYERS);
                                }
                                setAboveState = ModBlocks.ASHEN_DUST_PILE.get().defaultBlockState().setValue(AshPileBlock.LAYERS, Math.min(together - 8 + prev, 8));
                            }
                        }
                        if (flag2 && flag4) {
                            boolean flag5 = false;
                            if (this.level().setBlockAndUpdate(blockpos, setState)) {
                                this.discard();
                                if (block instanceof Fallable) {
                                    ((Fallable) block).onLand(this.level(), blockpos, setState, blockstate, this);
                                }
                                flag5 = true;
                            }
                            if (setAboveState != null) {
                                BlockPos abovePos = blockpos.above();
                                BlockState aboveState = this.level().getBlockState(abovePos);
                                if (aboveState.canBeReplaced(new DirectionalPlaceContext(this.level(), abovePos, Direction.DOWN, ItemStack.EMPTY, Direction.UP))) {
                                    this.level().setBlockAndUpdate(abovePos, setAboveState);
                                } else {
                                    this.spawnAtLocation(block);
                                }
                                this.discard();
                                flag5 = true;
                            }
                            if (!flag5 && this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                this.discard();
                                this.callOnBrokenAfterFall(block, blockpos);
                                this.spawnAtLocation(block);
                            }
                        } else {
                            this.discard();
                            if (this.dropItem && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                                this.callOnBrokenAfterFall(block, blockpos);
                                this.spawnAtLocation(block);
                            }
                        }
                    } else {
                        this.discard();
                        this.callOnBrokenAfterFall(block, blockpos);
                    }

                }
            }

            this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        }
    }

    private boolean isFullGuanoBlock(BlockState state) {
        return state.is(ModBlocks.ASHEN_DUST_PILE.get()) && state.getValue(AshPileBlock.LAYERS) == 8;
    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("BlockState", NbtUtils.writeBlockState(this.ashState));
    }

    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.ashState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), tag.getCompound("BlockState"));
    }

    public BlockState getBlockState() {
        return this.ashState;
    }


    public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket) {
        super.recreateFromPacket(clientboundAddEntityPacket);
        this.ashState = Block.stateById(clientboundAddEntityPacket.getData());
    }
}
