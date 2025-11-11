package dev.willyelton.variable_redstone.client;

import dev.willyelton.variable_redstone.common.network.ChangePowerPayload;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class VariableSlider extends AbstractSliderButton {
    private static final int MAX_VALUE = 15;

    private final BlockPos pos;
    private int lastPowerValue;

    public VariableSlider(int x, int y, int width, int height, int value, BlockPos pos) {
        super(x, y, width, height, Component.literal(String.valueOf(value)), value / (float) MAX_VALUE);

        this.pos = pos;
        lastPowerValue = value;
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
        if (currentPowerValue != lastPowerValue) {
            PacketDistributor.sendToServer(new ChangePowerPayload(currentPowerValue, pos));
            lastPowerValue = currentPowerValue;
        }
    }

    private int powerValue() {
        return (int) Math.round(value * MAX_VALUE);
    }
}
