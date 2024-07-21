package net.karashokleo.social_distance;

import net.karashokleo.social_distance.config.ModConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.text.DecimalFormat;

public class SocialDistance
{
    public static final String MOD_ID = "social_distance";

    private static final String TEXT_MESSAGE = "message.social_distance.out_of_range";

    public static void init()
    {
        ModConfig.refresh();
    }

    public static Component getMessage(float range, float distance)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return new TranslatableComponent(TEXT_MESSAGE, df.format(range), df.format(distance)).withStyle(ChatFormatting.RED);
    }
}
