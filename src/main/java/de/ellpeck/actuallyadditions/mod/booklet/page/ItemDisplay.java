/*
 * This file ("ItemDisplay.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet.page;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.ellpeck.actuallyadditions.api.booklet.IBookletPage;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.booklet.gui.GuiBooklet;
import de.ellpeck.actuallyadditions.mod.booklet.gui.GuiPage;
import de.ellpeck.actuallyadditions.mod.booklet.misc.BookletUtils;
import de.ellpeck.actuallyadditions.mod.util.AssetUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.List;

public class ItemDisplay {

    public final int x;
    public final int y;
    public final float scale;
    private final GuiPage gui;
    private final IBookletPage page;
    public ItemStack stack;

    public ItemDisplay(GuiPage gui, int x, int y, float scale, ItemStack stack, boolean shouldTryTransfer) {
        this.gui = gui;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.stack = stack;
        this.page = shouldTryTransfer
                ? BookletUtils.findFirstPageForStack(stack)
                : null;
    }

    @OnlyIn(Dist.CLIENT)
    public void drawPre(MatrixStack matrices) {
        AssetUtil.renderStackToGui(matrices, this.stack, this.x, this.y, this.scale);
    }

    @OnlyIn(Dist.CLIENT)
    public void drawPost(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.isHovered(mouseX, mouseY)) {
            Minecraft mc = this.gui.getMinecraft();
//            boolean flagBefore = mc.font.getUnicodeFlag();
//            mc.font.setUnicodeFlag(false);

            List<ITextComponent> list = this.stack.getTooltipLines(mc.player, mc.options.advancedItemTooltips
                    ? TooltipFlags.ADVANCED
                    : TooltipFlags.NORMAL);

            for (int k = 0; k < list.size(); ++k) {
                if (k == 0) {
                    list.set(k, list.get(k).copy().withStyle(this.stack.getItem().getRarity(this.stack).color));
                } else {
                    list.set(k, list.get(k).copy().withStyle(TextFormatting.GRAY));
                }
            }

            if (this.page != null && this.page != this.gui.pages[0] && this.page != this.gui.pages[1]) {
                list.add(new TranslationTextComponent("booklet." + ActuallyAdditions.MODID + ".clickToSeeRecipe").withStyle(TextFormatting.GOLD));
            }

            GuiUtils.drawHoveringText(matrices, list, mouseX, mouseY, mc.screen.width, mc.screen.height, -1, mc.font);

//            mc.font.setUnicodeFlag(flagBefore);
        }
    }

    public void onMousePress(int button, int mouseX, int mouseY) {
        if (button == 0 && this.isHovered(mouseX, mouseY)) {
            if (this.page != null && this.page != this.gui.pages[0] && this.page != this.gui.pages[1]) {
                Minecraft mc = this.gui.getMinecraft();
                mc.getSoundManager().play(SimpleSound.forLocalAmbience(SoundEvents.UI_BUTTON_CLICK, 1.0F, 0.25F));

                GuiBooklet gui = BookletUtils.createPageGui(this.gui.previousScreen, this.gui, this.page);
                mc.setScreen(gui);
            }
        }
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + 16 * this.scale && mouseY < this.y + 16 * this.scale;
    }
}
