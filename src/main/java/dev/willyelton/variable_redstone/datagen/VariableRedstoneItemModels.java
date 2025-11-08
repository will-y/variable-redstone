package dev.willyelton.variable_redstone.datagen;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class VariableRedstoneItemModels extends ItemModelProvider {
    public VariableRedstoneItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VariableRedstone.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(VariableRedstone.VARIABLE_REDSTONE_BLOCK.getId().getPath(), modLoc("block/variable_redstone_block"));
    }
}
