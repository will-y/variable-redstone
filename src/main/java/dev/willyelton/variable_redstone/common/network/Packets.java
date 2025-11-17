package dev.willyelton.variable_redstone.common.network;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = VariableRedstone.MODID)
public class Packets {
    @SubscribeEvent
    public static void registerNetworking(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = new PayloadRegistrar("1.0.0");

        registrar.playToServer(ChangePowerPayload.TYPE, ChangePowerPayload.STREAM_CODEC, ChangePowerHandler.INSTANCE::handle);
        registrar.playToServer(ChangeTickLengthPayload.TYPE, ChangeTickLengthPayload.STREAM_CODEC, ChangeTickLengthHandler.INSTANCE::handle);
    }
}
