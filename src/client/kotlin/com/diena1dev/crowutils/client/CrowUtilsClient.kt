package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.BrowserHUD
import com.diena1dev.crowutils.screen.BrowserScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

val gameInstance: MinecraftClient = MinecraftClient.getInstance()

@Suppress("unused")
class CrowUtilsClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeybindHandler.init()

        HudLayerRegistrationCallback.EVENT.register {BrowserHUD()}
    }
}
