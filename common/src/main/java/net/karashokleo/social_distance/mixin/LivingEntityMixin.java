package net.karashokleo.social_distance.mixin;

import net.karashokleo.social_distance.SocialDistance;
import net.karashokleo.social_distance.config.ModConfig;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin
{
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void inject_hurt(DamageSource source, float f, CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity entity = (LivingEntity) (Object) this;
        Entity attacker = source.getEntity();
        if (entity.getLevel().isClientSide() || attacker == null) return;

        Float range = ModConfig.get().distance_config.get(Registry.ENTITY_TYPE.getKey(entity.getType()).toString());
        float distance = entity.distanceTo(attacker);
        if (range == null || range >= distance) return;
        if (ModConfig.get().show_message && attacker instanceof ServerPlayer player)
            player.sendSystemMessage(SocialDistance.getMessage(range, distance), ModConfig.get().message_overlay);
        cir.setReturnValue(false);
    }
}
