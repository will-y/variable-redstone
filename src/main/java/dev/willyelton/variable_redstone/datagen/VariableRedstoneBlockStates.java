package dev.willyelton.variable_redstone.datagen;


import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class VariableRedstoneBlockStates extends BlockStateProvider {
    public VariableRedstoneBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VariableRedstone.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(VariableRedstone.VARIABLE_REDSTONE_BLOCK.get());
    }
}
