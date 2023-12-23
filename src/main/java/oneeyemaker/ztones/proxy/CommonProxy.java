package oneeyemaker.ztones.proxy;

import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.blocks.ModBlocks;
import oneeyemaker.ztones.items.ModItems;
import oneeyemaker.ztones.network.ModNetwork;

public class CommonProxy {

    public void preInitialize(FMLPreInitializationEvent event) {
        ModConfiguration.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        ModBlocks.registerBlocks();
        ModItems.registerItems();
    }

    public void initialize(FMLInitializationEvent event) {
        ModItems.registerHandlers();
        ModNetwork.registerMessages();
    }

    public void postInitialize(FMLPostInitializationEvent event) {}

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
