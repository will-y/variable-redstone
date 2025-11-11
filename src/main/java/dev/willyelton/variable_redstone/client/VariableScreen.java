package dev.willyelton.variable_redstone.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class VariableScreen extends Screen {
    private static final int SLIDER_WIDTH = 150;
    private static final int SLIDER_HEIGHT = 25;

    private final int startingValue;
    private final BlockPos pos;

    public VariableScreen(int startingValue, BlockPos pos) {
        super(Component.translatable("screen.variable.title"));

        this.startingValue = startingValue;
        this.pos = pos;
    }

    @Override
    protected void init() {
        int x = (this.width - SLIDER_WIDTH) / 2;
        int y = (this.height - SLIDER_HEIGHT) / 2;
        this.addRenderableOnly(new StringWidget(x, y - 25,
                SLIDER_WIDTH, SLIDER_HEIGHT, this.title, Minecraft.getInstance().font));
        this.addRenderableWidget(new VariableSlider(x, y,
                SLIDER_WIDTH, SLIDER_HEIGHT, startingValue, pos));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
