package com.example.cleareffects.mixin;

import com.example.cleareffects.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "getShader", at = @At("HEAD"), cancellable = true)
    private void onGetShader(ResourceLocation id, CallbackInfoReturnable<net.minecraft.client.renderer.ShaderInstance> cir) {
        if (id == null) return;
        var player = Minecraft.getInstance().player;
        if (player == null) return;

        String path = id.getPath();

        if (ModConfig.disableBlindness && "shaders/core/blur.json".equals(path)) {
            if (player.hasEffect(MobEffects.BLINDNESS)) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (ModConfig.disableNausea && "shaders/core/nausea.json".equals(path)) {
            if (player.hasEffect(MobEffects.CONFUSION)) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (ModConfig.disableDarkness && "shaders/core/darkness.json".equals(path)) {
            if (player.hasEffect(MobEffects.DARKNESS)) {
                cir.setReturnValue(null);
                return;
            }
        }
    }
}
