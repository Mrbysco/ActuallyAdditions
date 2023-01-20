/*
 * This file ("TrialsButton.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.booklet.gui.GuiBooklet;
import de.ellpeck.actuallyadditions.mod.inventory.gui.TexturedButton;
import de.ellpeck.actuallyadditions.mod.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class TrialsButton extends TexturedButton {

    private final boolean isTrials;

    public TrialsButton(GuiBooklet gui, IPressable action) {
        super(GuiBooklet.RES_LOC_GADGETS, gui.getGuiLeft() + gui.getSizeX(), gui.getGuiTop() + 10, 0, 204, 52, 16, action);
        this.isTrials = gui.areTrialsOpened();
        this.active = !this.isTrials;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        super.render(matrices, mouseX, mouseY, partialTicks);

        if (this.visible) {
            if (this.isHovered || this.isTrials) {
                Minecraft mc = Minecraft.getInstance();
                drawCenteredString(matrices, mc.font, new TranslationTextComponent("booklet." + ActuallyAdditions.MODID + ".trialsButton"), this.x + (this.width - 8) / 2, this.y + (this.height - 8) / 2, 14737632);
            }
        }
    }

    @Override
    protected int getYImage(boolean mouseOver) {
        if (mouseOver || this.isTrials) {
            return 2;
        } else if (!this.active) {
            return 0;
        } else {
            return 1;
        }
    }
}
