package oneeyemaker.ztones;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModConfiguration {

    public static void synchronizeConfiguration(File configurationFile) {
        Configuration configuration = new Configuration(configurationFile);
        for (ZtoneType type : ZtoneType.values()) {
            boolean isEnabled = configuration.getBoolean(
                String.format("enable%s", type.name()),
                "blocks",
                true,
                String.format("Should %s block be registered in game?", type.name()));
            type.setEnabled(isEnabled);
        }
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}