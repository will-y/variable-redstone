package dev.willyelton.variable_redstone.datagen;


import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class VariableRedstoneBlockStates extends BlockStateProvider {
    public VariableRedstoneBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VariableRedstone.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerRedstoneBlock(VariableRedstone.VARIABLE_REDSTONE_BLOCK, "variable_redstone_block");
        registerTorch();
        registerWallTorch();
    }

    private void registerRedstoneBlock(DeferredHolder<Block, ?> holder, String name) {
        var b = models().withExistingParent(name, mcLoc("block/cube_all"))
                .texture("all", modLoc("block/variable_redstone_block"))
                .element()
                .allFaces((dir, builder) -> {
                    builder.tintindex(0);
                    builder.texture("#all");
                })
                .end();

        simpleBlock(VariableRedstone.VARIABLE_REDSTONE_BLOCK.get(), ConfiguredModel.builder().modelFile(b).build());
    }

    private void registerTorch() {
        BlockModelBuilder torchModel = models().getBuilder(VariableRedstone.VARIABLE_REDSTONE_TORCH.getId().getPath())
                .parent(models().getExistingFile(mcLoc("block/template_torch")))
                .renderType("cutout")
                .texture("torch", modLoc("block/variable_redstone_torch"))
                .element()
                    .shade(false)
                    .from(7, 0, 7)
                    .to(9, 10, 9)
                    .face(Direction.UP)
                        .tintindex(0)
                        .texture("#torch")
                        .uvs(7, 6, 9, 8)
                    .end()
                    .face(Direction.DOWN)
                        .tintindex(0)
                        .texture("#torch")
                        .uvs(7, 6, 9, 8)
                    .end()
                .end()
                .element()
                    .shade(false)
                    .from(7, 0, 0)
                    .to(9, 16, 16)
                    .face(Direction.WEST)
                        .tintindex(0)
                        .texture("#torch")
                        .uvs(0, 0, 16, 16)
                    .end()
                    .face(Direction.EAST)
                        .tintindex(0)
                        .texture("#torch")
                        .uvs(0, 0, 16, 16)
                    .end()
                .end()
                    .element()
                    .shade(false)
                    .from(0, 0, 7)
                    .to(16, 16, 9)
                    .face(Direction.NORTH)
                        .tintindex(0)
                        .texture("#torch")
                        .uvs(0, 0, 16, 16)
                    .end()
                    .face(Direction.SOUTH)
                        .tintindex(0)
                        .texture("#torch")
                        .uvs(0, 0, 16, 16)
                    .end()
                .end();

        BlockModelBuilder offModel = models().getBuilder(VariableRedstone.VARIABLE_REDSTONE_TORCH.getId().getPath() + "_off")
                .parent(models().getExistingFile(mcLoc("block/template_torch")))
                .renderType("cutout")
                .texture("torch", modLoc("block/variable_redstone_torch_off"));

        getVariantBuilder(VariableRedstone.VARIABLE_REDSTONE_TORCH.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(state.getValue(RedstoneTorchBlock.LIT) ? torchModel : offModel)
                        .build());
    }

    private void registerWallTorch() {
        BlockModelBuilder wallTorchModel = models().getBuilder(VariableRedstone.VARIABLE_REDSTONE_WALL_TORCH.getId().getPath())
                .parent(models().getExistingFile(mcLoc("block/template_torch_wall")))
                .renderType("cutout")
                .texture("torch", modLoc("block/variable_redstone_torch"))
                .element()
                    .from(-1, 3.5F, 7)
                    .to(1, 13.5F, 9)
                    .rotation()
                        .origin(0, 3.5F, 8)
                        .axis(Direction.Axis.Z)
                        .angle(-22.5F)
                    .end()
                    .shade(false)
                    .face(Direction.UP)
                        .uvs(7, 6, 9, 8)
                        .texture("#torch")
                        .tintindex(0)
                    .end()
                    .face(Direction.DOWN)
                        .uvs(7, 13, 9, 15)
                        .texture("#torch")
                        .tintindex(0)
                    .end()
                .end()
                .element()
                    .from(-1, 3.5F, 0)
                    .to(1, 19.5F, 16)
                    .rotation()
                        .origin(0, 3.5F, 8)
                        .axis(Direction.Axis.Z)
                        .angle(-22.5F)
                    .end()
                    .shade(false)
                    .face(Direction.EAST)
                        .uvs(0, 0, 16, 16)
                        .texture("#torch")
                        .tintindex(0)
                    .end()
                    .face(Direction.WEST)
                        .uvs(0, 0, 16, 16)
                        .texture("#torch")
                        .tintindex(0)
                    .end()
                .end()
                .element()
                    .from(-8, 3.5F, 7)
                    .to(8, 19.5F, 9)
                    .rotation()
                        .origin(0, 3.5F, 8)
                        .axis(Direction.Axis.Z)
                        .angle(-22.5F)
                    .end()
                    .shade(false)
                    .face(Direction.NORTH)
                        .uvs(0, 0, 16, 16)
                        .texture("#torch")
                        .tintindex(0)
                    .end()
                    .face(Direction.SOUTH)
                        .uvs(0, 0, 16, 16)
                        .texture("#torch")
                        .tintindex(0)
                    .end()
                .end();

        BlockModelBuilder offModel = models().getBuilder(VariableRedstone.VARIABLE_REDSTONE_WALL_TORCH.getId().getPath() + "_off")
                .parent(models().getExistingFile(mcLoc("block/template_torch_wall")))
                .renderType("cutout")
                .texture("torch", modLoc("block/variable_redstone_torch_off"));

        horizontalBlock(VariableRedstone.VARIABLE_REDSTONE_WALL_TORCH.get(),
                state -> state.getValue(RedstoneTorchBlock.LIT) ? wallTorchModel : offModel, 90);
    }
}
