package dev.willyelton.variable_redstone.datagen;

import dev.willyelton.variable_redstone.VariableRedstone;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class VariableRedstoneRecipes extends RecipeProvider {
    public VariableRedstoneRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        shaped(RecipeCategory.REDSTONE, VariableRedstone.VARIABLE_REDSTONE_BLOCK.get(), 1)
                .pattern(" a ")
                .pattern("ara")
                .pattern(" a ")
                .define('a', Items.AMETHYST_SHARD)
                .define('r', Items.REDSTONE_BLOCK)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);

        shaped(RecipeCategory.REDSTONE, VariableRedstone.VARIABLE_REDSTONE_TORCH.get(), 1)
                .pattern("a")
                .pattern("t")
                .define('a', Items.AMETHYST_SHARD)
                .define('t', Items.REDSTONE_TORCH)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);

        shaped(RecipeCategory.REDSTONE, VariableRedstone.VARIABLE_LEVER.get(), 1)
                .pattern("a")
                .pattern("l")
                .define('a', Items.AMETHYST_SHARD)
                .define('l', Items.LEVER)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);

        shapeless(RecipeCategory.REDSTONE, VariableRedstone.VARIABLE_BUTTON.get(), 1)
                .requires(Items.AMETHYST_SHARD)
                .requires(ItemTags.BUTTONS)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);

        shaped(RecipeCategory.REDSTONE, VariableRedstone.VARIABLE_REDSTONE_LAMP.get(), 1)
                .pattern(" a ")
                .pattern("ala")
                .pattern(" a ")
                .define('a', Items.AMETHYST_SHARD)
                .define('l', Items.REDSTONE_LAMP)
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);
    }
}
