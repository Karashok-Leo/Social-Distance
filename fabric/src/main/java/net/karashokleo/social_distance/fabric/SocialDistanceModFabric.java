package net.karashokleo.social_distance.fabric;

import net.karashokleo.social_distance.SocialDistance;
import net.fabricmc.api.ModInitializer;

public class SocialDistanceModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SocialDistance.init();
    }
}
