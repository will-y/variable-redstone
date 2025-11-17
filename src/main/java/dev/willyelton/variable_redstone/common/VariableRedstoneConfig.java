package dev.willyelton.variable_redstone.common;

import net.neoforged.neoforge.common.ModConfigSpec;

public class VariableRedstoneConfig {
    public static final ModConfigSpec SERVER_CONFIG;

    public static ModConfigSpec.IntValue BUTTON_DEFAULT_TICKS;
    public static ModConfigSpec.IntValue BUTTON_MAX_TICKS;

    static {
        ModConfigSpec.Builder configBuilder = new ModConfigSpec.Builder();
        setupConfig(configBuilder);
        SERVER_CONFIG = configBuilder.build();
    }

    private static void setupConfig(ModConfigSpec.Builder builder) {
        BUTTON_DEFAULT_TICKS = builder.comment("The default number of (game) ticks that the variable button stays pressed")
                .defineInRange("button_default_ticks", 20, 1, 10000);
        BUTTON_MAX_TICKS = builder.comment("The max number of (game) ticks that the variable button can be set to stay pressed")
                .defineInRange("button_max_ticks", 100, 1, 10000);
    }
}
