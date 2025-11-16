package dev.willyelton.variable_redstone.datagen;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class VariableRedstoneBlockTags extends BlockTagsProvider {
    public VariableRedstoneBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VariableRedstone.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                VariableRedstone.VARIABLE_REDSTONE_BLOCK.get(),
                VariableRedstone.VARIABLE_LEVER.get()
        );
        tag(BlockTags.BUTTONS).add(
                VariableRedstone.VARIABLE_BUTTON.get()
        );
    }
}
