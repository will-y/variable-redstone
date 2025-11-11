package dev.willyelton.variable_redstone.datagen;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = VariableRedstone.MODID)
public class DataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new VariableRedstoneBlockStates(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new VariableRedstoneItemModels(packOutput, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(VariableRedstoneLootTables::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeServer(), new VariableRedstoneRecipes(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new VariableRedstoneBlockTags(packOutput, lookupProvider, event.getExistingFileHelper()));
    }
}
