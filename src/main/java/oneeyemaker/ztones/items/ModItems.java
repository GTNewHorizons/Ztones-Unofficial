package oneeyemaker.ztones.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Ztones;
import oneeyemaker.ztones.items.tools.DiamondZaneItem;
import oneeyemaker.ztones.items.tools.OfanixItem;
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
    public static OfanixItem ofanix;

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
        if (ModConfiguration.isOfanixEnabled) {
            ofanix = new OfanixItem();
            GameRegistry.registerItem(ofanix, "ofanix");
        }
    }

    public static void registerRecipes() {
        if (ModConfiguration.isMiniCoalEnabled) {
            if (Ztones.proxy.isDreamCraftLoaded) {
                addRecipe(
                    new ItemStack(miniCoal, 7),
                    "T  ",
                    "C  ",
                    "   ",
                    'T',
                    "craftingToolSoftMallet",
                    'C',
                    "dustCoal");
            } else {
                GameRegistry.addShapelessRecipe(new ItemStack(miniCoal, 8), new ItemStack(Items.coal));
                GameRegistry.addRecipe(new ItemStack(Items.coal), "ccc", "c c", "ccc", 'c', new ItemStack(miniCoal));
            }
        }
        if (ModConfiguration.isMiniCharcoalEnabled) {
            if (Ztones.proxy.isDreamCraftLoaded) {
                addRecipe(
                    new ItemStack(miniCharcoal, 7),
                    "T  ",
                    "C  ",
                    "   ",
                    'T',
                    "craftingToolSoftMallet",
                    'C',
                    "dustCharcoal");
            } else {
                GameRegistry.addShapelessRecipe(new ItemStack(miniCharcoal, 8), new ItemStack(Items.coal, 1, 1));
                GameRegistry
                    .addRecipe(new ItemStack(Items.coal, 1, 1), "ccc", "c c", "ccc", 'c', new ItemStack(miniCharcoal));
            }
        }
        if (ModConfiguration.isHungerPillEnabled) {
            if (!Ztones.proxy.isDreamCraftLoaded) {
                GameRegistry.addShapelessRecipe(
                    new ItemStack(hungerPill),
                    new ItemStack(Items.slime_ball),
                    new ItemStack(Items.rotten_flesh),
                    new ItemStack(Items.sugar));
            }
        }
        if (ModConfiguration.isOfanixEnabled) {
            if (!Ztones.proxy.isDreamCraftLoaded) {
                addRecipe(
                    new ItemStack(ModItems.ofanix),
                    "eil",
                    "ici",
                    "wid",
                    'i',
                    "ingotIron",
                    'c',
                    Blocks.crafting_table,
                    'w',
                    Items.water_bucket,
                    'l',
                    Items.lava_bucket,
                    'd',
                    "gemDiamond",
                    'e',
                    Items.ender_pearl);
                GameRegistry.addShapelessRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(ofanix));
            }
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

    private static void addRecipe(ItemStack output, Object... ingredients) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, ingredients));
    }
}
