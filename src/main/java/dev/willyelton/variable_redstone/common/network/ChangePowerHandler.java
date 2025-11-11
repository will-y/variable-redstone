package dev.willyelton.variable_redstone.common.network;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static dev.willyelton.variable_redstone.VariableRedstone.POWER;

public class ChangePowerHandler {
    public static ChangePowerHandler INSTANCE = new ChangePowerHandler();

    public void handle(ChangePowerPayload payload, IPayloadContext context) {
        Level level = context.player().level();

        BlockState prevState = level.getBlockState(payload.pos());

        if (prevState.hasProperty(POWER)) {
            level.setBlock(payload.pos(), prevState.setValue(POWER, payload.power()), 3);
        }
    }
}
