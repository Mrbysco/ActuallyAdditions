/*
 * This file ("PageTrials.java") is part of the Actually Additions mod for Minecraft.
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
import de.ellpeck.actuallyadditions.mod.data.PlayerData;
import de.ellpeck.actuallyadditions.mod.data.PlayerData.PlayerSave;
import de.ellpeck.actuallyadditions.mod.network.PacketHandlerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PageTrials extends BookletPage {

    @OnlyIn(Dist.CLIENT)
    private Button button;

    public PageTrials(int localizationKey, boolean button, boolean text) {
        super(localizationKey);

        if (!text) {
            this.setNoText();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void init(GuiBookletBase gui, int startX, int startY) {
        super.init(gui, startX, startY);

        this.button = new Button(startX + 125 / 2 - 50, startY + 120, 100, 20, StringTextComponent.EMPTY, (button) -> {
            PlayerEntity player = Minecraft.getInstance().player;
            PlayerSave data = PlayerData.getDataFromPlayer(player);
            String id = this.chapter.getIdentifier();

            boolean completed = data.completedTrials.contains(id);
            if (completed) {
                data.completedTrials.remove(id);
            } else {
                data.completedTrials.add(id);
            }
            this.updateButton();

            PacketHandlerHelper.sendPlayerDataToServer(false, 2);
        });
        gui.getButtonList().add(this.button);
        this.updateButton();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawScreenPre(GuiBookletBase gui, int startX, int startY, int mouseX, int mouseY, float partialTicks) {
        super.drawScreenPre(gui, startX, startY, mouseX, mouseY, partialTicks);
        PageTextOnly.renderTextToPage(gui, this, startX + 6, startY + 5);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected String getLocalizationKey() {
        return "booklet." + ActuallyAdditions.MODID + ".trials." + this.chapter.getIdentifier() + ".text." + this.localizationKey;
    }

    @OnlyIn(Dist.CLIENT)
    private void updateButton() {
        if (this.button != null) {
            PlayerEntity player = Minecraft.getInstance().player;
            PlayerSave data = PlayerData.getDataFromPlayer(player);

            boolean completed = data.completedTrials.contains(this.chapter.getIdentifier());
            if (completed) {
                this.button.setMessage(new TranslationTextComponent("booklet." + ActuallyAdditions.MODID + ".trialFinishButton.completed.name").withStyle(TextFormatting.DARK_GREEN));
            } else {
                this.button.setMessage(new TranslationTextComponent("booklet." + ActuallyAdditions.MODID + ".trialFinishButton.uncompleted.name").withStyle(TextFormatting.DARK_RED));
            }
        }
    }
}
