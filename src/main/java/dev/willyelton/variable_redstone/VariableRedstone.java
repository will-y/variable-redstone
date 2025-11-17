package dev.willyelton.variable_redstone;

import dev.willyelton.variable_redstone.common.ChunkPulseLengthData;
import dev.willyelton.variable_redstone.common.VariableRedstoneConfig;
import dev.willyelton.variable_redstone.common.block.VariableButton;
import dev.willyelton.variable_redstone.common.block.VariableLever;
import dev.willyelton.variable_redstone.common.block.VariableRedstoneBlock;
import dev.willyelton.variable_redstone.common.block.VariableRedstoneTorch;
import dev.willyelton.variable_redstone.common.block.VariableRedstoneWallTorch;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(VariableRedstone.MODID)
public class VariableRedstone {
    public static final String MODID = "variable_redstone";
    public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    // Blocks
    public static final DeferredHolder<Block, Block> VARIABLE_REDSTONE_BLOCK = BLOCKS.registerBlock("variable_redstone_block", VariableRedstoneBlock::new);
    public static final DeferredHolder<Item, BlockItem> VARIABLE_REDSTONE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(VARIABLE_REDSTONE_BLOCK);

    public static final DeferredHolder<Block, VariableRedstoneTorch> VARIABLE_REDSTONE_TORCH = BLOCKS.register("variable_redstone_torch", VariableRedstoneTorch::new);
    public static final DeferredHolder<Block, VariableRedstoneWallTorch> VARIABLE_REDSTONE_WALL_TORCH = BLOCKS.register("variable_redstone_wall_torch", VariableRedstoneWallTorch::new);
    public static final DeferredHolder<Item, BlockItem> VARIABLE_REDSTONE_TORCH_BLOCK_ITEM = ITEMS.register("variable_redstone_torch", () -> new StandingAndWallBlockItem(VARIABLE_REDSTONE_TORCH.get(), VARIABLE_REDSTONE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

    public static final DeferredHolder<Block, VariableLever> VARIABLE_LEVER = BLOCKS.register("variable_lever", VariableLever::new);
    public static final DeferredHolder<Item, BlockItem> VARIABLE_LEVER_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(VARIABLE_LEVER);

    public static final DeferredHolder<Block, VariableButton> VARIABLE_BUTTON = BLOCKS.register("variable_button", VariableButton::new);
    public static final DeferredHolder<Item, BlockItem> VARIABLE_BUTTON_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(VARIABLE_BUTTON);

    // Data Attachments
    public static final Supplier<AttachmentType<ChunkPulseLengthData>> CHUNK_PULSE_LENGTH = ATTACHMENT_TYPES.register("chunk_pulse_length",
            () -> AttachmentType.builder(ChunkPulseLengthData::new)
                    .serialize(ChunkPulseLengthData.CODEC)
                    .sync(ChunkPulseLengthData.STREAM_CODEC)
                    .build());

    // Creative Tab
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("variable_redstone_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.variable_redstone"))
                    .icon(() -> new ItemStack(VARIABLE_REDSTONE_BLOCK.get()))
                    .displayItems((flags, output) -> {
                        output.accept(VARIABLE_REDSTONE_BLOCK_ITEM.get());
                        output.accept(VARIABLE_REDSTONE_TORCH_BLOCK_ITEM.get());
                        output.accept(VARIABLE_LEVER_BLOCK_ITEM.get());
                        output.accept(VARIABLE_BUTTON_BLOCK_ITEM.get());
                    })
                    .build());

    public VariableRedstone(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.SERVER, VariableRedstoneConfig.SERVER_CONFIG);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
