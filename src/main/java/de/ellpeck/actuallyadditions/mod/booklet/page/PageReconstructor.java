/*
 * This file ("PageReconstructor.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet.page;

import de.ellpeck.actuallyadditions.api.booklet.internal.GuiBookletBase;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.booklet.gui.GuiBooklet;
import de.ellpeck.actuallyadditions.mod.crafting.ActuallyRecipes;
import de.ellpeck.actuallyadditions.mod.crafting.LaserRecipe;
import de.ellpeck.actuallyadditions.mod.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PageReconstructor extends BookletPage {

    private final LaserRecipe recipe;
    private int counter = 0;
    private int rotate = 0;
    private ItemStack[] stacks;

    public PageReconstructor(int localizationKey, LaserRecipe recipe) {
        super(localizationKey);
        this.recipe = recipe;
        if (recipe != null) {
            this.stacks = recipe.getInput().getItems();
        }
    }

    public PageReconstructor(int localizationKey, IItemProvider itemProvider) {
        this(localizationKey, getRecipe(itemProvider));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawScreenPre(GuiBookletBase gui, int startX, int startY, int mouseX, int mouseY, float partialTicks) {
        super.drawScreenPre(gui, startX, startY, mouseX, mouseY, partialTicks);

        gui.getMinecraft().getTextureManager().bind(GuiBooklet.RES_LOC_GADGETS);
        GuiUtils.drawTexturedModalRect(startX + 30, startY + 10, 80, 146, 68, 48, 0);

        gui.renderScaledAsciiString("(" + StringUtil.localize("booklet." + ActuallyAdditions.MODID + ".reconstructorRecipe") + ")", startX + 6, startY + 63, 0, false, gui.getMediumFontSize());

        PageTextOnly.renderTextToPage(gui, this, startX + 6, startY + 88);
        if (this.recipe != null) {
            if (this.counter++ % 50 == 0) {
                gui.addOrModifyItemRenderer(this.stacks[this.rotate++ % this.stacks.length], startX + 30 + 1, startY + 10 + 13, 1F, true);
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void init(GuiBookletBase gui, int startX, int startY) {
        super.init(gui, startX, startY);

        if (this.recipe != null) {
            gui.addOrModifyItemRenderer(this.stacks[0], startX + 30 + 1, startY + 10 + 13, 1F, true);
            gui.addOrModifyItemRenderer(this.recipe.getResultItem(), startX + 30 + 47, startY + 10 + 13, 1F, false);
        }
    }

    @Override
    public void getItemStacksForPage(List<ItemStack> list) {
        super.getItemStacksForPage(list);

        if (this.recipe != null) {
            ItemStack copy = this.recipe.getResultItem().copy();
            list.add(copy);
        }
    }

    @Override
    public int getSortingPriority() {
        return 20;
    }

    private static LaserRecipe getRecipe(IItemProvider provider) {
        return getRecipe(new ItemStack(provider));
    }

    private static LaserRecipe getRecipe(ItemStack stack) {
        Minecraft mc = Minecraft.getInstance();
        World world = mc.level;
        if (world != null) {
            List<LaserRecipe> matchingRecipes = world.getRecipeManager().getAllRecipesFor(ActuallyRecipes.Types.LASER).stream()
                    .filter(recipe -> recipe.getResultItem().sameItem(stack)).collect(Collectors.toList());
            return matchingRecipes.isEmpty() ? null : matchingRecipes.get(0);
        }
        return null;
    }
}
