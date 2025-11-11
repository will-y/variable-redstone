package dev.willyelton.variable_redstone.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import static dev.willyelton.variable_redstone.VariableRedstone.rl;

public record ChangePowerPayload(int power, BlockPos pos) implements CustomPacketPayload {
    public static final Type<ChangePowerPayload> TYPE = new Type<>(rl("change_power"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ChangePowerPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ChangePowerPayload::power,
            BlockPos.STREAM_CODEC, ChangePowerPayload::pos,
            ChangePowerPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
