package dev.willyelton.variable_redstone.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import static dev.willyelton.variable_redstone.VariableRedstone.rl;

public record ChangeTickLengthPayload(int tickLength, BlockPos pos) implements CustomPacketPayload {
    public static final Type<ChangeTickLengthPayload> TYPE = new Type<>(rl("change_tick_length"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ChangeTickLengthPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ChangeTickLengthPayload::tickLength,
            BlockPos.STREAM_CODEC, ChangeTickLengthPayload::pos,
            ChangeTickLengthPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
