package net.karashokleo.social_distance.mixin;

import net.karashokleo.social_distance.config.ModConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.text.DecimalFormat;

@Mixin(LivingEntity.class)
public class LivingEntityMixin
{
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void inject_hurt(DamageSource source, float f, CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity entity = (LivingEntity) (Object) this;
        Entity attacker = source.getEntity();
        if (entity.getLevel().isClientSide() || attacker == null) return;

        Float range = ModConfig.manager.value.distance_config.get(Registry.ENTITY_TYPE.getKey(entity.getType()));
        float distance = entity.distanceTo(attacker);
        if (range == null || range >= distance) return;
        if (ModConfig.manager.value.show_message && attacker instanceof ServerPlayer player)
        {
            DecimalFormat df = new DecimalFormat("0.00");
            Component component = new TranslatableComponent("message.social_distance.out_of_range", df.format(range), df.format(distance)).withStyle(ChatFormatting.RED);
            player.sendMessage(component, ChatType.GAME_INFO, Util.NIL_UUID);
        }
        cir.setReturnValue(false);
    }
}
