package oneeyemaker.ztones.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.items.ZtoneGenericItemBlock;

public class ModBlocks {

    public static AuroraBlock auroraBlock;
    public static CleanDirtBlock cleanDirtBlock;
    public static FlatLampBlock flatLampBlock;
    public static FlatLampBlock flatTransparentLampBlock;
    public static FlatLampBlock flatDarkenedLampBlock;
    public static BoosterBlock boosterBlock;
    private static final HashMap<ZtoneType, ZtoneGenericBlock> genericBlocks = new HashMap<>();

    public static ZtoneGenericBlock getBlock(ZtoneType type) {
        return genericBlocks.get(type);
    }

    public static void registerBlocks() {
        auroraBlock = new AuroraBlock();
        GameRegistry.registerBlock(auroraBlock, "auroraBlock");
        cleanDirtBlock = new CleanDirtBlock();
        GameRegistry.registerBlock(cleanDirtBlock, "cleanDirt");
        flatLampBlock = new FlatLampBlock(false, false);
        GameRegistry.registerBlock(flatLampBlock, "lampf");
        flatTransparentLampBlock = new FlatLampBlock(true, false);
        GameRegistry.registerBlock(flatTransparentLampBlock, "lampt");
        flatDarkenedLampBlock = new FlatLampBlock(true, true);
        GameRegistry.registerBlock(flatDarkenedLampBlock, "lampb");
        boosterBlock = new BoosterBlock();
        GameRegistry.registerBlock(boosterBlock, "booster");

        for (ZtoneType type : ZtoneType.values()) {
            if (type.isEnabled()) {
                ZtoneGenericBlock ztoneGenericBlock = new ZtoneGenericBlock(type);
                genericBlocks.put(type, ztoneGenericBlock);
                GameRegistry
                    .registerBlock(ztoneGenericBlock, ZtoneGenericItemBlock.class, ztoneGenericBlock.getRegistryName());
            }
        }
    }
}
