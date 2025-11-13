package dev.willyelton.variable_redstone.datagen;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Collectors;

public class VariableRedstoneLootTables extends VanillaBlockLoot {
    public VariableRedstoneLootTables(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        dropSelf(VariableRedstone.VARIABLE_REDSTONE_BLOCK.get());
        dropSelf(VariableRedstone.VARIABLE_REDSTONE_TORCH.get());
        dropSelf(VariableRedstone.VARIABLE_LEVER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(VariableRedstone.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
