/*
 * This file ("InitItems.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.items;

import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.blocks.ActuallyBlocks;
import de.ellpeck.actuallyadditions.mod.items.base.*;
import de.ellpeck.actuallyadditions.mod.items.lens.ItemLens;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheFoods;
import de.ellpeck.actuallyadditions.mod.material.ArmorMaterials;
import de.ellpeck.actuallyadditions.mod.material.ToolMaterials;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ActuallyItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ActuallyAdditions.MODID);

    // REMOVE ME
    @Deprecated
    public static final RegistryObject<Item> itemMisc = ITEMS.register("item_misc", ItemBase::new);

    // MISC ITEMS
    public static final RegistryObject<Item> itemCanola = ITEMS.register("canola", ItemBase::new);
    public static final RegistryObject<Item> itemCoffeeCup = ITEMS.register("coffee_cup", ItemBase::new);
    public static final RegistryObject<Item> itemPaperCone = ITEMS.register("paper_cone", ItemBase::new);
    public static final RegistryObject<Item> itemMashedFood = ITEMS.register("mashed_food", ItemBase::new);
    public static final RegistryObject<Item> itemKnifeBlade = ITEMS.register("knife_blade", ItemBase::new);
    public static final RegistryObject<Item> itemKnifeHandle = ITEMS.register("knife_handle", ItemBase::new);
    public static final RegistryObject<Item> itemDough = ITEMS.register("dough", ItemBase::new);
    public static final RegistryObject<Item> itemBlackQuartz = ITEMS.register("black_quartz", ItemBase::new);
    public static final RegistryObject<Item> itemRing = ITEMS.register("ring", ItemBase::new);
    public static final RegistryObject<Item> itemCoil = ITEMS.register("coil", ItemBase::new);
    public static final RegistryObject<Item> itemCoilAdvanced = ITEMS.register("coil_advanced", ItemBase::new);
    public static final RegistryObject<Item> itemRiceDough = ITEMS.register("rice_dough", ItemBase::new);
    public static final RegistryObject<Item> itemTinyCoal = ITEMS.register("tiny_coal", ItemBase::new);
    public static final RegistryObject<Item> itemTinyChar = ITEMS.register("tiny_charcoal", ItemBase::new);
    public static final RegistryObject<Item> itemRiceSlime = ITEMS.register("rice_slime", ItemBase::new);
    public static final RegistryObject<Item> itemCup = ITEMS.register("cup", ItemBase::new);
    public static final RegistryObject<Item> itemBatWing = ITEMS.register("bat_wing", ItemBase::new);
    public static final RegistryObject<Item> itemDrillCore = ITEMS.register("drill_core", ItemBase::new);
    public static final RegistryObject<Item> itemBlackDye = ITEMS.register("black_dye", ItemBase::new);
    public static final RegistryObject<Item> itemLens = ITEMS.register("lens", ItemBase::new);
    public static final RegistryObject<Item> itemEnderStar = ITEMS.register("ender_star", ItemBase::new);
    public static final RegistryObject<Item> itemSpawnerShard = ITEMS.register("spawner_shard", ItemBase::new);
    public static final RegistryObject<Item> itemBiomass = ITEMS.register("biomass", ItemBase::new);
    public static final RegistryObject<Item> itemBiocoal = ITEMS.register("biocoal", ItemBase::new);
    public static final RegistryObject<Item> itemCrystallizedCanolaSeed = ITEMS.register("crystallized_canola_seed", ItemBase::new);
    public static final RegistryObject<Item> itemEmpoweredCanolaSeed = ITEMS.register("empowered_canola_seed", ItemBase::new);

    // SHARDS
    public static final RegistryObject<Item> RESTONIA_CRYSTAL_SHARD = ITEMS.register("item_restonia_crystal_shard", ItemBase::new);
    public static final RegistryObject<Item> PALIS_CRYSTAL_SHARD = ITEMS.register("item_palis_crystal_shard", ItemBase::new);
    public static final RegistryObject<Item> DIAMATINE_CRYSTAL_SHARD = ITEMS.register("item_diamatine_crystal_shard", ItemBase::new);
    public static final RegistryObject<Item> VOID_CRYSTAL_SHARD = ITEMS.register("item_void_crystal_shard", ItemBase::new);
    public static final RegistryObject<Item> EMERADIC_CRYSTAL_SHARD = ITEMS.register("item_emeradic_crystal_shard", ItemBase::new);
    public static final RegistryObject<Item> ENORI_CRYSTAL_SHARD = ITEMS.register("item_enori_crystal_shard", ItemBase::new);

    // CRYSTALS
    public static final RegistryObject<Item> RESTONIA_CRYSTAL = ITEMS.register("restonia_crystal", ItemCrystal::new);
    public static final RegistryObject<Item> PALIS_CRYSTAL = ITEMS.register("palis_crystal", ItemCrystal::new);
    public static final RegistryObject<Item> DIAMATINE_CRYSTAL = ITEMS.register("diamatine_crystal", ItemCrystal::new);
    public static final RegistryObject<Item> VOID_CRYSTAL = ITEMS.register("void_crystal", ItemCrystal::new);
    public static final RegistryObject<Item> EMERADIC_CRYSTAL = ITEMS.register("emeradic_crystal", ItemCrystal::new);
    public static final RegistryObject<Item> ENORI_CRYSTAL = ITEMS.register("enori_crystal", ItemCrystal::new);

    public static final RegistryObject<Item> RESTONIA_EMPOWERED_CRYSTAL = ITEMS.register("restonia_empowered_crystal", () -> new ItemCrystal(true));
    public static final RegistryObject<Item> PALIS_EMPOWERED_CRYSTAL = ITEMS.register("palis_empowered_crystal", () -> new ItemCrystal(true));
    public static final RegistryObject<Item> DIAMATINE_EMPOWERED_CRYSTAL = ITEMS.register("diamatine_empowered_crystal", () -> new ItemCrystal(true));
    public static final RegistryObject<Item> VOID_EMPOWERED_CRYSTAL = ITEMS.register("void_empowered_crystal", () -> new ItemCrystal(true));
    public static final RegistryObject<Item> EMERADIC_EMPOWERED_CRYSTAL = ITEMS.register("emeradic_empowered_crystal", () -> new ItemCrystal(true));
    public static final RegistryObject<Item> ENORI_EMPOWERED_CRYSTAL = ITEMS.register("enori_empowered_crystal", () -> new ItemCrystal(true));

    // BLACK QUARTZ
    public static final RegistryObject<Item> BLACK_QUARTZ = ITEMS.register("black_quartz", ItemBase::new);

    public static final RegistryObject<Item> itemEngineerGogglesAdvanced = ITEMS.register("item_engineer_goggles_advanced", () -> new ItemEngineerGoggles(true));
    public static final RegistryObject<Item> itemEngineerGoggles = ITEMS.register("item_engineer_goggles", () -> new ItemEngineerGoggles(false));
    public static final RegistryObject<Item> itemLaserUpgradeRange = ITEMS.register("item_laser_upgrade_range", ItemBase::new);
    public static final RegistryObject<Item> itemLaserUpgradeInvisibility = ITEMS.register("item_laser_upgrade_invisibility", ItemBase::new);
    public static final RegistryObject<Item> itemFillingWand = ITEMS.register("item_filling_wand", ItemFillingWand::new);
    public static final RegistryObject<Item> itemBag = ITEMS.register("item_bag", () -> new ItemBag(false));
    public static final RegistryObject<Item> itemVoidBag = ITEMS.register("item_void_bag", () -> new ItemBag(true));
    public static final RegistryObject<Item> itemWorm = ITEMS.register("item_worm", ItemWorm::new);
    public static final RegistryObject<Item> itemPlayerProbe = ITEMS.register("item_player_probe", ItemPlayerProbe::new);
    public static final RegistryObject<Item> itemFilter = ITEMS.register("item_filter", ItemFilter::new);
    public static final RegistryObject<Item> itemWaterBowl = ITEMS.register("item_water_bowl", ItemWaterBowl::new);
    public static final RegistryObject<Item> itemSpawnerChanger = ITEMS.register("item_spawner_changer", ItemSpawnerChanger::new);
    public static final RegistryObject<Item> itemCrateKeeper = ITEMS.register("item_crate_keeper", () -> new ItemGeneric(defaultProps().maxStackSize(1)));
    public static final RegistryObject<Item> itemColorLens = ITEMS.register("item_color_lens", () -> new ItemLens(ActuallyAdditionsAPI.lensColor));
    public static final RegistryObject<Item> itemExplosionLens = ITEMS.register("item_explosion_lens", () -> new ItemLens(ActuallyAdditionsAPI.lensDetonation));
    public static final RegistryObject<Item> itemDamageLens = ITEMS.register("item_damage_lens", () -> new ItemLens(ActuallyAdditionsAPI.lensDeath));
    public static final RegistryObject<Item> itemMoreDamageLens = ITEMS.register("item_more_damage_lens", () -> new ItemLens(ActuallyAdditionsAPI.lensEvenMoarDeath));
    public static final RegistryObject<Item> itemDisenchantingLens = ITEMS.register("item_disenchanting_lens", () -> new ItemLens(ActuallyAdditionsAPI.lensDisenchanting));
    public static final RegistryObject<Item> itemMiningLens = ITEMS.register("item_mining_lens", () -> new ItemLens(ActuallyAdditionsAPI.lensMining));
    public static final RegistryObject<Item> itemLaserWrench = ITEMS.register("item_laser_wrench", ItemLaserWrench::new);
    //    public static final RegistryObject<Item> itemChestToCrateUpgrade = ITEMS.register("", new ItemChestToCrateUpgrade("item_chest_to_crate_upgrade", TileEntityChest.class, InitBlocks.blockGiantChest.getDefaultState()));
    //    public static final RegistryObject<Item> itemSmallToMediumCrateUpgrade = ITEMS.register("", new ItemChestToCrateUpgrade("item_small_to_medium_crate_upgrade", TileEntityGiantChest.class, InitBlocks.blockGiantChestMedium.getDefaultState()));
    //    public static final RegistryObject<Item> itemMediumToLargeCrateUpgrade = ITEMS.register("", new ItemChestToCrateUpgrade("item_medium_to_large_crate_upgrade", TileEntityGiantChestMedium.class, InitBlocks.blockGiantChestLarge.getDefaultState()));
    public static final RegistryObject<Item> itemBooklet = ITEMS.register("item_booklet", ItemBooklet::new);
    public static final RegistryObject<Item> itemGrowthRing = ITEMS.register("item_growth_ring", ItemGrowthRing::new);
    public static final RegistryObject<Item> itemMagnetRing = ITEMS.register("item_suction_ring", ItemMagnetRing::new);
    public static final RegistryObject<Item> itemWaterRemovalRing = ITEMS.register("item_water_removal_ring", ItemWaterRemovalRing::new);
    public static final RegistryObject<Item> itemTeleStaff = ITEMS.register("item_tele_staff", ItemTeleStaff::new);
    public static final RegistryObject<Item> itemWingsOfTheBats = ITEMS.register("item_wings_of_the_bats", ItemWingsOfTheBats::new);
    public static final RegistryObject<Item> itemDrill = ITEMS.register("item_drill", ItemDrill::new);
    public static final RegistryObject<Item> itemBattery = ITEMS.register("item_battery", () -> new ItemBattery(200000, 1000));
    public static final RegistryObject<Item> itemBatteryDouble = ITEMS.register("item_battery_double", () -> new ItemBattery(350000, 5000));
    public static final RegistryObject<Item> itemBatteryTriple = ITEMS.register("item_battery_triple", () -> new ItemBattery(600000, 10000));
    public static final RegistryObject<Item> itemBatteryQuadruple = ITEMS.register("item_battery_quadruple", () -> new ItemBattery(1000000, 30000));
    public static final RegistryObject<Item> itemBatteryQuintuple = ITEMS.register("item_battery_quintuple", () -> new ItemBattery(2000000, 100000));
    public static final RegistryObject<Item> itemDrillUpgradeSpeed = ITEMS.register("item_drill_upgrade_speed", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.SPEED));
    public static final RegistryObject<Item> itemDrillUpgradeSpeedII = ITEMS.register("item_drill_upgrade_speed_ii", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.SPEED_II));
    public static final RegistryObject<Item> itemDrillUpgradeSpeedIII = ITEMS.register("item_drill_upgrade_speed_iii", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.SPEED_III));
    public static final RegistryObject<Item> itemDrillUpgradeSilkTouch = ITEMS.register("item_drill_upgrade_silk_touch", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.SILK_TOUCH));
    public static final RegistryObject<Item> itemDrillUpgradeFortune = ITEMS.register("item_drill_upgrade_fortune", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.FORTUNE));
    public static final RegistryObject<Item> itemDrillUpgradeFortuneII = ITEMS.register("item_drill_upgrade_fortune_ii", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.FORTUNE_II));
    public static final RegistryObject<Item> itemDrillUpgradeThreeByThree = ITEMS.register("item_drill_upgrade_three_by_three", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.THREE_BY_THREE));
    public static final RegistryObject<Item> itemDrillUpgradeFiveByFive = ITEMS.register("item_drill_upgrade_five_by_five", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.FIVE_BY_FIVE));
    public static final RegistryObject<Item> itemDrillUpgradeBlockPlacing = ITEMS.register("item_drill_upgrade_block_placing", () -> new ItemDrillUpgrade(ItemDrillUpgrade.UpgradeType.PLACER));
    public static final RegistryObject<Item> itemFertilizer = ITEMS.register("item_fertilizer", ItemFertilizer::new);
    public static final RegistryObject<Item> itemCoffee = ITEMS.register("item_coffee", ItemCoffee::new);
    public static final RegistryObject<Item> itemPhantomConnector = ITEMS.register("item_phantom_connector", ItemPhantomConnector::new);
    public static final RegistryObject<Item> itemResonantRice = ITEMS.register("item_resonant_rice", ItemResonantRice::new);
    public static final RegistryObject<Item> itemFoods = ITEMS.register("item_food", ItemFoods::new);
    public static final RegistryObject<Item> itemJams = ITEMS.register("item_jam", ItemJams::new);
    public static final RegistryObject<Item> itemKnife = ITEMS.register("item_knife", ItemKnife::new);
    public static final RegistryObject<Item> itemCrafterOnAStick = ITEMS.register("item_crafter_on_a_stick", ItemCrafterOnAStick::new);
    public static final RegistryObject<Item> itemDust = ITEMS.register("item_dust", ItemDust::new);
    public static final RegistryObject<Item> itemSolidifiedExperience = ITEMS.register("item_solidified_experience", ItemSolidifiedExperience::new);
    public static final RegistryObject<Item> itemLeafBlower = ITEMS.register("item_leaf_blower", () -> new ItemLeafBlower(false));
    public static final RegistryObject<Item> itemLeafBlowerAdvanced = ITEMS.register("item_leaf_blower_advanced", () -> new ItemLeafBlower(true));

    // TODO [port] unflatten
    public static final RegistryObject<Item> itemPotionRing = ITEMS.register("item_potion_ring", () -> new ItemPotionRing(false));
    public static final RegistryObject<Item> itemPotionRingAdvanced = ITEMS.register("item_potion_ring_advanced", () -> new ItemPotionRing(true));

    public static final RegistryObject<Item> itemHairyBall = ITEMS.register("item_hairy_ball", ItemHairyBall::new);
    public static final RegistryObject<Item> itemCoffeeBean = ITEMS.register("item_coffee_beans", ItemCoffeeBean::new);

    public static final RegistryObject<Item> itemRiceSeed = ITEMS.register("item_rice_seed", () -> new ItemSeed("seedRice", ActuallyBlocks.RICE.get(), itemFoods.get(), TheFoods.RICE.ordinal()));
    public static final RegistryObject<Item> itemCanolaSeed = ITEMS.register("item_canola_seed", () -> new ItemFoodSeed("seedCanola", ActuallyBlocks.CANOLA, itemMisc, 0, 1, 0.01F, 10).setPotionEffect(new PotionEffect(MobEffects.NAUSEA, 1000, 0), 0.2F));
    public static final RegistryObject<Item> itemFlaxSeed = ITEMS.register("item_flax_seed", () -> new ItemSeed("seedFlax", ActuallyBlocks.FLAX, Items.STRING, 0));
    public static final RegistryObject<Item> itemCoffeeSeed = ITEMS.register("item_coffee_seed", () -> new ItemSeed("seedCoffeeBeans", ActuallyBlocks.COFFEE, itemCoffeeBean, 0));

    // TOOLS & ARMOR
    public static final RegistryObject<Item> itemHelmQuartz = ITEMS.register("item_helm_quartz", () -> new ItemArmorAA(ArmorMaterials.QUARTZ, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestQuartz = ITEMS.register("item_chest_quartz", () -> new ItemArmorAA(ArmorMaterials.QUARTZ, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsQuartz = ITEMS.register("item_pants_quartz", () -> new ItemArmorAA(ArmorMaterials.QUARTZ, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsQuartz = ITEMS.register("item_boots_quartz", () -> new ItemArmorAA(ArmorMaterials.QUARTZ, EquipmentSlotType.FEET));

    public static final RegistryObject<Item> itemPickaxeQuartz = ITEMS.register("item_pickaxe_quartz", () -> new ItemPickaxeAA(ToolMaterials.QUARTZ));
    public static final RegistryObject<Item> itemAxeQuartz = ITEMS.register("item_axe_quartz", () -> new ItemAxeAA(ToolMaterials.QUARTZ));
    public static final RegistryObject<Item> itemShovelQuartz = ITEMS.register("item_shovel_quartz", () -> new ItemShovelAA(ToolMaterials.QUARTZ));
    public static final RegistryObject<Item> itemSwordQuartz = ITEMS.register("item_sword_quartz", () -> new ItemSwordAA(ToolMaterials.QUARTZ));
    public static final RegistryObject<Item> itemHoeQuartz = ITEMS.register("item_hoe_quartz", () -> new ItemHoeAA(ToolMaterials.QUARTZ));
    public static final RegistryObject<Item> woodenPaxel = ITEMS.register("wooden_paxel", () -> new ItemAllToolAA(ItemTier.WOOD));
    public static final RegistryObject<Item> stonePaxel = ITEMS.register("stone_paxel", () -> new ItemAllToolAA(ItemTier.STONE));
    public static final RegistryObject<Item> ironPaxel = ITEMS.register("iron_paxel", () -> new ItemAllToolAA(ItemTier.IRON));
    public static final RegistryObject<Item> goldPaxel = ITEMS.register("gold_paxel", () -> new ItemAllToolAA(ItemTier.GOLD));
    public static final RegistryObject<Item> diamondPaxel = ITEMS.register("diamond_paxel", () -> new ItemAllToolAA(ItemTier.DIAMOND));
    public static final RegistryObject<Item> quartzPaxel = ITEMS.register("quartz_paxel", () -> new ItemAllToolAA(ToolMaterials.QUARTZ));

    public static final RegistryObject<Item> itemPickaxeCrystalRed = ITEMS.register("item_pickaxe_crystal_restonia", () -> new ItemPickaxeAA(ToolMaterials.RESTONIA));
    public static final RegistryObject<Item> itemAxeCrystalRed = ITEMS.register("item_axe_crystal_restonia", () -> new ItemAxeAA(ToolMaterials.RESTONIA));
    public static final RegistryObject<Item> itemShovelCrystalRed = ITEMS.register("item_shovel_crystal_restonia", () -> new ItemShovelAA(ToolMaterials.RESTONIA));
    public static final RegistryObject<Item> itemSwordCrystalRed = ITEMS.register("item_sword_crystal_restonia", () -> new ItemSwordAA(ToolMaterials.RESTONIA));
    public static final RegistryObject<Item> itemHoeCrystalRed = ITEMS.register("item_hoe_crystal_restonia", () -> new ItemHoeAA(ToolMaterials.RESTONIA));
    public static final RegistryObject<Item> itemHelmCrystalRed = ITEMS.register("item_helm_crystal_red", () -> new ItemArmorAA(ArmorMaterials.RESTONIA, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestCrystalRed = ITEMS.register("item_chest_crystal_red", () -> new ItemArmorAA(ArmorMaterials.RESTONIA, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsCrystalRed = ITEMS.register("item_pants_crystal_red", () -> new ItemArmorAA(ArmorMaterials.RESTONIA, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsCrystalRed = ITEMS.register("item_boots_crystal_red", () -> new ItemArmorAA(ArmorMaterials.RESTONIA, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> itemPaxelCrystalRed = ITEMS.register("item_paxel_crystal_red", () -> new ItemAllToolAA(ToolMaterials.RESTONIA));

    public static final RegistryObject<Item> itemPickaxeCrystalBlue = ITEMS.register("item_pickaxe_crystal_blue", () -> new ItemPickaxeAA(ToolMaterials.PALIS));
    public static final RegistryObject<Item> itemAxeCrystalBlue = ITEMS.register("item_axe_crystal_blue", () -> new ItemAxeAA(ToolMaterials.PALIS));
    public static final RegistryObject<Item> itemShovelCrystalBlue = ITEMS.register("item_shovel_crystal_blue", () -> new ItemShovelAA(ToolMaterials.PALIS));
    public static final RegistryObject<Item> itemSwordCrystalBlue = ITEMS.register("item_sword_crystal_blue", () -> new ItemSwordAA(ToolMaterials.PALIS));
    public static final RegistryObject<Item> itemHoeCrystalBlue = ITEMS.register("item_hoe_crystal_blue", () -> new ItemHoeAA(ToolMaterials.PALIS));
    public static final RegistryObject<Item> itemHelmCrystalBlue = ITEMS.register("item_helm_crystal_blue", () -> new ItemArmorAA(ArmorMaterials.PALIS, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestCrystalBlue = ITEMS.register("item_chest_crystal_blue", () -> new ItemArmorAA(ArmorMaterials.PALIS, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsCrystalBlue = ITEMS.register("item_pants_crystal_blue", () -> new ItemArmorAA(ArmorMaterials.PALIS, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsCrystalBlue = ITEMS.register("item_boots_crystal_blue", () -> new ItemArmorAA(ArmorMaterials.PALIS, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> itemPaxelCrystalBlue = ITEMS.register("item_paxel_crystal_blue", () -> new ItemAllToolAA(ToolMaterials.PALIS));

    public static final RegistryObject<Item> itemPickaxeCrystalLightBlue = ITEMS.register("item_pickaxe_crystal_light_blue", () -> new ItemPickaxeAA(ToolMaterials.DIAMATINE));
    public static final RegistryObject<Item> itemAxeCrystalLightBlue = ITEMS.register("item_axe_crystal_light_blue", () -> new ItemAxeAA(ToolMaterials.DIAMATINE));
    public static final RegistryObject<Item> itemShovelCrystalLightBlue = ITEMS.register("item_shovel_crystal_light_blue", () -> new ItemShovelAA(ToolMaterials.DIAMATINE));
    public static final RegistryObject<Item> itemSwordCrystalLightBlue = ITEMS.register("item_sword_crystal_light_blue", () -> new ItemSwordAA(ToolMaterials.DIAMATINE));
    public static final RegistryObject<Item> itemHoeCrystalLightBlue = ITEMS.register("item_hoe_crystal_light_blue", () -> new ItemHoeAA(ToolMaterials.DIAMATINE));
    public static final RegistryObject<Item> itemHelmCrystalLightBlue = ITEMS.register("item_helm_crystal_light_blue", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestCrystalLightBlue = ITEMS.register("item_chest_crystal_light_blue", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsCrystalLightBlue = ITEMS.register("item_pants_crystal_light_blue", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsCrystalLightBlue = ITEMS.register("item_boots_crystal_light_blue", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> itemPaxelCrystalLightBlue = ITEMS.register("item_paxel_crystal_light_blue", () -> new ItemAllToolAA(ToolMaterials.DIAMATINE));

    public static final RegistryObject<Item> itemPickaxeCrystalBlack = ITEMS.register("item_pickaxe_crystal_black", () -> new ItemPickaxeAA(ToolMaterials.VOID));
    public static final RegistryObject<Item> itemAxeCrystalBlack = ITEMS.register("item_axe_crystal_black", () -> new ItemAxeAA(ToolMaterials.VOID));
    public static final RegistryObject<Item> itemShovelCrystalBlack = ITEMS.register("item_shovel_crystal_black", () -> new ItemShovelAA(ToolMaterials.VOID));
    public static final RegistryObject<Item> itemSwordCrystalBlack = ITEMS.register("item_sword_crystal_black", () -> new ItemSwordAA(ToolMaterials.VOID));
    public static final RegistryObject<Item> itemHoeCrystalBlack = ITEMS.register("item_hoe_crystal_black", () -> new ItemHoeAA(ToolMaterials.VOID));
    public static final RegistryObject<Item> itemHelmCrystalBlack = ITEMS.register("item_helm_crystal_black", () -> new ItemArmorAA(ArmorMaterials.VOID, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestCrystalBlack = ITEMS.register("item_chest_crystal_black", () -> new ItemArmorAA(ArmorMaterials.VOID, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsCrystalBlack = ITEMS.register("item_pants_crystal_black", () -> new ItemArmorAA(ArmorMaterials.VOID, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsCrystalBlack = ITEMS.register("item_boots_crystal_black", () -> new ItemArmorAA(ArmorMaterials.VOID, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> itemPaxelCrystalBlack = ITEMS.register("item_paxel_crystal_black", () -> new ItemAllToolAA(ToolMaterials.VOID));

    public static final RegistryObject<Item> itemPickaxeCrystalGreen = ITEMS.register("item_pickaxe_crystal_green", () -> new ItemPickaxeAA(ToolMaterials.EMERADIC));
    public static final RegistryObject<Item> itemAxeCrystalGreen = ITEMS.register("item_axe_crystal_green", () -> new ItemAxeAA(ToolMaterials.EMERADIC));
    public static final RegistryObject<Item> itemShovelCrystalGreen = ITEMS.register("item_shovel_crystal_green", () -> new ItemShovelAA(ToolMaterials.EMERADIC));
    public static final RegistryObject<Item> itemSwordCrystalGreen = ITEMS.register("item_sword_crystal_green", () -> new ItemSwordAA(ToolMaterials.EMERADIC));
    public static final RegistryObject<Item> itemHoeCrystalGreen = ITEMS.register("item_hoe_crystal_green", () -> new ItemHoeAA(ToolMaterials.EMERADIC));
    public static final RegistryObject<Item> itemHelmCrystalGreen = ITEMS.register("item_helm_crystal_green", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestCrystalGreen = ITEMS.register("item_chest_crystal_green", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsCrystalGreen = ITEMS.register("item_pants_crystal_green", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsCrystalGreen = ITEMS.register("item_boots_crystal_green", () -> new ItemArmorAA(ArmorMaterials.DIAMATINE, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> itemPaxelCrystalGreen = ITEMS.register("item_paxel_crystal_green", () -> new ItemAllToolAA(ToolMaterials.EMERADIC));

    public static final RegistryObject<Item> itemPickaxeCrystalWhite = ITEMS.register("item_pickaxe_crystal_white", () -> new ItemPickaxeAA(ToolMaterials.ENORI));
    public static final RegistryObject<Item> itemAxeCrystalWhite = ITEMS.register("item_axe_crystal_white", () -> new ItemAxeAA(ToolMaterials.ENORI));
    public static final RegistryObject<Item> itemShovelCrystalWhite = ITEMS.register("item_shovel_crystal_white", () -> new ItemShovelAA(ToolMaterials.ENORI));
    public static final RegistryObject<Item> itemSwordCrystalWhite = ITEMS.register("item_sword_crystal_white", () -> new ItemSwordAA(ToolMaterials.ENORI));
    public static final RegistryObject<Item> itemHoeCrystalWhite = ITEMS.register("item_hoe_crystal_white", () -> new ItemHoeAA(ToolMaterials.ENORI));
    public static final RegistryObject<Item> itemHelmCrystalWhite = ITEMS.register("item_helm_crystal_white", () -> new ItemArmorAA(ArmorMaterials.ENORI, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> itemChestCrystalWhite = ITEMS.register("item_chest_crystal_white", () -> new ItemArmorAA(ArmorMaterials.ENORI, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> itemPantsCrystalWhite = ITEMS.register("item_pants_crystal_white", () -> new ItemArmorAA(ArmorMaterials.ENORI, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> itemBootsCrystalWhite = ITEMS.register("item_boots_crystal_white", () -> new ItemArmorAA(ArmorMaterials.ENORI, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> itemPaxelCrystalWhite = ITEMS.register("item_paxel_crystal_white", () -> new ItemAllToolAA(ToolMaterials.ENORI));

    public static Item.Properties defaultProps() {
        return new Item.Properties().group(ActuallyAdditions.GROUP);
    }
}