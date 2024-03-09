package net.karashokleo.social_distance.mixin;

import net.karashokleo.social_distance.config.ModConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
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
        if (entity.level().isClientSide() || attacker == null) return;

        Float range = ModConfig.manager.value.distance_config.get(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()));
        float distance = entity.distanceTo(attacker);
        if (range == null || range >= distance) return;
        if (ModConfig.manager.value.show_message && attacker instanceof ServerPlayer player)
        {
            DecimalFormat df = new DecimalFormat("0.00");
            Component component = Component.translatable("message.social_distance.out_of_range", df.format(range), df.format(distance)).withStyle(ChatFormatting.RED);
            player.sendSystemMessage(component, ModConfig.manager.value.message_overlay);
        }
        cir.setReturnValue(false);
    }
}
