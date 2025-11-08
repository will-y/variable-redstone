package dev.willyelton.variable_redstone;

import dev.willyelton.variable_redstone.common.block.VariableRedstoneBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(VariableRedstone.MODID)
public class VariableRedstone {
    public static final String MODID = "variable_redstone";
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Blocks
    public static final DeferredHolder<Block, Block> VARIABLE_REDSTONE_BLOCK = BLOCKS.registerBlock("variable_redstone_block", VariableRedstoneBlock::new);
    public static final DeferredHolder<Item, BlockItem> VARIABLE_REDSTONE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(VARIABLE_REDSTONE_BLOCK);

    // Creative Tab
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("variable_redstone_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.variable_redstone"))
                    .icon(() -> new ItemStack(VARIABLE_REDSTONE_BLOCK.get()))
                    .displayItems((flags, output) -> {
                        output.accept(VARIABLE_REDSTONE_BLOCK_ITEM.get());
                    })
                    .build());

    public VariableRedstone(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

}
