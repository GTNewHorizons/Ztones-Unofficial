package oneeyemaker.ztones;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static void synchronizeConfiguration(File configurationFile) {
        Configuration configuration = new Configuration(configurationFile);
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
