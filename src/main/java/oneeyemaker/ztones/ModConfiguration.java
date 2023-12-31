package oneeyemaker.ztones;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModConfiguration {

    public static boolean isVariantCyclingEnabled = true;
    public static boolean isVariantCraftingEnabled = true;
    public static boolean isChiselIntegrationEnabled = true;
    public static int chiselCarvingMode = 1;
    public static boolean isCreatureSpawnOnZtonesEnabled = false;
    public static boolean isMiniCoalEnabled = true;
    public static boolean isMiniCharcoalEnabled = true;
    public static boolean isHungerPillEnabled = true;
    public static boolean isDiamondZaneEnabled = true;
    public static boolean isSplatAxeEnabled = true;
    public static boolean isTerrainEaterEnabled = true;
    public static boolean isTotemToolEnabled = true;
    public static boolean isOfanixEnabled = true;
    public static boolean isChestLootGenerationEnabled = true;

    public static void synchronizeConfiguration(File configurationFile) {
        Configuration configuration = new Configuration(configurationFile);
        for (ZtoneType type : ZtoneType.values()) {
            if (type == ZtoneType.Tile) {
                type.setEnabled(true);
                continue;
            }
            boolean isEnabled = configuration.getBoolean(
                String.format("enable%s", type.name()),
                "blocks",
                true,
                String.format("Should %s block be registered?", type.name()));
            type.setEnabled(isEnabled);
        }
        isVariantCyclingEnabled = configuration.getBoolean(
            "enableVariantCycling",
            "general",
            isVariantCyclingEnabled,
            "Is Ztone variants scroll-cycling enabled?");
        isVariantCraftingEnabled = configuration.getBoolean(
            "enableVariantCrafting",
            "general",
            isVariantCraftingEnabled,
            "Can Ztone variants be crafted using Ofanix?");
        isChiselIntegrationEnabled = configuration.getBoolean(
            "enableChiselIntegration",
            "general",
            isChiselIntegrationEnabled,
            "Should Chisel recipes for carving of Ztone variants be registered?");
        chiselCarvingMode = configuration.getInt("chiselCarvingMode", "general", chiselCarvingMode, 0, 1, """
            There are 2 modes supported:
            0 - Allow chiseling of the Ztones Tile into each of the different types Ztones adds.
            1 - Allow chiseling between all the variants within their type.""");
        isCreatureSpawnOnZtonesEnabled = configuration.getBoolean(
            "enableCreatureSpawnOnZtones",
            "general",
            isCreatureSpawnOnZtonesEnabled,
            "Can mobs spawn on decorative blocks?");
        isMiniCoalEnabled = configuration.getBoolean("Mini Coal", "items", isMiniCoalEnabled, "Is mini coal enabled?");
        isMiniCharcoalEnabled = configuration
            .getBoolean("Mini Charcoal", "items", isMiniCharcoalEnabled, "Is mini charcoal enabled?");
        isHungerPillEnabled = configuration
            .getBoolean("MSG Pill", "items", isHungerPillEnabled, "Is hunger pill enabled?");
        isDiamondZaneEnabled = configuration
            .getBoolean("Diamond Zane", "items", isDiamondZaneEnabled, "Is Diamond Zane enabled?");
        isSplatAxeEnabled = configuration.getBoolean("Splat Axe", "items", isSplatAxeEnabled, "Is Splat Axe enabled?");
        isTerrainEaterEnabled = configuration
            .getBoolean("Terrain Eater", "items", isTerrainEaterEnabled, "Is Terrain Eater enabled?");
        isTotemToolEnabled = configuration
            .getBoolean("Totem Tool", "items", isTotemToolEnabled, "Is Totem tool enabled?");
        isOfanixEnabled = configuration.getBoolean("Ofanix", "items", isOfanixEnabled, "Is Ofanix tool enabled?");
        isChestLootGenerationEnabled = configuration.getBoolean(
            "enableChestGen",
            "tweaks",
            isChestLootGenerationEnabled,
            "Should random tools appear in dungeon chests?");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
