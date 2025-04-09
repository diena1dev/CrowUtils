package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.browser.web.WebBrowserHandler
import com.diena1dev.crowutils.menus.MenuHandler
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

val gameInstance: MinecraftClient = MinecraftClient.getInstance()

@Suppress("unused")
class CrowUtilsClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeybindHandler.init()
        WebBrowserHandler.init()
    }
}
