package dev.willyelton.variable_redstone.datagen;


import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock.FACE;
import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;
import static net.minecraft.world.level.block.LeverBlock.POWERED;
import static net.minecraft.world.level.block.state.properties.AttachFace.FLOOR;
import static net.minecraft.world.level.block.state.properties.AttachFace.WALL;

public class VariableRedstoneBlockStates extends BlockStateProvider {
    public VariableRedstoneBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VariableRedstone.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerRedstoneBlock(VariableRedstone.VARIABLE_REDSTONE_BLOCK, "variable_redstone_block");
        registerTorch();
        registerWallTorch();
        registerLever();
        registerButton();
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

    private void registerLever() {
        BlockModelBuilder leverOff = getLeverModel("", -45);
        BlockModelBuilder leverOn = getLeverModel("_on", 45);

        getVariantBuilder(VariableRedstone.VARIABLE_LEVER.get())
                .forAllStates(state -> {
                    ModelFile modelFile = state.getValue(POWERED) ? leverOn : leverOff;
                    int[] rot = rotFromDir(state.getValue(FACE), state.getValue(FACING));
                    return ConfiguredModel.builder()
                            .modelFile(modelFile)
                            .rotationX(rot[0])
                            .rotationY(rot[1])
                            .build();
                });
    }

    private void registerButton() {
        BlockModelBuilder button = getButtonModel("", 2);
        BlockModelBuilder buttonPressed = getButtonModel("_pressed", 1);

        getVariantBuilder(VariableRedstone.VARIABLE_BUTTON.get())
                .forAllStates(state -> {
                    ModelFile modelFile = state.getValue(POWERED) ? buttonPressed : button;
                    int[] rot = rotFromDir(state.getValue(FACE), state.getValue(FACING));
                    return ConfiguredModel.builder()
                            .modelFile(modelFile)
                            .rotationX(rot[0])
                            .rotationY(rot[1])
                            .build();
                });
    }

    private int[] rotFromDir(AttachFace attachFace, Direction facing) {
        int x = switch (attachFace) {
            case WALL -> 90;
            case FLOOR -> 0;
            case CEILING -> 180;
        };
        
        int y = switch(facing) {
            case DOWN, UP -> 0;
            case NORTH -> attachFace == WALL || attachFace == FLOOR ? 0 : 180;
            case SOUTH -> attachFace == WALL || attachFace == FLOOR ? 180 : 0;
            case WEST -> attachFace == WALL || attachFace == FLOOR ? 270 : 90;
            case EAST -> attachFace == WALL || attachFace == FLOOR ? 90 : 270;
        };

        return new int[]{x, y};
    }

    private BlockModelBuilder getLeverModel(String suffix, float angle) {
        return models().getBuilder(VariableRedstone.VARIABLE_LEVER.getId().getPath() + suffix)
                .texture("particle", mcLoc("block/cobblestone"))
                .texture("base", mcLoc("block/cobblestone"))
                .texture("lever", modLoc("block/variable_lever"))
                .ao(false)
                .element()
                .from(5, -0.02F, 4)
                .to(11, 2.98F, 12)
                .allFaces((dir, faceBuilder) -> {
                    faceBuilder.texture("#base");
                    switch (dir) {
                        case DOWN:
                            faceBuilder.cullface(Direction.DOWN);
                        case UP:
                            faceBuilder.uvs(5, 4, 11, 12);
                            break;
                        case NORTH:
                        case SOUTH:
                            faceBuilder.uvs(5, 0, 11, 3);
                            break;
                        case EAST:
                        case WEST:
                            faceBuilder.uvs(4, 0, 12, 3);
                            break;
                    }
                })
                .end()
                .element()
                    .from(7, 1, 7)
                    .to(9, 11, 9)
                    .rotation()
                        .origin(8, 1, 8)
                        .axis(Direction.Axis.X)
                        .angle(angle)
                    .end()
                    .allFaces((dir, faceBuilder) -> {
                        faceBuilder.texture("#lever")
                                .tintindex(0);
                        switch (dir) {
                            case UP:
                                faceBuilder.uvs(7, 6, 9, 8);
                                break;
                            case DOWN:
                            case NORTH:
                            case SOUTH:
                            case EAST:
                            case WEST:
                                faceBuilder.uvs(7, 6, 9, 16);
                                break;
                        }
                    })
                .end();
    }

    private BlockModelBuilder getButtonModel(String suffix, int height) {
        return models().getBuilder(VariableRedstone.VARIABLE_BUTTON.getId().getPath() + suffix)
                .texture("texture", modLoc("block/variable_redstone_block"))
                .texture("particle", "#texture")
                .element()
                .from(5, 0, 6)
                .to(11, height == 1 ? 1.02F : 2, 10)
                .allFaces((dir, faceBuilder) -> {
                    faceBuilder.texture("#texture");
                    faceBuilder.tintindex(0);
                    switch (dir) {
                        case DOWN:
                            faceBuilder.cullface(Direction.DOWN);
                        case UP:
                            faceBuilder.uvs(4, 2, 10, 6);
                            break;
                        case NORTH:
                        case SOUTH:
                            faceBuilder.uvs(5, 12, 11, 12 + height);
                            break;
                        case EAST:
                        case WEST:
                            faceBuilder.uvs(6, 12, 10, 12 + height);
                            break;
                    }
                })
                .end();
    }
}
