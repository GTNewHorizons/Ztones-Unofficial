package oneeyemaker.ztones.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.items.SimpleItemBlock;
import oneeyemaker.ztones.items.ZtoneGenericItemBlock;

public class ModBlocks {

    public static AuroraBlock aurora;
    public static CleanDirtBlock cleanDirt;
    public static FlatLampBlock flatLamp;
    public static FlatLampBlock flatTransparentLamp;
    public static FlatLampBlock flatDarkenedLamp;
    public static BoosterBlock booster;
    private static final HashMap<ZtoneType, ZtoneGenericBlock> genericBlocks = new HashMap<>();

    public static ZtoneGenericBlock getBlock(ZtoneType type) {
        return genericBlocks.get(type);
    }

    public static void registerBlocks() {
        aurora = new AuroraBlock();
        GameRegistry.registerBlock(aurora, SimpleItemBlock.class, "auroraBlock");
        cleanDirt = new CleanDirtBlock();
        GameRegistry.registerBlock(cleanDirt, SimpleItemBlock.class, "cleanDirt");
        flatLamp = new FlatLampBlock(false, false);
        GameRegistry.registerBlock(flatLamp, "lampf");
        flatTransparentLamp = new FlatLampBlock(true, false);
        GameRegistry.registerBlock(flatTransparentLamp, "lampt");
        flatDarkenedLamp = new FlatLampBlock(true, true);
        GameRegistry.registerBlock(flatDarkenedLamp, "lampb");
        booster = new BoosterBlock();
        GameRegistry.registerBlock(booster, "booster");

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
