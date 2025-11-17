package dev.willyelton.variable_redstone.client;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.function.BiFunction;

public class PacketSendingSlider extends AbstractSliderButton {
    private final int maxValue;
    private final BiFunction<Integer, BlockPos, ? extends CustomPacketPayload> packetCreator;
    private final BlockPos pos;
    private int lastIntValue;

    public PacketSendingSlider(int x, int y, int width, int height, int value, BlockPos pos, int maxValue, BiFunction<Integer, BlockPos, ? extends CustomPacketPayload> packetCreator) {
        super(x, y, width, height, Component.literal(String.valueOf(value)), value / (float) maxValue);

        this.maxValue = maxValue;
        this.packetCreator = packetCreator;
        this.pos = pos;
        this.lastIntValue = value;
    }

    @Override
    protected void updateMessage() {
        // Call set message and set tooltip to round
        Component component = Component.literal(String.valueOf(powerValue()));
        this.setMessage(component);
    }

    @Override
    protected void applyValue() {
        int currentPowerValue = powerValue();
        if (currentPowerValue != lastIntValue) {
            PacketDistributor.sendToServer(packetCreator.apply(currentPowerValue, pos));
//            PacketDistributor.sendToServer(new ChangePowerPayload(currentPowerValue, pos));
            lastIntValue = currentPowerValue;
        }
    }

    private int powerValue() {
        return (int) Math.round(value * maxValue);
    }
}
