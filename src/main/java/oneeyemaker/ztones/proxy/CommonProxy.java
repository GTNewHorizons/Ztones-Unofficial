package oneeyemaker.ztones.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import oneeyemaker.ztones.ConfigurationHandler;

public class CommonProxy {

    public void preInitialize(FMLPreInitializationEvent event) {
        ConfigurationHandler.synchronizeConfiguration(event.getSuggestedConfigurationFile());
    }

    public void initialize(FMLInitializationEvent event) {}

    public void postInitialize(FMLPostInitializationEvent event) {}
}
