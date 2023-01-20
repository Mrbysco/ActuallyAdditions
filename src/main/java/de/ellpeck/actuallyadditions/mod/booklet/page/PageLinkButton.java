/*
 * This file ("PageLinkButton.java") is part of the Actually Additions mod for Minecraft.
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.net.URI;

public class PageLinkButton extends BookletPage {

    private final String link;

    public PageLinkButton(int localizationKey, String link) {
        super(localizationKey);
        this.link = link;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void init(GuiBookletBase gui, int startX, int startY) {
        super.init(gui, startX, startY);

        gui.getButtonList().add(new Button(startX + 125 / 2 - 50, startY + 130, 100, 20,
                new TranslationTextComponent("booklet." + ActuallyAdditions.MODID + ".chapter." + this.chapter.getIdentifier() + ".button." + this.localizationKey),
                (button) -> {
                    Minecraft  mc = Minecraft.getInstance();
                    mc.setScreen(new ConfirmOpenLinkScreen((yes) -> {
                        if (yes) {
                            Util.getPlatform().openUri(this.link);
                        }

                        mc.setScreen(null);
                    }, this.link, true));
                })
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawScreenPre(GuiBookletBase gui, int startX, int startY, int mouseX, int mouseY, float partialTicks) {
        super.drawScreenPre(gui, startX, startY, mouseX, mouseY, partialTicks);
        PageTextOnly.renderTextToPage(gui, this, startX + 6, startY + 5);
    }
}
