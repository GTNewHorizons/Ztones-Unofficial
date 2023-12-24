package oneeyemaker.ztones.items;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ModConfiguration;

public class ModItems {

    public static MiniFuelItem miniCoal;
    public static MiniFuelItem miniCharcoal;
    public static HungerPillItem hungerPill;

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
    }

    public static void registerHandlers() {
        if (ModConfiguration.isMiniCoalEnabled || ModConfiguration.isMiniCharcoalEnabled) {
            GameRegistry.registerFuelHandler(new IFuelHandler() {

                @Override
                public int getBurnTime(ItemStack itemStack) {
                    if (itemStack != null && itemStack.getItem() instanceof MiniFuelItem) {
                        return 200;
                    }
                    return 0;
                }
            });
        }
    }
}
