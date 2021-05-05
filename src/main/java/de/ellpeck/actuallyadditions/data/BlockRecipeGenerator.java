package de.ellpeck.actuallyadditions.data;

import com.google.gson.JsonObject;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.blocks.ActuallyBlocks;
import de.ellpeck.actuallyadditions.mod.items.ActuallyItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.nio.file.Path;
import java.util.function.Consumer;

public class BlockRecipeGenerator extends RecipeProvider {
    public BlockRecipeGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        //Battery Box
       ShapelessRecipeBuilder.shapelessRecipe(ActuallyBlocks.BATTERY_BOX.get())
                .addIngredient(ActuallyBlocks.ENERGIZER.get())
                .addIngredient(ActuallyBlocks.ENERVATOR.get())
                .addIngredient(ActuallyItems.COIL.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "battery_box"));

        //Farmer
       ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.FARMER.get())
                .patternLine("ISI")
                .patternLine("SCS")
                .patternLine("ISI")
                .key('I', ActuallyBlocks.CRYSTAL_ENORI.get())
                .key('C', ActuallyBlocks.IRON_CASING.get())
                .key('S', Tags.Items.SEEDS)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "farmer"));

       //Empowerer
       ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.EMPOWERER.get())
               .patternLine(" R ")
               .patternLine(" B ")
               .patternLine("CDC")
               .key('R', ActuallyItems.RESTONIA_CRYSTAL.get())
               .key('B', ActuallyItems.BATTERY_DOUBLE.get())
               .key('C', ActuallyBlocks.IRON_CASING.get())
               .key('D', ActuallyBlocks.DISPLAY_STAND.get())
               .addCriterion("", hasItem(Items.AIR))
               .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "empowerer"));

       //Tiny Torch coal
        ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.TINY_TORCH.get(), 2)
                .patternLine("C")
                .patternLine("S")
                .key('C', ActuallyItems.TINY_COAL.get())
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "tiny_torch_coal"));

        //Tiny Torch charcoal
        ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.TINY_TORCH.get(), 2)
                .patternLine("C")
                .patternLine("S")
                .key('C', ActuallyItems.TINY_CHARCOAL.get())
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "tiny_torch_charcoal"));

        //Fireworks Box
        ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.FIREWORK_BOX.get())
                .patternLine("GFG")
                .patternLine("SAS")
                .patternLine("CCC")
                .key('G', Tags.Items.GUNPOWDER)
                .key('S', Tags.Items.RODS_WOODEN)
                .key('A', ActuallyBlocks.IRON_CASING.get())
                .key('F', Items.FIREWORK_ROCKET)
                .key('C', ActuallyItems.ENORI_CRYSTAL.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "firework_box"));

        //Shock Suppressor
        ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.SHOCK_SUPPRESSOR.get())
                .patternLine("OAO")
                .patternLine("ACA")
                .patternLine("OAO")
                .key('A', ActuallyItems.VOID_EMPOWERED_CRYSTAL.get())
                .key('O', Tags.Items.OBSIDIAN)
                .key('C', ActuallyItems.COIL_ADVANCED.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "shock_suppressor"));

        //Display Stand
        ShapedRecipeBuilder.shapedRecipe(ActuallyBlocks.DISPLAY_STAND.get())
                .patternLine(" R ")
                .patternLine("EEE")
                .patternLine("GGG")
                .key('R', ActuallyItems.COIL_ADVANCED.get())
                .key('E', ActuallyBlocks.ETHETIC_GREEN_BLOCK.get())
                .key('G', ActuallyBlocks.ETHETIC_WHITE_BLOCK.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(ActuallyAdditions.MODID, "display_stand"));
    }

    @Override
    protected void saveRecipeAdvancement(DirectoryCache cache, JsonObject cache2, Path advancementJson) {
        //Nope...
    }
}
