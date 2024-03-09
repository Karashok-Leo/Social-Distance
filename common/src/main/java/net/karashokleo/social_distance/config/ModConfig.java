package net.karashokleo.social_distance.config;

import net.karashokleo.social_distance.SocialDistance;
import net.tinyconfig.ConfigManager;

public class ModConfig
{
    public static final ConfigManager<DefaultConfig> manager = new ConfigManager<>
            (SocialDistance.MOD_ID, new DefaultConfig())
            .builder()
            .setDirectory(".")
            .enableLogging(true)
            .sanitize(true)
            .build();

    public static void init()
    {
        manager.refresh();
    }
}
