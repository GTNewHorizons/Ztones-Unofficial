package oneeyemaker.ztones.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.Loader;
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

    public static ItemStack getBlock(ZtoneType type, int amount) {
        ZtoneGenericBlock genericBlock = genericBlocks.get(type);
        return genericBlock != null ? new ItemStack(genericBlock, amount) : null;
    }

    public static Block getBlock(ZtoneType type, Block replacement) {
        return type.isEnabled() ? genericBlocks.get(type) : replacement;
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
        if (Loader.isModLoaded("dreamcraft")) {
            GameRegistry.addRecipe(
                new ItemStack(aurora, 8),
                "ggg",
                "gdg",
                "ggg",
                'g',
                Blocks.glass,
                'd',
                new ItemStack(Items.dye, 1, OreDictionary.WILDCARD_VALUE));
            GameRegistry
                .addRecipe(getBlock(ZtoneType.Tile, 8), "sss", "sSs", "sss", 's', Blocks.stone_slab, 'S', Blocks.stone);
        } else {
            GameRegistry.addRecipe(
                new ItemStack(aurora, 8),
                "gg ",
                "gdg",
                " gg",
                'g',
                Blocks.glass,
                'd',
                new ItemStack(Items.dye, 1, OreDictionary.WILDCARD_VALUE));
            GameRegistry
                .addRecipe(getBlock(ZtoneType.Tile, 8), "ss ", "sSs", " ss", 's', Blocks.stone_slab, 'S', Blocks.stone);
            GameRegistry.addRecipe(
                new ItemStack(cleanDirt, 8),
                "ddd",
                "dws",
                "sss",
                'd',
                Blocks.dirt,
                's',
                Blocks.sand,
                'w',
                Items.wheat_seeds);
            addRecipe(new ItemStack(booster, 9), "rr ", 'r', "blockRedstone");
            addRecipe(
                new ItemStack(flatLamp, 16),
                "igi",
                "gGg",
                "igi",
                'i',
                "ingotIron",
                'g',
                Blocks.glass,
                'G',
                "dustGlowstone");
            GameRegistry.addShapelessRecipe(new ItemStack(flatLamp), new ItemStack(flatTransparentLamp));
            GameRegistry.addShapelessRecipe(new ItemStack(flatTransparentLamp), new ItemStack(flatDarkenedLamp));
            GameRegistry.addShapelessRecipe(new ItemStack(flatDarkenedLamp), new ItemStack(flatLamp));
            GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 4), "ii", "ii", 'i', Blocks.ice);
            GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new ItemStack(Blocks.packed_ice));
            for (int metadata = 0; metadata < 6; metadata++) {
                GameRegistry.addShapelessRecipe(
                    new ItemStack(Blocks.planks, 1, metadata),
                    new ItemStack(Blocks.wooden_slab, 1, metadata),
                    new ItemStack(Blocks.wooden_slab, 1, metadata));
            }
        }

        ItemStack tile = new ItemStack(getBlock(ZtoneType.Tile), 1, OreDictionary.WILDCARD_VALUE);
        if (ZtoneType.Agon.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Agon, 8), "ttt", "tat", "ttt", 't', tile.copy(), 'a', aurora);
        }
        if (ZtoneType.Azur.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Azur, 8), "ttt", "tdt", "ttt", 't', tile.copy(), 'd', "gemLapis");
            addRecipe(getBlock(ZtoneType.Azur, 8), "ttt", "tdt", "ttt", 't', tile.copy(), 'd', "gemLazurite");
        }
        if (ZtoneType.Bitt.isEnabled()) {
            addRecipe(
                getBlock(ZtoneType.Bitt, 8),
                "ttt",
                "twt",
                "ttt",
                't',
                tile.copy(),
                'w',
                new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE));
        }
        if (ZtoneType.Cray.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Cray, 16), "ccc", "cac", "ccc", 'c', Blocks.hardened_clay, 'a', aurora);
        }
        if (ZtoneType.Fort.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 9);
            addRecipe(getBlock(ZtoneType.Fort, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Glaxx.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Glaxx, 8), "ggg", "gag", "ggg", 'g', Blocks.glass, 'a', aurora);
        }
        if (ZtoneType.Iszm.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 8);
            addRecipe(getBlock(ZtoneType.Iszm, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Jelt.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Jelt, 8), "ttt", "tgt", "ttt", 't', tile.copy(), 'g', "ingotGold");
        }
        if (ZtoneType.Korp.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Korp, 8), "ttt", "tot", "ttt", 't', tile.copy(), 'o', Blocks.obsidian);
        }
        if (ZtoneType.Kryp.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Kryp, 8), "ttt", "tst", "ttt", 't', tile.copy(), 's', Blocks.soul_sand);
        }
        if (ZtoneType.Lair.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Lair, 8), "ttt", "tnt", "ttt", 't', tile.copy(), 'n', Blocks.netherrack);
        }
        if (ZtoneType.Lave.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Lave, 8), "ttt", "tit", "ttt", 't', tile.copy(), 'i', Blocks.ice);
        }
        if (ZtoneType.Mint.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Mint, 8), "ttt", "tst", "ttt", 't', tile.copy(), 's', Items.slime_ball);
        }
        if (ZtoneType.Myst.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Myst, 8), "mmm", "mam", "mmm", 'm', Blocks.brown_mushroom_block, 'a', aurora);
        }
        if (ZtoneType.Reds.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Reds, 8), "ttt", "trt", "ttt", 't', tile.copy(), 'r', "dustRedstone");
        }
        if (ZtoneType.Reed.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Reed, 8), "ttt", "trt", "ttt", 't', tile.copy(), 'r', Items.reeds);
        }
        if (ZtoneType.Roen.isEnabled()) {
            addRecipe(
                getBlock(ZtoneType.Roen, 8),
                "ttt",
                "tst",
                "ttt",
                't',
                tile.copy(),
                's',
                new ItemStack(Blocks.sandstone, 1, OreDictionary.WILDCARD_VALUE));
        }
        if (ZtoneType.Sols.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Sols, 8), "ttt", "tbt", "ttt", 't', tile.copy(), 'b', Items.blaze_powder);
        }
        if (ZtoneType.Sync.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Sync, 8), "ttt", "tet", "ttt", 't', tile.copy(), 'e', "gemEmerald");
        }
        if (ZtoneType.Tank.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Tank, 8), "ttt", "tit", "ttt", 't', tile.copy(), 'i', "ingotIron");
        }
        if (ZtoneType.Test.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Test, 8), "ttt", "tst", "ttt", 't', tile.copy(), 's', "stickWood");
        }
        if (ZtoneType.Vect.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Vect, 8), "ttt", "tgt", "ttt", 't', tile.copy(), 'g', Items.ghast_tear);
        }
        if (ZtoneType.Vena.isEnabled()) {
            addRecipe(getBlock(ZtoneType.Vena, 8), "ttt", "tet", "ttt", 't', tile.copy(), 'e', Items.ender_pearl);
        }
        if (ZtoneType.Zane.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 12);
            addRecipe(getBlock(ZtoneType.Zane, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zech.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 4);
            addRecipe(getBlock(ZtoneType.Zech, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zest.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 11);
            addRecipe(getBlock(ZtoneType.Zest, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zeta.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 13);
            addRecipe(getBlock(ZtoneType.Zeta, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zion.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 7);
            addRecipe(getBlock(ZtoneType.Zion, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zkul.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 10);
            addRecipe(getBlock(ZtoneType.Zkul, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zoea.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 14);
            addRecipe(getBlock(ZtoneType.Zoea, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zome.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 6);
            addRecipe(getBlock(ZtoneType.Zome, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zone.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 3);
            addRecipe(getBlock(ZtoneType.Zone, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Zorg.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 5);
            addRecipe(getBlock(ZtoneType.Zorg, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
        if (ZtoneType.Ztyl.isEnabled()) {
            addRecipe(
                getBlock(ZtoneType.Ztyl, 32),
                "ttt",
                "cti",
                "ttt",
                't',
                tile.copy(),
                'c',
                "blockCoal",
                'i',
                "blockIron");
            addRecipe(getBlock(ZtoneType.Ztyl, 8), "ttt", "tst", "ttt", 't', tile.copy(), 's', "ingotSteel");
        }
        if (ZtoneType.Zyth.isEnabled()) {
            ItemStack ingredient = new ItemStack(getBlock(ZtoneType.Ztyl, Blocks.stained_hardened_clay), 1, 15);
            addRecipe(getBlock(ZtoneType.Zyth, 8), "zzz", "zaz", "zzz", 'z', ingredient, 'a', aurora);
        }
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

    private static void addRecipe(ItemStack output, Object... ingredients) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, ingredients));
    }
}
