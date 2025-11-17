package dev.willyelton.variable_redstone.common;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.HashMap;
import java.util.Map;

public class ChunkPulseLengthData {
    private static final Codec<BlockPos> STRING_BLOCK_POS_CODEC = Codec.STRING.xmap(Long::parseLong, String::valueOf).xmap(BlockPos::of, BlockPos::asLong);
    public static final Codec<ChunkPulseLengthData> CODEC = Codec.unboundedMap(STRING_BLOCK_POS_CODEC, Codec.INT).xmap(ChunkPulseLengthData::new, ChunkPulseLengthData::getRawData);
    public static final StreamCodec<ByteBuf, ChunkPulseLengthData> STREAM_CODEC = ByteBufCodecs
            .map(ChunkPulseLengthData::mapGetter, BlockPos.STREAM_CODEC, ByteBufCodecs.INT)
            .map(ChunkPulseLengthData::new, ChunkPulseLengthData::getRawData);

    private static Map<BlockPos, Integer> mapGetter(int i) {
        return new HashMap<>(i);
    }

    private final Map<BlockPos, Integer> data;

    public ChunkPulseLengthData() {
        this.data = new HashMap<>();
    }

    private ChunkPulseLengthData(Map<BlockPos, Integer> data) {
        this.data = new HashMap<>(data);
    }

    public void setLength(BlockPos pos, int length) {
        this.data.put(pos, length);
    }

    public int getLength(BlockPos pos) {
        return this.data.get(pos) != null ? this.data.get(pos) : VariableRedstoneConfig.BUTTON_DEFAULT_TICKS.get();
    }

    public void remove(BlockPos pos) {
        this.data.remove(pos);
    }

    private Map<BlockPos, Integer> getRawData() {
        return data;
    }
}
