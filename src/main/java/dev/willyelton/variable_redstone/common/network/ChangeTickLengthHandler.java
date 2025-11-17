package dev.willyelton.variable_redstone.common.network;

import dev.willyelton.variable_redstone.VariableRedstone;
import dev.willyelton.variable_redstone.common.ChunkPulseLengthData;
import dev.willyelton.variable_redstone.common.VariableRedstoneConfig;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ChangeTickLengthHandler {
    public static ChangeTickLengthHandler INSTANCE = new ChangeTickLengthHandler();

    public void handle(ChangeTickLengthPayload payload, IPayloadContext context) {
        Level level = context.player().level();

        LevelChunk chunk = level.getChunkAt(payload.pos());
        ChunkPulseLengthData pulseLengthData = chunk.getData(VariableRedstone.CHUNK_PULSE_LENGTH);
        pulseLengthData.setLength(payload.pos(), Math.min(payload.tickLength(), VariableRedstoneConfig.BUTTON_MAX_TICKS.get()));
        chunk.setUnsaved(true);
        chunk.syncData(VariableRedstone.CHUNK_PULSE_LENGTH);
    }
}
