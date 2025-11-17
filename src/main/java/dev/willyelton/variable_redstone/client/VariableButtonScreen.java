package dev.willyelton.variable_redstone.client;

import dev.willyelton.variable_redstone.common.VariableRedstoneConfig;
import dev.willyelton.variable_redstone.common.network.ChangeTickLengthPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class VariableButtonScreen extends VariableScreen {
    private final int startingPulse;

    public VariableButtonScreen(int powerStartingValue, BlockPos pos, int startingPulse) {
        super(powerStartingValue, pos, 25);

        this.startingPulse = startingPulse;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - SLIDER_WIDTH) / 2;
        int y = (this.height - SLIDER_HEIGHT) / 2 + 25;
        this.addRenderableOnly(new StringWidget(x, y - 25,
                SLIDER_WIDTH, SLIDER_HEIGHT, Component.translatable("screen.variable_redstone.tick_length"), Minecraft.getInstance().font));
        this.addRenderableWidget(new PacketSendingSlider(x, y,
                SLIDER_WIDTH, SLIDER_HEIGHT, startingPulse, pos, VariableRedstoneConfig.BUTTON_MAX_TICKS.get(), ChangeTickLengthPayload::new));
    }
}
