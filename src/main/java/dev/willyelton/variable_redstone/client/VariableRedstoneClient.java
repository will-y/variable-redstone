package dev.willyelton.variable_redstone.client;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = VariableRedstone.MODID, dist = Dist.CLIENT)
public class VariableRedstoneClient {
    public VariableRedstoneClient(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
