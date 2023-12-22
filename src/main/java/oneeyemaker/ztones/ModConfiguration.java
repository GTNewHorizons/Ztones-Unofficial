package oneeyemaker.ztones;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModConfiguration {

    public static boolean isVariantCyclingEnabled = true;
    public static boolean isCreatureSpawnOnZtonesEnabled = false;

    public static void synchronizeConfiguration(File configurationFile) {
        Configuration configuration = new Configuration(configurationFile);
        for (ZtoneType type : ZtoneType.values()) {
            boolean isEnabled = configuration.getBoolean(
                String.format("enable%s", type.name()),
                "blocks",
                true,
                String.format("Should %s block be registered?", type.name()));
            type.setEnabled(isEnabled);
        }
        isVariantCyclingEnabled = configuration.getBoolean(
            "enableVariantCycling",
            "general",
            isVariantCyclingEnabled,
            "Is Ztone variant scroll-cycling enabled?");
        isCreatureSpawnOnZtonesEnabled = configuration.getBoolean(
            "enableCreatureSpawnOnZtones",
            "general",
            isCreatureSpawnOnZtonesEnabled,
            "Can mobs spawn on decorative blocks?");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
