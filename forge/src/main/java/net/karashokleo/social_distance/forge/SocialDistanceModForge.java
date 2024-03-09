package net.karashokleo.social_distance.forge;

import net.karashokleo.social_distance.SocialDistance;
import net.minecraftforge.fml.common.Mod;

@Mod(SocialDistance.MOD_ID)
public class SocialDistanceModForge
{
    public SocialDistanceModForge() {
        SocialDistance.init();
    }
}
