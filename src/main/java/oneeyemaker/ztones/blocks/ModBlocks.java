package oneeyemaker.ztones.blocks;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.ZtoneType;
import oneeyemaker.ztones.items.ModItems;
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

    public static void registerRecipes() {
        if (ModConfiguration.isOfanixEnabled && ModConfiguration.isVariantCraftingEnabled) {
            for (ZtoneType type : ZtoneType.values()) {
                registerOfanixRecipes(type);
            }
        }
    }

    private static void registerOfanixRecipes(ZtoneType type) {
        if (!type.isEnabled()) {
            return;
        }
        ZtoneGenericBlock genericBlock = getBlock(type);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 0),
            "ox ",
            "   ",
            "   ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 1),
            "o x",
            "   ",
            "   ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 2),
            "o  ",
            "x  ",
            "   ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 3),
            "o  ",
            " x ",
            "   ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 4),
            "o  ",
            "  x",
            "   ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 5),
            "o  ",
            "   ",
            "x  ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 6),
            "o  ",
            "   ",
            " x ",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 7),
            "o  ",
            "   ",
            "  x",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 8),
            "x  ",
            "   ",
            "  o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 9),
            " x ",
            "   ",
            "  o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 10),
            "  x",
            "   ",
            "  o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 11),
            "   ",
            "x  ",
            "  o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 12),
            "   ",
            " x ",
            "  o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 13),
            "   ",
            "  x",
            "  o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 14),
            "   ",
            "   ",
            "x o",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
        GameRegistry.addRecipe(
            new ItemStack(genericBlock, 1, 15),
            "   ",
            "   ",
            " xo",
            'x',
            new ItemStack(genericBlock, 1, OreDictionary.WILDCARD_VALUE),
            'o',
            ModItems.ofanix);
    }
}
