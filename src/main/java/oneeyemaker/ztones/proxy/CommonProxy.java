package oneeyemaker.ztones.proxy;

import net.minecraft.world.World;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.Tags;
import oneeyemaker.ztones.Ztones;
import oneeyemaker.ztones.blocks.ModBlocks;
import oneeyemaker.ztones.gui.ModGui;
import oneeyemaker.ztones.integration.ChiselIntegration;
import oneeyemaker.ztones.items.ModItems;
import oneeyemaker.ztones.network.ModNetwork;
import oneeyemaker.ztones.world.ChestLootGenerator;

public class CommonProxy {

    public boolean isChiselLoaded = false;
    public boolean isChiselTonesLoaded = false;
    public boolean isDreamCraftLoaded = false;

    public void preInitialize(FMLPreInitializationEvent event) {
        isChiselLoaded = Loader.isModLoaded("chisel");
        isChiselTonesLoaded = Loader.isModLoaded("chiseltones");
        isDreamCraftLoaded = Loader.isModLoaded("dreamcraft");

        ModConfiguration.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ChestLootGenerator.populateChests();
    }

    public void initialize(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Tags.MODID, new ModGui());
        ModBlocks.registerRecipes();
        ModItems.registerRecipes();
        ModItems.registerHandlers();
        ModNetwork.registerMessages();
    }

    public void postInitialize(FMLPostInitializationEvent event) {
        if (isChiselLoaded) {
            if (!isChiselTonesLoaded) {
                ChiselIntegration.registerCarvingRecipes();
            } else {
                Ztones.LOG.info("Chisel integration is disabled because ChiselTones mod is present.");
            }
        }
    }

    public World getClientWorld() {
        return null;
    }

    public boolean isVariantCyclingEnabled() {
        return ModConfiguration.isVariantCyclingEnabled;
    }

    public void setVariantCyclingEnabled(boolean isVariantCyclingEnabled) {}

    public String getCyclingKeybinding() {
        return "";
    }
}
