/*
 * This file ("InitBooklet.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.booklet;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import de.ellpeck.actuallyadditions.api.booklet.IBookletChapter;
import de.ellpeck.actuallyadditions.api.booklet.IBookletEntry;
import de.ellpeck.actuallyadditions.api.booklet.IBookletPage;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.blocks.ActuallyBlocks;
import de.ellpeck.actuallyadditions.mod.booklet.chapter.BookletChapter;
import de.ellpeck.actuallyadditions.mod.booklet.chapter.BookletChapterCoffee;
import de.ellpeck.actuallyadditions.mod.booklet.chapter.BookletChapterCrusher;
import de.ellpeck.actuallyadditions.mod.booklet.chapter.BookletChapterTrials;
import de.ellpeck.actuallyadditions.mod.booklet.entry.BookletEntry;
import de.ellpeck.actuallyadditions.mod.booklet.entry.BookletEntryAllItems;
import de.ellpeck.actuallyadditions.mod.booklet.entry.BookletEntryTrials;
import de.ellpeck.actuallyadditions.mod.booklet.page.BookletPage;
import de.ellpeck.actuallyadditions.mod.booklet.page.PageCrafting;
import de.ellpeck.actuallyadditions.mod.booklet.page.PageEmpowerer;
import de.ellpeck.actuallyadditions.mod.booklet.page.PageLinkButton;
import de.ellpeck.actuallyadditions.mod.booklet.page.PagePicture;
import de.ellpeck.actuallyadditions.mod.booklet.page.PageReconstructor;
import de.ellpeck.actuallyadditions.mod.booklet.page.PageTextOnly;
import de.ellpeck.actuallyadditions.mod.config.values.ConfigIntValues;
import de.ellpeck.actuallyadditions.mod.fluids.InitFluids;
import de.ellpeck.actuallyadditions.mod.items.ActuallyItems;
import de.ellpeck.actuallyadditions.mod.items.ItemWingsOfTheBats;
import de.ellpeck.actuallyadditions.mod.items.lens.LensDisenchanting;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityAtomicReconstructor;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityCoffeeMachine;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityCrusher;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityFireworkBox;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityHeatCollector;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityLaserRelay;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityLaserRelayEnergy;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityLaserRelayEnergyAdvanced;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityLaserRelayEnergyExtreme;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityLavaFactoryController;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityLongRangeBreaker;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityPhantomPlacer;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityPhantomface;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityPlayerInterface;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityPoweredFurnace;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityRangedCollector;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityShockSuppressor;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityVerticalDigger;
import de.ellpeck.actuallyadditions.mod.update.UpdateChecker;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class InitBooklet {

    public static BookletChapter[] chaptersIntroduction = new BookletChapter[11];

    public static void init() {
        ActuallyAdditionsAPI.entryAllAndSearch = new BookletEntryAllItems("allAndSearch").setImportant();
        ActuallyAdditionsAPI.entryTrials = new BookletEntryTrials("trials");

        ActuallyAdditionsAPI.entryGettingStarted = new BookletEntry("gettingStarted").setImportant();
        ActuallyAdditionsAPI.entryReconstruction = new BookletEntry("reconstruction");
        ActuallyAdditionsAPI.entryLaserRelays = new BookletEntry("laserRelays").setSpecial();
        ActuallyAdditionsAPI.entryFunctionalNonRF = new BookletEntry("functionalNoRF");
        ActuallyAdditionsAPI.entryFunctionalRF = new BookletEntry("functionalRF");
        ActuallyAdditionsAPI.entryGeneratingRF = new BookletEntry("generatingRF");
        ActuallyAdditionsAPI.entryItemsNonRF = new BookletEntry("itemsNoRF");
        ActuallyAdditionsAPI.entryItemsRF = new BookletEntry("itemsRF");
        ActuallyAdditionsAPI.entryMisc = new BookletEntry("misc");
        ActuallyAdditionsAPI.entryUpdatesAndInfos = new BookletEntry("updatesAndInfos").setSpecial();

        initChapters();

        int chapCount = 0;
        int pageCount = 0;
        int infoCount = 0;
        for (IBookletEntry entry : ActuallyAdditionsAPI.BOOKLET_ENTRIES) {
            for (IBookletChapter chapter : entry.getAllChapters()) {
                if (!ActuallyAdditionsAPI.ALL_CHAPTERS.contains(chapter)) {
                    ActuallyAdditionsAPI.ALL_CHAPTERS.add(chapter);
                    chapCount++;

                    for (IBookletPage page : chapter.getAllPages()) {
                        pageCount++;

                        List<ItemStack> items = new ArrayList<>();
                        page.getItemStacksForPage(items);
                        List<FluidStack> fluids = new ArrayList<>();
                        page.getFluidStacksForPage(fluids);

                        if (items != null && !items.isEmpty() || fluids != null && !fluids.isEmpty()) {
                            if (!ActuallyAdditionsAPI.BOOKLET_PAGES_WITH_ITEM_OR_FLUID_DATA.contains(page)) {
                                ActuallyAdditionsAPI.BOOKLET_PAGES_WITH_ITEM_OR_FLUID_DATA.add(page);
                                infoCount++;
                            }
                        }
                    }
                }
            }
        }

        Collections.sort(ActuallyAdditionsAPI.BOOKLET_ENTRIES, (entry1, entry2) -> {
            Integer prio1 = entry1.getSortingPriority();
            Integer prio2 = entry2.getSortingPriority();
            return prio2.compareTo(prio1);
        });
        Collections.sort(ActuallyAdditionsAPI.ALL_CHAPTERS, (chapter1, chapter2) -> {
            Integer prio1 = chapter1.getSortingPriority();
            Integer prio2 = chapter2.getSortingPriority();
            return prio2.compareTo(prio1);
        });
        Collections.sort(ActuallyAdditionsAPI.BOOKLET_PAGES_WITH_ITEM_OR_FLUID_DATA, (page1, page2) -> {
            Integer prio1 = page1.getSortingPriority();
            Integer prio2 = page2.getSortingPriority();
            return prio2.compareTo(prio1);
        });

        ActuallyAdditions.LOGGER.info("Registered a total of " + chapCount + " booklet chapters, where " + infoCount + " out of " + pageCount + " booklet pages contain information about items or fluids!");
    }

    private static void initChapters() {

        //Getting Started
        chaptersIntroduction[0] = new BookletChapter("bookTutorial", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(ActuallyItems.ITEM_BOOKLET.get()), new PageTextOnly(1), new PageTextOnly(2), new PageTextOnly(3), new PageCrafting(4, ActuallyItems.ITEM_BOOKLET.get()).setNoText());
        chaptersIntroduction[1] = new BookletChapter("videoGuide", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(Items.COAL/*, TODO: AYO WTF? ITEM THAT IS JUST A LOGO? 1, TheMiscItems.YOUTUBE_ICON.ordinal()*/), new PageLinkButton(1, "https://www.youtube.com/watch?v=fhjz0Ew56pM"), new PageLinkButton(2, "https://www.youtube.com/playlist?list=PLJeFZ64pT89MrTRZYzD_rtHFajPVlt6cF")).setImportant();
        new BookletChapter("intro", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(ActuallyItems.ITEM_BOOKLET.get()), new PageTextOnly(1), new PageTextOnly(2), new PageTextOnly(3));
        ArrayList<BookletPage> crystalPages = new ArrayList<>();
        crystalPages.addAll(Arrays.asList(new PageTextOnly(1).addTextReplacement("<rf>", TileEntityAtomicReconstructor.ENERGY_USE), new PageTextOnly(2), new PageTextOnly(3), new PagePicture(4, "page_atomic_reconstructor", 0).setNoText(), new PageTextOnly(5), new PageCrafting(6, ActuallyBlocks.ATOMIC_RECONSTRUCTOR.get())));
//        for (int i = 0; i < LensRecipeHandler.MAIN_PAGE_RECIPES.size(); i++) {
//            crystalPages.add(new PageReconstructor(7 + i, LensRecipeHandler.MAIN_PAGE_RECIPES.get(i)).setNoText());
//        }
//        crystalPages.add(new PageCrafting(crystalPages.size() + 1, MiscCrafting.RECIPES_CRYSTALS).setNoText());
//        crystalPages.add(new PageCrafting(crystalPages.size() + 1, MiscCrafting.RECIPES_CRYSTAL_BLOCKS).setNoText());
        chaptersIntroduction[2] = new BookletChapter("engineerHouse", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(Items.EMERALD), new PageTextOnly(1), new PagePicture(2, "page_engineer_house", 145));
        chaptersIntroduction[6] = new BookletChapter("crystals", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(ActuallyBlocks.ATOMIC_RECONSTRUCTOR.get()), crystalPages.toArray(new BookletPage[crystalPages.size()])).setSpecial();
        chaptersIntroduction[5] = new BookletChapter("coalGen", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(ActuallyBlocks.COAL_GENERATOR.get()), new PageTextOnly(1).addTextReplacement("<rf>", 30/*ConfigIntValues.COAL_GENERATOR_CF_PRODUCTION.getValue()*/), new PageCrafting(2, ActuallyBlocks.COAL_GENERATOR.get()).setNoText());
//        ArrayList<BookletPage> empowererPages = new ArrayList<>();
//        empowererPages.addAll(Arrays.asList(new PageTextOnly(1), new PagePicture(2, "page_empowerer", 137), new PageCrafting(3, BlockCrafting.recipeEmpowerer), new PageCrafting(4, BlockCrafting.recipeDisplayStand)));
//        for (int i = 0; i < EmpowererHandler.MAIN_PAGE_RECIPES.size(); i++) {
//            empowererPages.add(new PageEmpowerer(7 + i, EmpowererHandler.MAIN_PAGE_RECIPES.get(i)).setNoText());
//        }
//        empowererPages.add(new PageCrafting(empowererPages.size() + 1, MiscCrafting.RECIPES_EMPOWERED_CRYSTALS).setNoText());
//        empowererPages.add(new PageCrafting(empowererPages.size() + 1, MiscCrafting.RECIPES_EMPOWERED_CRYSTAL_BLOCKS).setNoText());
//        new BookletChapter("empowerer", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(ActuallyBlocks.EMPOWERER.get()), empowererPages.toArray(new BookletPage[empowererPages.size()])).setSpecial();
        new BookletChapter("craftingIngs", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(ActuallyItems.BASIC_COIL.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.BASIC_COIL.get()).setNoText(), new PageCrafting(3, ActuallyItems.ADVANCED_COIL.get()).setNoText(), /*new PageCrafting(4, BlockCrafting.recipeCase).setNoText(), new PageCrafting(5, ActuallyBlocks.recipeEnderPearlBlock).setNoText(),*/ new PageCrafting(6, ActuallyBlocks.ENDER_CASING.getBlock()).setNoText(), /*new PageCrafting(7, ItemCrafting.recipeRing).setNoText(), new PageCrafting(8, ItemCrafting.recipeKnifeHandle).setNoText(), new PageCrafting(9, ItemCrafting.recipeKnifeBlade).setNoText(), new PageCrafting(10, ItemCrafting.recipeKnife).setNoText(), new PageCrafting(11, ItemCrafting.recipeDough).setNoText(), new PageCrafting(12, ItemCrafting.recipeRiceDough).setNoText(), */new PageCrafting(13, ActuallyBlocks.IRON_CASING.get()).setNoText(), new PageCrafting(14, ActuallyItems.LENS.get()).setNoText());
        chaptersIntroduction[4] = new BookletChapter("rf", ActuallyAdditionsAPI.entryGettingStarted, new ItemStack(Items.REDSTONE), new PageTextOnly(1), new PageTextOnly(2)).setImportant();

        //Miscellaneous
        new BookletChapter("worms", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.WORM.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.WORM.get())), new PagePicture(2, "page_worms", 145)).setImportant();
        new BookletChapter("lushCaves", ActuallyAdditionsAPI.entryMisc, new ItemStack(Blocks.STONE), new PageTextOnly(1), new PagePicture(2, "page_lush_caves", 0).setNoText());
        Block[] crystalBlocks = new Block[]{ActuallyBlocks.ENORI_CRYSTAL_CLUSTER.get(), ActuallyBlocks.RESTONIA_CRYSTAL_CLUSTER.get(), ActuallyBlocks.PALIS_CRYSTAL_CLUSTER.get(), ActuallyBlocks.DIAMATINE_CRYSTAL_CLUSTER.get(), ActuallyBlocks.VOID_CRYSTAL_CLUSTER.get(), ActuallyBlocks.EMERADIC_CRYSTAL_CLUSTER.get()};
        Item[] crystalItems = new Item[]{ActuallyItems.ENORI_CRYSTAL.get(), ActuallyItems.RESTONIA_CRYSTAL.get(), ActuallyItems.PALIS_CRYSTAL.get(), ActuallyItems.DIAMATINE_CRYSTAL.get(), ActuallyItems.VOID_CRYSTAL.get(), ActuallyItems.EMERADIC_CRYSTAL.get()};
        new BookletChapter("crystalClusters", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyBlocks.EMERADIC_CRYSTAL_CLUSTER.get()), new PageTextOnly(1).addItemsToPage(crystalBlocks), new PageCrafting(2, crystalBlocks).setNoText(), new PageCrafting(3, crystalItems).setNoText()).setSpecial();
        new BookletChapter("banners", ActuallyAdditionsAPI.entryMisc, new ItemStack(Items.BLUE_BANNER, 1), new PageTextOnly(1));
        new BookletChapter("miscDecorStuffsAndThings", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyBlocks.ETHETIC_GREEN_BLOCK.get()), new PageTextOnly(1), new PageReconstructor(2, ActuallyBlocks.ETHETIC_WHITE_WALL.get()).setNoText(), new PageReconstructor(3, ActuallyBlocks.ETHETIC_GREEN_WALL.get()).setNoText());
        chaptersIntroduction[3] = new BookletChapter("quartz", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.BLACK_QUARTZ.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyBlocks.BLACK_QUARTZ_ORE.get())).addTextReplacement("<lowest>", /*AAWorldGen.QUARTZ_MIN*/ -69).addTextReplacement("<highest>", 420 /*AAWorldGen.QUARTZ_MAX*/), new PageTextOnly(2).addItemsToPage(new ItemStack(ActuallyItems.BLACK_QUARTZ.get())), new PageCrafting(3, ActuallyBlocks.BLACK_QUARTZ.get()).setNoText(), new PageCrafting(4, ActuallyBlocks.BLACK_QUARTZ_PILLAR.get()).setNoText(), new PageCrafting(5, ActuallyBlocks.CHISELED_BLACK_QUARTZ.get()).setNoText());
        //        new BookletChapter("cloud", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyBlocks.blockSmileyCloud), new PageTextOnly(1), new PageCrafting(2, BlockCrafting.recipeSmileyCloud)).setSpecial();
        new BookletChapter("coalStuff", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.TINY_COAL.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.TINY_COAL.get()).setNoText(), new PageCrafting(3, ActuallyItems.TINY_CHARCOAL.get()).setNoText()/*, new PageCrafting(4, BlockCrafting.recipeBlockChar).setNoText()*/);
        ArrayList<BookletPage> lampPages = new ArrayList<>();
        lampPages.add(new PageTextOnly(lampPages.size() + 1));
        lampPages.add(new PageTextOnly(lampPages.size() + 1));
//        lampPages.add(new PageCrafting(lampPages.size() + 1, BlockCrafting.recipePowerer).setNoText());

        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_WHITE.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_ORANGE.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_MAGENTA.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_LIGHT_BLUE.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_YELLOW.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_LIME.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_PINK.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_GRAY.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_LIGHT_GRAY.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_CYAN.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_PURPLE.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_BLUE.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_BROWN.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_GREEN.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_RED.get()).setNoText());
        lampPages.add(new PageCrafting(lampPages.size() + 1, ActuallyBlocks.LAMP_BLACK.get()).setNoText());
        new BookletChapter("lamps", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyBlocks.LAMP_GREEN.get()), lampPages.toArray(new BookletPage[lampPages.size()]));
        new BookletChapter("enderStar", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.ENDER_STAR.get()), new PageCrafting(1, ActuallyItems.ENDER_STAR.get()));
//        new BookletChapter("spawnerShard", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.SPAWNER_SHARD.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.SPAWNER_SHARD.get())));
        //        new BookletChapter("treasureChest", ActuallyAdditionsAPI.entryMisc, new ItemStack(InitBlocks.blockTreasureChest.get()), new PagePicture(1, "page_treasure_chest", 150).addItemsToPage(new ItemStack(InitBlocks.blockTreasureChest.get())), new PageTextOnly(2)).setSpecial();
//        new BookletChapter("hairBalls", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.HAIRY_BALL.get()), new PagePicture(1, "page_fur_balls", 110).addItemsToPage(new ItemStack(ActuallyItems.HAIRY_BALL.get())), new PageTextOnly(2)).setSpecial();
        //        new BookletChapter("blackLotus", ActuallyAdditionsAPI.entryMisc, new ItemStack(InitBlocks.blockBlackLotus.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(InitBlocks.blockBlackLotus.get())), new PageCrafting(2, ItemCrafting.recipeBlackDye));
        new BookletChapter("waterBowl", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyItems.WATER_BOWL.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.WATER_BOWL.get())));
        new BookletChapter("tinyTorch", ActuallyAdditionsAPI.entryMisc, new ItemStack(ActuallyBlocks.TINY_TORCH.get()), new PageCrafting(1, ActuallyBlocks.TINY_TORCH.get())).setSpecial();

        //Reconstruction
        chaptersIntroduction[7] = new BookletChapter("reconstructorLenses", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS.get()), new PageTextOnly(1)).setImportant();
        new BookletChapter("additionalRecipes", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(Items.LEATHER), new PageReconstructor(1, Blocks.SOUL_SAND).setNoText(), new PageReconstructor(2, Items.LEATHER).setNoText(), new PageReconstructor(3, Items.NETHER_WART).setNoText(), new PageReconstructor(4, Items.PRISMARINE_SHARD).setNoText()).setSpecial();
//        new BookletChapter("bookSplitting", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(Items.ENCHANTED_BOOK), new PageTextOnly(1), new PageReconstructor(2, new LensConversionRecipe(Ingredient.fromItem(Items.ENCHANTED_BOOK), new ItemStack(Items.ENCHANTED_BOOK), 0, ActuallyAdditionsAPI.lensDefaultConversion)).setNoText());
        new BookletChapter("lensColor", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS_OF_COLOR.get()), new PageTextOnly(1), new PageReconstructor(2, ActuallyItems.LENS_OF_COLOR.get()).setNoText());
        new BookletChapter("lensDeath", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS_OF_CERTAIN_DEATH.get()), new PageTextOnly(1), new PageReconstructor(2, ActuallyItems.LENS_OF_CERTAIN_DEATH.get()).setNoText());
        new BookletChapter("lensMoreDeath", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS_OF_THE_KILLER.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.LENS_OF_THE_KILLER.get()).setNoText());
        new BookletChapter("lensDetonation", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS_OF_DETONATION.get()), new PageTextOnly(1), new PageReconstructor(2, ActuallyItems.LENS_OF_DETONATION.get()).setNoText());
        new BookletChapter("lensDisenchanting", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS_OF_DISENCHANTING.get()), new PageTextOnly(1).addTextReplacement("<energy>", LensDisenchanting.ENERGY_USE), new PageCrafting(2, ActuallyItems.LENS_OF_DISENCHANTING.get()).setNoText()).setSpecial();
        new BookletChapter("lensMining", ActuallyAdditionsAPI.entryReconstruction, new ItemStack(ActuallyItems.LENS_OF_THE_MINER.get()), new PageTextOnly(1).addTextReplacement("<energy>", ConfigIntValues.MINING_LENS_USE.getValue()), new PageCrafting(2, ActuallyItems.LENS_OF_THE_MINER.get()).setNoText()).setImportant();

        //Laser Relays
        chaptersIntroduction[8] = new BookletChapter("laserIntro", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyItems.LASER_WRENCH.get()), new PageTextOnly(1), new PageTextOnly(2).addTextReplacement("<range>", TileEntityLaserRelay.MAX_DISTANCE), new PageCrafting(3, ActuallyItems.LASER_WRENCH.get())).setImportant();
        new BookletChapter("laserRelays", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyBlocks.LASER_RELAY.get()), new PageTextOnly(1), new PageTextOnly(2).addTextReplacement("<cap1>", TileEntityLaserRelayEnergy.CAP).addTextReplacement("<cap2>", TileEntityLaserRelayEnergyAdvanced.CAP).addTextReplacement("<cap3>", TileEntityLaserRelayEnergyExtreme.CAP), new PagePicture(3, "page_laser_relay", 0).setNoText(), new PageCrafting(4, ActuallyBlocks.LASER_RELAY.getItem()).setNoText(), new PageCrafting(5, ActuallyBlocks.LASER_RELAY_ADVANCED.getItem()).setNoText(), new PageCrafting(6, ActuallyBlocks.LASER_RELAY_EXTREME.getItem()).setNoText());
        new BookletChapter("fluidLaser", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyBlocks.LASER_RELAY_FLUIDS.get()), new PageTextOnly(1), new PageReconstructor(2, ActuallyBlocks.LASER_RELAY_FLUIDS.get()).setNoText());
        new BookletChapter("itemRelays", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyBlocks.LASER_RELAY_ITEM.get()), new PageTextOnly(1), new PageReconstructor(2, ActuallyBlocks.LASER_RELAY_ITEM.get()).setNoText()).setSpecial();
        new BookletChapter("itemInterfaces", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyBlocks.ITEM_INTERFACE.get()), new PageTextOnly(1), new PageTextOnly(2), new PageCrafting(3, ActuallyBlocks.ITEM_INTERFACE.get()).setNoText());
        new BookletChapter("itemRelaysAdvanced", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyBlocks.LASER_RELAY_ITEM_ADVANCED.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.LASER_RELAY_ITEM_ADVANCED.get()));
        new BookletChapter("itemInterfacesHopping", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyBlocks.ITEM_INTERFACE_HOPPING.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.ITEM_INTERFACE_HOPPING.get()).setNoText());
        new BookletChapter("laserUpgradeInvisibility", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyItems.LASER_UPGRADE_INVISIBILITY.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.LASER_UPGRADE_INVISIBILITY.get()).setNoText()).setImportant();
        new BookletChapter("laserUpgradeRange", ActuallyAdditionsAPI.entryLaserRelays, new ItemStack(ActuallyItems.LASER_UPGRADE_RANGE.get()), new PageTextOnly(1).addTextReplacement("<def>", TileEntityLaserRelay.MAX_DISTANCE).addTextReplacement("<upgraded>", TileEntityLaserRelay.MAX_DISTANCE_RANGED), new PageCrafting(2, ActuallyItems.LASER_UPGRADE_RANGE.get()).setNoText()).setImportant();

        //No RF Using Blocks
        new BookletChapter("breaker", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.BREAKER.get()), new PageCrafting(1, ActuallyBlocks.BREAKER.get()), new PageCrafting(2, ActuallyBlocks.PLACER.get()), new PageCrafting(3, ActuallyBlocks.FLUID_PLACER.get()), new PageCrafting(4, ActuallyBlocks.FLUID_COLLECTOR.get()));
        new BookletChapter("dropper", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.DROPPER.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.DROPPER.get()).setNoText());
        new BookletChapter("phantomfaces", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.PHANTOM_LIQUIFACE.get()), new PageTextOnly(1).addTextReplacement("<range>", TileEntityPhantomface.RANGE), new PageTextOnly(2), new PageCrafting(3, ActuallyBlocks.PHANTOM_ITEMFACE.get()), new PageCrafting(4, ActuallyBlocks.PHANTOM_LIQUIFACE.get()), new PageCrafting(5, ActuallyBlocks.PHANTOM_ENERGYFACE.get()), /*new PageCrafting(6, ItemCrafting.recipePhantomConnector).setNoText(),*/ new PageCrafting(7, ActuallyBlocks.PHANTOM_BOOSTER.get())).setImportant();
        new BookletChapter("phantomRedstoneface", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.PHANTOM_REDSTONEFACE.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.PHANTOM_REDSTONEFACE.get()).setNoText());
        new BookletChapter("phantomBreaker", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.PHANTOM_BREAKER.get()), new PageTextOnly(1).addTextReplacement("<range>", TileEntityPhantomPlacer.RANGE), new PageCrafting(2, ActuallyBlocks.PHANTOM_PLACER.get()).setNoText(), new PageCrafting(3, ActuallyBlocks.PHANTOM_BREAKER.get()).setNoText());
//        new BookletChapter("esd", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.INPUTTER_ADVANCED.get()), new PageTextOnly(1), new PageCrafting(2, BlockCrafting.recipeESD).setNoText(), new PageCrafting(3, BlockCrafting.recipeAdvancedESD).setNoText()).setSpecial();
        new BookletChapter("xpSolidifier", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.XP_SOLIDIFIER.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.SOLIDIFIED_EXPERIENCE.get())), new PageCrafting(2, ActuallyBlocks.XP_SOLIDIFIER.get()).setNoText()).setImportant();
        new BookletChapter("greenhouseGlass", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.GREENHOUSE_GLASS.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.GREENHOUSE_GLASS.get()));
        new BookletChapter("feeder", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.FEEDER.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.FEEDER.get()).setNoText());
//        new BookletChapter("crate", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.blockGiantChest.get()), new PageCrafting(1, BlockCrafting.recipeCrate), new PageCrafting(2, BlockCrafting.recipeCrateMedium).setNoText(), new PageCrafting(3, BlockCrafting.recipeCrateLarge).setNoText(), new PageCrafting(4, ItemCrafting.recipeCrateKeeper), new PageCrafting(5, ItemCrafting.recipeChestToCrateUpgrade), new PageCrafting(6, ItemCrafting.recipeSmallToMediumCrateUpgrade), new PageCrafting(7, ItemCrafting.recipeMediumToLargeCrateUpgrade));
        new BookletChapter("rangedCollector", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyBlocks.RANGED_COLLECTOR.get()), new PageTextOnly(1).addTextReplacement("<range>", TileEntityRangedCollector.RANGE), new PageCrafting(2, ActuallyBlocks.RANGED_COLLECTOR.get()).setNoText());

        //RF Using Blocks
        new BookletChapter("fireworkBox", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.FIREWORK_BOX.get()), new PageTextOnly(1).addTextReplacement("<rf>", TileEntityFireworkBox.USE_PER_SHOT), new PageCrafting(2, ActuallyBlocks.FIREWORK_BOX.get())).setSpecial();
        new BookletChapter("batteryBox", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.BATTERY_BOX.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.BATTERY_BOX.get()).setNoText()).setSpecial();
        new BookletChapter("farmer", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.FARMER.get()), new PageTextOnly(1), new PagePicture(2, "page_farmer_crops", 95).addItemsToPage(new ItemStack(Items.WHEAT_SEEDS)).addItemsToPage(new ItemStack(ActuallyItems.CANOLA_SEEDS.get())), new PagePicture(3, "page_farmer_cactus", 105).addItemsToPage(new ItemStack(Blocks.CACTUS)), new PagePicture(4, "page_farmer_wart", 95).addItemsToPage(new ItemStack(Items.NETHER_WART)), new PagePicture(5, "page_farmer_reeds", 105).addItemsToPage(new ItemStack(Items.SUGAR_CANE)), new PagePicture(6, "page_farmer_melons", 105).addItemsToPage(new ItemStack(Items.MELON), new ItemStack(Blocks.PUMPKIN), new ItemStack(Blocks.MELON)), new PagePicture(7, "page_farmer_enderlilly", 105), new PagePicture(8, "page_farmer_redorchid", 105), new PageCrafting(4, ActuallyBlocks.FARMER.getItem()).setNoText()).setImportant();
        new BookletChapter("miner", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.VERTICAL_DIGGER.get()), new PageTextOnly(1).addTextReplacement("<rf>", TileEntityVerticalDigger.ENERGY_USE_PER_BLOCK).addTextReplacement("<range>", TileEntityVerticalDigger.DEFAULT_RANGE), new PageCrafting(2, ActuallyBlocks.VERTICAL_DIGGER.get())).setSpecial();
        new BookletChapterCoffee("coffeeMachine", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.COFFEE_MACHINE.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.COFFEE_BEANS.get())).addTextReplacement("<rf>", TileEntityCoffeeMachine.ENERGY_USED).addTextReplacement("<coffee>", TileEntityCoffeeMachine.CACHE_USE).addTextReplacement("<water>", TileEntityCoffeeMachine.WATER_USE), new PageTextOnly(2).addItemsToPage(new ItemStack(ActuallyItems.COFFEE_BEANS.get() /* TODO: COFFEE */)), new PagePicture(3, "page_coffee_machine", 115), new PageCrafting(4, ActuallyBlocks.COFFEE_MACHINE.get()).setNoText(), new PageCrafting(5, ActuallyItems.COFFEE_CUP.get()).setNoText()).setImportant();

        List<IBookletPage> list = new ArrayList<>();
        list.add(new PageTextOnly(1).addTextReplacement("<rf>", TileEntityCrusher.ENERGY_USE));
        list.add(new PageCrafting(2, ActuallyBlocks.CRUSHER.get()).setNoText());
        list.add(new PageCrafting(3, ActuallyBlocks.CRUSHER_DOUBLE.get()).setNoText());
//        if (CrusherCrafting.recipeIronHorseArmor != null) {
//            list.add(new PageCrusherRecipe(4, Items.IRON_HORSE_ARMOR).setNoText());
//        }
//        if (CrusherCrafting.recipeGoldHorseArmor != null) {
//            list.add(new PageCrusherRecipe(5, Items.GOLDEN_HORSE_ARMOR).setNoText());
//        }
//        if (CrusherCrafting.recipeDiamondHorseArmor != null) {
//            list.add(new PageCrusherRecipe(6, Items.DIAMOND_HORSE_ARMOR).setNoText());
//        }

        new BookletChapterCrusher("crusher", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.CRUSHER_DOUBLE.get()), list.toArray(new IBookletPage[0]));
        new BookletChapter("furnaceDouble", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.POWERED_FURNACE.get()), new PageCrafting(1, ActuallyBlocks.POWERED_FURNACE.get()).addTextReplacement("<rf>", TileEntityPoweredFurnace.ENERGY_USE));
        new BookletChapter("lavaFactory", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.LAVA_FACTORY_CONTROLLER.get()), new PageTextOnly(1).addTextReplacement("<rf>", TileEntityLavaFactoryController.ENERGY_USE), new PagePicture(2, "page_lava_factory", 0).setNoText(), new PageCrafting(3, ActuallyBlocks.LAVA_FACTORY_CONTROLLER.get()).setNoText(), new PageCrafting(4, ActuallyBlocks.LAVA_FACTORY_CASING.getItem()).setNoText());
        new BookletChapter("energizer", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.ENERGIZER.get()), new PageCrafting(1, ActuallyBlocks.ENERGIZER.get()), new PageCrafting(2, ActuallyBlocks.ENERVATOR.get()));
        //        new BookletChapter("repairer", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(InitBlocks.blockItemRepairer.get()), new PageCrafting(1, BlockCrafting.recipeRepairer).addTextReplacement("<rf>", TileEntityItemRepairer.ENERGY_USE));
        new BookletChapter("longRangeBreaker", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.LONG_RANGE_BREAKER.get()), new PageTextOnly(1).addTextReplacement("<rf>", TileEntityLongRangeBreaker.ENERGY_USE).addTextReplacement("<range>", TileEntityLongRangeBreaker.RANGE), new PageCrafting(2, ActuallyBlocks.BREAKER.get()));
        new BookletChapter("playerInterface", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.PLAYER_INTERFACE.get()), new PageTextOnly(1).addTextReplacement("<range>", TileEntityPlayerInterface.DEFAULT_RANGE), new PageCrafting(2, ActuallyBlocks.PLAYER_INTERFACE.get()).setNoText()).setSpecial();
        new BookletChapter("displayStand", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.DISPLAY_STAND.get()), new PageTextOnly(1), new PageTextOnly(2), new PageCrafting(3, ActuallyBlocks.DISPLAY_STAND.get()).setNoText()).setSpecial();
        new BookletChapter("shockSuppressor", ActuallyAdditionsAPI.entryFunctionalRF, new ItemStack(ActuallyBlocks.SHOCK_SUPPRESSOR.get()), new PageTextOnly(1).addTextReplacement("<range>", TileEntityShockSuppressor.RANGE).addTextReplacement("<rf>", TileEntityShockSuppressor.USE_PER), new PageCrafting(2, ActuallyBlocks.SHOCK_SUPPRESSOR.get()));

        //RF Generating Blocks
        //        new BookletChapter("solarPanel", ActuallyAdditionsAPI.entryGeneratingRF, new ItemStack(ActuallyBlocks.blockFurnaceSolar.get()), new PageTextOnly(1).addTextReplacement("<rf>", TileEntityFurnaceSolar.PRODUCE), new PageCrafting(2, BlockCrafting.recipeSolar).setNoText());
        new BookletChapter("heatCollector", ActuallyAdditionsAPI.entryGeneratingRF, new ItemStack(ActuallyBlocks.HEAT_COLLECTOR.get()), new PageTextOnly(1).addTextReplacement("<rf>", TileEntityHeatCollector.ENERGY_PRODUCE).addTextReplacement("<min>", TileEntityHeatCollector.BLOCKS_NEEDED), new PageCrafting(2, ActuallyBlocks.HEAT_COLLECTOR.get()).setNoText());
        new BookletChapter("canola", ActuallyAdditionsAPI.entryGeneratingRF, new ItemStack(ActuallyBlocks.FERMENTING_BARREL.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.CANOLA.get())).addItemsToPage(new ItemStack(ActuallyItems.CANOLA_SEEDS.get())).addFluidToPage(InitFluids.CANOLA_OIL.get()), new PageTextOnly(2).addFluidToPage(InitFluids.REFINED_CANOLA_OIL.get()).addFluidToPage(InitFluids.CRYSTALLIZED_OIL.get()).addFluidToPage(InitFluids.EMPOWERED_OIL.get()), new PageCrafting(3, ActuallyBlocks.CANOLA_PRESS.get()).setNoText(), new PageCrafting(4, ActuallyBlocks.FERMENTING_BARREL.get()), new PageCrafting(5, ActuallyBlocks.OIL_GENERATOR.get()), new PageReconstructor(6, ActuallyItems.CRYSTALLIZED_CANOLA_SEED.get()).setNoText(), new PageEmpowerer(7, ActuallyItems.EMPOWERED_CANOLA_SEED.get()).setNoText());
        new BookletChapter("leafGen", ActuallyAdditionsAPI.entryGeneratingRF, new ItemStack(ActuallyBlocks.LEAF_GENERATOR.get()), new PageTextOnly(1).addTextReplacement("<rf>", ConfigIntValues.LEAF_GENERATOR_CF_PER_LEAF.getValue()).addTextReplacement("<range>", ConfigIntValues.LEAF_GENERATOR_AREA.getValue()), new PageCrafting(2, ActuallyBlocks.LEAF_GENERATOR.get())).setImportant();
        new BookletChapter("bioReactor", ActuallyAdditionsAPI.entryGeneratingRF, new ItemStack(ActuallyBlocks.BIOREACTOR.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyBlocks.BIOREACTOR.getItem()).setNoText()).setSpecial();

        //No RF Using Items
        chaptersIntroduction[9] = new BookletChapter("goggles", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.ENGINEERS_GOGGLES.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.ENGINEERS_GOGGLES.get()).setNoText(), new PageCrafting(3, ActuallyItems.ADVANCED_LEAF_BLOWER.get()).setNoText()).setImportant();
//        new BookletChapter("bags", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.BAG.get()), new PageTextOnly(1), new PageCrafting(2, ItemCrafting.recipeBag), new PageCrafting(3, ItemCrafting.recipeVoidBag).setNoText()).setImportant();
        new BookletChapter("wings", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.WINGS_OF_THE_BATS.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.WINGS_OF_THE_BATS.get())).addTextReplacement("<secs>", ItemWingsOfTheBats.MAX_FLY_TIME / 20), new PageCrafting(2, ActuallyItems.WINGS_OF_THE_BATS.get()).setNoText()).setSpecial();
//        new BookletChapter("foods", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.FOOD.get(), 1, TheFoods.HAMBURGER.ordinal()), new PageCrafting(1, FoodCrafting.recipeBacon).setNoText(), new PageFurnace(2, new ItemStack(ActuallyItems.FOOD.get(), 1, TheFoods.RICE_BREAD.ordinal())).setNoText(), new PageCrafting(3, FoodCrafting.recipeHamburger).setNoText(), new PageCrafting(4, FoodCrafting.recipeBigCookie).setNoText(), new PageCrafting(5, FoodCrafting.recipeSubSandwich).setNoText(), new PageCrafting(6, FoodCrafting.recipeFrenchFry).setNoText(), new PageCrafting(7, FoodCrafting.recipeFrenchFries).setNoText(), new PageCrafting(8, FoodCrafting.recipeFishNChips).setNoText(), new PageCrafting(9, FoodCrafting.recipeCheese).setNoText(), new PageCrafting(10, FoodCrafting.recipePumpkinStew).setNoText(), new PageCrafting(11, FoodCrafting.recipeCarrotJuice).setNoText(), new PageCrafting(12, FoodCrafting.recipeSpaghetti).setNoText(), new PageCrafting(13, FoodCrafting.recipeNoodle).setNoText(), new PageCrafting(14, FoodCrafting.recipeChocolate).setNoText(), new PageCrafting(15, FoodCrafting.recipeChocolateCake).setNoText(), new PageCrafting(16, FoodCrafting.recipeToast).setNoText(), new PageFurnace(17, new ItemStack(ActuallyItems.FOOD.get(), 1, TheFoods.BAGUETTE.ordinal())).setNoText(), new PageCrafting(18, FoodCrafting.recipeChocolateToast).setNoText(), new PageCrafting(1, FoodCrafting.recipePizza).setNoText());
        new BookletChapter("leafBlower", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.LEAF_BLOWER.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.LEAF_BLOWER.get()).setNoText(), new PageCrafting(3, ActuallyItems.ADVANCED_LEAF_BLOWER.get()).setNoText()).setImportant();
        new BookletChapter("playerProbe", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.PLAYER_PROBE.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.PLAYER_PROBE.get()).setNoText()).setSpecial();
        ArrayList<BookletPage> aiotPages = new ArrayList<>();
        aiotPages.add(new PageTextOnly(aiotPages.size() + 1));
        aiotPages.add(new PageCrafting(aiotPages.size() + 1, ActuallyItems.WOODEN_AIOT.get()).setNoText());
        aiotPages.add(new PageCrafting(aiotPages.size() + 1, ActuallyItems.STONE_AIOT.get()).setNoText());
        aiotPages.add(new PageCrafting(aiotPages.size() + 1, ActuallyItems.IRON_AIOT.get()).setNoText());
        aiotPages.add(new PageCrafting(aiotPages.size() + 1, ActuallyItems.GOLD_AIOT.get()).setNoText());
        aiotPages.add(new PageCrafting(aiotPages.size() + 1, ActuallyItems.DIAMOND_AIOT.get()).setNoText());
        aiotPages.add(new PageCrafting(aiotPages.size() + 1, ActuallyItems.NETHERITE_AIOT.get()).setNoText());

        new BookletChapter("aiots", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.DIAMOND_AIOT.get()), aiotPages.toArray(new BookletPage[aiotPages.size()])).setImportant();

//        new BookletChapter("jams", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.JAM.get()), new PageTextOnly(1).addItemsToPage(new ItemStack(ActuallyItems.JAM.get(), 1, Util.WILDCARD)), new PagePicture(2, "page_jam_house", 150), new PageTextOnly(3));

//        ArrayList<BookletPage> potionRingPages = new ArrayList<>();
//        potionRingPages.add(new PageTextOnly(potionRingPages.size() + 1));
//        for (IRecipe recipe : ItemCrafting.RECIPES_POTION_RINGS) {
//            potionRingPages.add(new PageCrafting(potionRingPages.size() + 1, recipe).setNoText());
//        }
//        new BookletChapter("potionRings", ActuallyAdditionsAPI.entryItemsNonRF, new ItemStack(ActuallyItems.POTION_RING.get()), potionRingPages.toArray(new BookletPage[potionRingPages.size()]));
        new BookletChapter("itemFilter", ActuallyAdditionsAPI.entryFunctionalNonRF, new ItemStack(ActuallyItems.FILTER.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.FILTER.get()).setNoText()).setImportant();

        //RF Using Items
        new BookletChapter("drill", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.DRILL_MAIN.get()), new PageTextOnly(1), new PageTextOnly(2), new PageCrafting(3, ActuallyItems.DRILL_MAIN.get()).setNoText(), new PageCrafting(4, ActuallyItems.DRILL_BLACK.get()), new PageCrafting(4, ActuallyItems.DRILL_CORE.get()).setNoText(), new PageCrafting(5, ActuallyItems.DRILL_UPGRADE_SPEED.get()).setNoText(), new PageCrafting(6, ActuallyItems.DRILL_UPGRADE_SPEED_II.get()).setNoText(), new PageCrafting(7, ActuallyItems.DRILL_UPGRADE_SPEED_III.get()).setNoText(), new PageCrafting(8, ActuallyItems.DRILL_UPGRADE_FORTUNE.get()).setNoText(), new PageCrafting(9, ActuallyItems.DRILL_UPGRADE_FORTUNE_II.get()).setNoText(), new PageCrafting(10, ActuallyItems.DRILL_UPGRADE_SILK_TOUCH.get()).setNoText(), new PageCrafting(11, ActuallyItems.DRILL_UPGRADE_THREE_BY_THREE.get()).setNoText(), new PageCrafting(12, ActuallyItems.DRILL_UPGRADE_FIVE_BY_FIVE.get()).setNoText(), new PageCrafting(13, ActuallyItems.DRILL_UPGRADE_BLOCK_PLACING.get()).setNoText()).setSpecial();
//        new BookletChapter("fillingWand", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.FILLING_WAND.get()), new PageTextOnly(1), new PageCrafting(2, ItemCrafting.recipeFillingWand).setNoText()).setSpecial();
        new BookletChapter("staff", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.TELEPORT_STAFF.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.TELEPORT_STAFF.get()).setNoText()).setImportant();
        new BookletChapter("magnetRing", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.RING_OF_MAGNETIZING.get()), new PageCrafting(1, ActuallyItems.RING_OF_MAGNETIZING.get()));
        new BookletChapter("growthRing", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.RING_OF_GROWTH.get()), new PageCrafting(1, ActuallyItems.RING_OF_GROWTH.get()));
//        new BookletChapter("waterRemovalRing", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.WATER_REMOVAL_RING.get()), new PageCrafting(1, ItemCrafting.recipeWaterRing));
        new BookletChapter("batteries", ActuallyAdditionsAPI.entryItemsRF, new ItemStack(ActuallyItems.TRIPLE_BATTERY.get()), new PageTextOnly(1), new PageCrafting(2, ActuallyItems.SINGLE_BATTERY.get()).setNoText(), new PageCrafting(3, ActuallyItems.DOUBLE_BATTERY.get()).setNoText(), new PageCrafting(4, ActuallyItems.TRIPLE_BATTERY.get()).setNoText(), new PageCrafting(5, ActuallyItems.QUADRUPLE_BATTERY.get()).setNoText(), new PageCrafting(6, ActuallyItems.QUINTUPLE_BATTERY.get()).setNoText());

        //Updates and infos
        new BookletChapter("changelog", ActuallyAdditionsAPI.entryUpdatesAndInfos, new ItemStack(Items.CLOCK), new PageLinkButton(1, UpdateChecker.CHANGELOG_LINK));
        new BookletChapter("curse", ActuallyAdditionsAPI.entryUpdatesAndInfos, new ItemStack(Items.FLINT_AND_STEEL), new PageLinkButton(1, "http://ellpeck.de/actadd"));
        new BookletChapter("patreon", ActuallyAdditionsAPI.entryUpdatesAndInfos, new ItemStack(ActuallyItems.EMERADIC_CRYSTAL.get(), 1), new PageLinkButton(1, "http://patreon.com/Ellpeck"), new PagePicture(2, "page_patreon", 153)).setImportant();
        new BookletChapter("website", ActuallyAdditionsAPI.entryUpdatesAndInfos, new ItemStack(ActuallyItems.ITEM_BOOKLET.get()), new PageLinkButton(1, "http://ellpeck.de"));

        //Trials
        chaptersIntroduction[10] = new BookletChapter("trialsIntro", ActuallyAdditionsAPI.entryTrials, new ItemStack(Items.GOLD_INGOT), new PageTextOnly(1), new PageTextOnly(2)).setSpecial();
        new BookletChapterTrials("crystalProduction", new ItemStack(ActuallyItems.EMERADIC_CRYSTAL.get()), false);
        new BookletChapterTrials("leatherProduction", new ItemStack(Items.LEATHER), false);
        new BookletChapterTrials("crystalOil", FluidUtil.getFilledBucket(new FluidStack(InitFluids.CRYSTALLIZED_OIL.get(), FluidAttributes.BUCKET_VOLUME)), false);
        new BookletChapterTrials("autoDisenchanter", new ItemStack(ActuallyItems.LENS_OF_DISENCHANTING.get()), false);
        new BookletChapterTrials("empoweredOil", FluidUtil.getFilledBucket(new FluidStack(InitFluids.EMPOWERED_OIL.get(), FluidAttributes.BUCKET_VOLUME)), false);
        new BookletChapterTrials("mobFarm", new ItemStack(Items.ROTTEN_FLESH), false);
        new BookletChapterTrials("empowererAutomation", new ItemStack(ActuallyBlocks.EMPOWERER.get()), false);


    }
}
