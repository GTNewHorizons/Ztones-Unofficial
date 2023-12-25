package oneeyemaker.ztones.integration;

import com.cricketcraft.chisel.api.carving.CarvingUtils;

import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.blocks.ModBlocks;

public class ChiselIntegration {

    public static void registerCarvingRecipes() {
        if (!ModConfiguration.isChiselIntegrationEnabled) {
            return;
        }
        if (ModConfiguration.chiselCarvingMode == 0) {
            cycleThroughTypes();
        } else {
            cycleThroughVariants();
        }
    }

    private static void cycleThroughTypes() {
        for (int metadata = 0; metadata < ZtoneType.Variants; metadata++) {
            int order = 0;
            for (ZtoneType type : ZtoneType.values()) {
                if (!type.isEnabled()) {
                    continue;
                }
                CarvingUtils.chisel
                    .addVariation(String.format("ZtoneTile%d", metadata), ModBlocks.getBlock(type), metadata, order++);
            }
        }
    }

    private static void cycleThroughVariants() {
        for (ZtoneType type : ZtoneType.values()) {
            if (!type.isEnabled()) {
                continue;
            }
            for (int metadata = 0; metadata < ZtoneType.Variants; metadata++) {
                CarvingUtils.chisel.addVariation(type.name(), ModBlocks.getBlock(type), metadata, metadata);
            }
        }
    }
}
