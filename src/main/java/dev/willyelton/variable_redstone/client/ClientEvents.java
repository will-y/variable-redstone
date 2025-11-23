package dev.willyelton.variable_redstone.client;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = VariableRedstone.MODID)
public class ClientEvents {
    private static final int COLOR_DELTA = 0x000A0A0A;
    private static final int LAMP_COLOR_DELTA = 0x00080808;

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        // Parameters are the block's state, the level the block is in, the block's position, and the tint index.
        // The level and position may be null.
        event.register((state, level, pos, tintIndex) -> {
                    // Replace with your own calculation. See the BlockColors class for vanilla references.
                    // Colors are in ARGB format. Generally, if the tint index is -1, it means that no tinting
                    // should take place and a default value should be used instead.
//            return -1;
                    return 0xFFFFFFFF - ((15 - state.getValue(VariableRedstone.POWER)) * COLOR_DELTA);
                },
                // A varargs of blocks to apply the tinting to
                VariableRedstone.VARIABLE_REDSTONE_TORCH.get(), VariableRedstone.VARIABLE_REDSTONE_WALL_TORCH.get(),
                VariableRedstone.VARIABLE_REDSTONE_BLOCK.get(),
                VariableRedstone.VARIABLE_LEVER.get(),
                VariableRedstone.VARIABLE_BUTTON.get(),
                // TODO: Tint the lamp differently and different state for 0 power
                VariableRedstone.VARIABLE_REDSTONE_LAMP.get());

        event.register((state, level, pos, tintIndex) -> {
                    return 0xFFFFFFFF - ((15 - state.getValue(VariableRedstone.POWER)) * LAMP_COLOR_DELTA);
                }, VariableRedstone.VARIABLE_REDSTONE_LAMP.get());
    }
}
