package net.karashokleo.social_distance.config;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class DefaultConfig
{
    public boolean show_message = true;
    public boolean message_overlay = true;
    public Map<ResourceLocation, Float> distance_config = Map.of(
            new ResourceLocation("minecraft:warden"), 32f,
            new ResourceLocation("minecraft:wither"), 32f
    );
}
