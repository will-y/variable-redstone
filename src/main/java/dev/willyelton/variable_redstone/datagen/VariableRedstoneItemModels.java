package dev.willyelton.variable_redstone.datagen;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static dev.willyelton.variable_redstone.VariableRedstone.rl;

public class VariableRedstoneItemModels extends ItemModelProvider {
    public VariableRedstoneItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VariableRedstone.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(VariableRedstone.VARIABLE_REDSTONE_BLOCK.getId().getPath(), modLoc("block/variable_redstone_block"));
        getBuilder(VariableRedstone.VARIABLE_REDSTONE_TORCH.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", rl("block/variable_redstone_torch"));
        getBuilder(VariableRedstone.VARIABLE_LEVER.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", rl("block/variable_lever"));
    }
}
