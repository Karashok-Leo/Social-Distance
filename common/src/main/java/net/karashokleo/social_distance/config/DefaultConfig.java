package net.karashokleo.social_distance.config;

import java.util.Map;

public class DefaultConfig
{
    public boolean show_message = true;
    public boolean message_overlay = true;
    public Map<String, Float> distance_config = Map.of(
            "minecraft:warden", 32f,
            "minecraft:wither", 32f
    );
}
