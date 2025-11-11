package dev.willyelton.variable_redstone.common.block;

import dev.willyelton.variable_redstone.VariableRedstone;
import dev.willyelton.variable_redstone.client.VariableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

import static dev.willyelton.variable_redstone.VariableRedstone.POWER;

public class VariableRedstoneWallTorch extends RedstoneWallTorchBlock {

    public VariableRedstoneWallTorch() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_WALL_TORCH).lootFrom(VariableRedstone.VARIABLE_REDSTONE_TORCH));
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, true).setValue(POWER, 15));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            Minecraft.getInstance().setScreen(new VariableScreen(state.getValue(POWER), pos));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(LIT) && Direction.UP != side ? blockState.getValue(POWER) : 0;
    }
}
