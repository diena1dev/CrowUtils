package com.diena1dev.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class TempMixin {
    @Inject(at = @At("HEAD"), method = "run")
    private void run(CallbackInfo info) {
        System.out.println("CrowUtils Started!");
    }
}
