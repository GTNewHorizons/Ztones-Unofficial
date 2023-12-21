package oneeyemaker.ztones.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.items.ZtoneGenericItemBlock;

public class ModBlocks {

    public static AuroraBlock auroraBlock;
    private static final HashMap<ZtoneType, ZtoneGenericBlock> genericBlocks = new HashMap<>();

    public static ZtoneGenericBlock getBlock(ZtoneType type) {
        return genericBlocks.get(type);
    }

    public static void registerBlocks() {
        auroraBlock = new AuroraBlock();
        GameRegistry.registerBlock(auroraBlock, "auroraBlock");
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
