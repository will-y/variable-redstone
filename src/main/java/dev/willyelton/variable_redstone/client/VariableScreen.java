package dev.willyelton.variable_redstone.client;

import dev.willyelton.variable_redstone.common.network.ChangePowerPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class VariableScreen extends Screen {
    protected static final int SLIDER_WIDTH = 150;
    protected static final int SLIDER_HEIGHT = 25;

    protected final int startingValue;
    protected final BlockPos pos;
    private final int yOffset;

    public VariableScreen(int startingValue, BlockPos pos) {
        this(startingValue, pos, 0);
    }

    public VariableScreen(int startingValue, BlockPos pos, int yOffset) {
        super(Component.translatable("screen.variable.title"));

        this.startingValue = startingValue;
        this.pos = pos;
        this.yOffset = yOffset;
    }

    @Override
    protected void init() {
        int x = (this.width - SLIDER_WIDTH) / 2;
        int y = (this.height - SLIDER_HEIGHT) / 2 - yOffset;
        this.addRenderableOnly(new StringWidget(x, y - 25,
                SLIDER_WIDTH, SLIDER_HEIGHT, this.title, Minecraft.getInstance().font));
        this.addRenderableWidget(new PacketSendingSlider(x, y,
                SLIDER_WIDTH, SLIDER_HEIGHT, startingValue, pos, 15, ChangePowerPayload::new));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
