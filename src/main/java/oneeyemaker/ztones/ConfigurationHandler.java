package oneeyemaker.ztones;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    private static final HashMap<ZtoneType, Boolean> enabledBlocks = new HashMap<>();

    public static void synchronizeConfiguration(File configurationFile) {
        Configuration configuration = new Configuration(configurationFile);
        for (ZtoneType type : ZtoneType.values()) {
            boolean isEnabled = configuration.getBoolean(
                String.format("enable%s", type.name()),
                "blocks",
                true,
                String.format("Should %s block be registered in game?", type.name()));
            enabledBlocks.put(type, isEnabled);
        }
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    public static boolean isBlockTypeEnabled(ZtoneType type) {
        return enabledBlocks.get(type);
    }
}
