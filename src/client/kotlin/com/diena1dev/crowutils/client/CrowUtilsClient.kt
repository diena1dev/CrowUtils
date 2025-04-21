package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.browser.WebBrowserHandler
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer
import net.minecraft.client.MinecraftClient

val gameInstance: MinecraftClient = MinecraftClient.getInstance()

@Suppress("unused")
class CrowUtilsClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeybindHandler.init()
        WebBrowserHandler.init()

        HudLayerRegistrationCallback.EVENT.register { layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.MISC_OVERLAYS, RenderHandler.HUDLayer()) }
    }
}
