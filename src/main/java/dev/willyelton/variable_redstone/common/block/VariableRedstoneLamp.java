package dev.willyelton.variable_redstone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static dev.willyelton.variable_redstone.VariableRedstone.POWER;

public class VariableRedstoneLamp extends Block {
    public VariableRedstoneLamp() {
        super(BlockBehaviour.Properties.of()
                .lightLevel(state -> state.getValue(POWER))
                .strength(0.3F)
                .sound(SoundType.GLASS)
                .isValidSpawn(Blocks::always));
        this.registerDefaultState(this.defaultBlockState().setValue(POWER, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWER, context.getLevel().getBestNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            int power = state.getValue(POWER);
            int neighborSignal = level.getBestNeighborSignal(pos);
            if (power != neighborSignal) {
                level.setBlock(pos, state.setValue(POWER, neighborSignal), 3);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

}
