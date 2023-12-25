package oneeyemaker.ztones.world;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.items.ModItems;

public class ChestLootGenerator {

    public static void populateChests() {
        if (!ModConfiguration.isChestLootGenerationEnabled) {
            return;
        }
        if (ModConfiguration.isMiniCoalEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.VILLAGE_BLACKSMITH,
                new WeightedRandomChestContent(new ItemStack(ModItems.miniCoal), 2, 2, 2));
        }
        if (ModConfiguration.isMiniCharcoalEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.VILLAGE_BLACKSMITH,
                new WeightedRandomChestContent(new ItemStack(ModItems.miniCharcoal), 2, 2, 2));
        }
        if (ModConfiguration.isHungerPillEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.PYRAMID_JUNGLE_CHEST,
                new WeightedRandomChestContent(new ItemStack(ModItems.hungerPill), 1, 2, 1));
        }
        if (ModConfiguration.isDiamondZaneEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.DUNGEON_CHEST,
                new WeightedRandomChestContent(new ItemStack(ModItems.diamondZane), 1, 2, 1));
        }
        if (ModConfiguration.isSplatAxeEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.MINESHAFT_CORRIDOR,
                new WeightedRandomChestContent(new ItemStack(ModItems.splatAxe), 1, 1, 1));
        }
        if (ModConfiguration.isTerrainEaterEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.VILLAGE_BLACKSMITH,
                new WeightedRandomChestContent(new ItemStack(ModItems.terrainEater), 1, 2, 1));
        }
        if (ModConfiguration.isTotemToolEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.PYRAMID_DESERT_CHEST,
                new WeightedRandomChestContent(new ItemStack(ModItems.totemTool), 1, 1, 1));
            ChestGenHooks.addItem(
                ChestGenHooks.PYRAMID_JUNGLE_CHEST,
                new WeightedRandomChestContent(new ItemStack(ModItems.totemTool), 1, 1, 1));
        }
        if (ModConfiguration.isOfanixEnabled) {
            ChestGenHooks.addItem(
                ChestGenHooks.PYRAMID_DESERT_CHEST,
                new WeightedRandomChestContent(new ItemStack(ModItems.ofanix), 1, 1, 1));
        }
    }
}
