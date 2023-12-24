package oneeyemaker.ztones.items;

import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.items.tools.DiamondZaneItem;
import oneeyemaker.ztones.items.tools.SplatAxeItem;
import oneeyemaker.ztones.items.tools.TerrainEaterItem;
import oneeyemaker.ztones.items.tools.TotemToolItem;

public class ModItems {

    public static MiniFuelItem miniCoal;
    public static MiniFuelItem miniCharcoal;
    public static HungerPillItem hungerPill;
    public static DiamondZaneItem diamondZane;
    public static SplatAxeItem splatAxe;
    public static TerrainEaterItem terrainEater;
    public static TotemToolItem totemTool;

    public static void registerItems() {
        if (ModConfiguration.isMiniCoalEnabled) {
            miniCoal = new MiniFuelItem("Coal");
            GameRegistry.registerItem(miniCoal, "minicoal");
        }
        if (ModConfiguration.isMiniCharcoalEnabled) {
            miniCharcoal = new MiniFuelItem("Charcoal");
            GameRegistry.registerItem(miniCharcoal, "minicharcoal");
        }
        if (ModConfiguration.isHungerPillEnabled) {
            hungerPill = new HungerPillItem();
            GameRegistry.registerItem(hungerPill, "hunger");
        }
        if (ModConfiguration.isDiamondZaneEnabled) {
            diamondZane = new DiamondZaneItem();
            GameRegistry.registerItem(diamondZane, "diamondZane");
        }
        if (ModConfiguration.isSplatAxeEnabled) {
            splatAxe = new SplatAxeItem();
            GameRegistry.registerItem(splatAxe, "splatAxe");
        }
        if (ModConfiguration.isTerrainEaterEnabled) {
            terrainEater = new TerrainEaterItem();
            GameRegistry.registerItem(terrainEater, "terrainEater");
        }
        if (ModConfiguration.isTotemToolEnabled) {
            totemTool = new TotemToolItem();
            GameRegistry.registerItem(totemTool, "totemTool");
        }
    }

    public static void registerHandlers() {
        if (ModConfiguration.isMiniCoalEnabled || ModConfiguration.isMiniCharcoalEnabled) {
            GameRegistry.registerFuelHandler(itemStack -> {
                if (itemStack != null && itemStack.getItem() instanceof MiniFuelItem) {
                    return 200;
                }
                return 0;
            });
        }
    }
}
