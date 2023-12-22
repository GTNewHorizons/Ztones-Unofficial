package oneeyemaker.ztones.proxy;

import net.minecraft.world.World;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import oneeyemaker.ztones.ModConfiguration;
import oneeyemaker.ztones.blocks.ModBlocks;
import oneeyemaker.ztones.network.ModNetwork;

public class CommonProxy {

    public void preInitialize(FMLPreInitializationEvent event) {
        ModConfiguration.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        ModBlocks.registerBlocks();
    }

    public void initialize(FMLInitializationEvent event) {
        ModNetwork.registerMessages();
    }

    public void postInitialize(FMLPostInitializationEvent event) {}

    public World getClientWorld() {
        return null;
    }

    public String getCyclingKeybinding() {
        return "";
    }
}
