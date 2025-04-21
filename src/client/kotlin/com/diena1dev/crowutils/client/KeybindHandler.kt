package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.browser.WebBrowserHandler
import com.diena1dev.crowutils.browser.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.config.Config
import com.diena1dev.crowutils.screen.BrowserScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.input.Input
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text

import org.apache.logging.log4j.LogManager

@Suppress("unused")
object KeybindHandler: ClientModInitializer {

    val c: Config = Config
    val logger = LogManager.getLogger()

    fun init() {
        val openUtilMenu = KeyBindingHelper.registerKeyBinding(KeyBinding("Open Browser Window (WIP)", InputUtil.Type.KEYSYM, c.openMenu, "CrowUtils"))
        // Open Utility/Browser Menu
        val reloadBrowser = KeyBindingHelper.registerKeyBinding(KeyBinding("Reload Browser (Experimental)", InputUtil.Type.KEYSYM, c.reloadBrowser, "CrowUtils"))
        // Reload Browser - TODO: Replace with a GUI button, add safety to not crash on reload.
        val zoomHUD = KeyBindingHelper.registerKeyBinding(KeyBinding("Zoom HUD", InputUtil.Type.KEYSYM, c.zoomHUD, "CrowUtils"))
        // Open Web Browser Debug Menu
        val toggleHUD = KeyBindingHelper.registerKeyBinding(KeyBinding("Toggle HUD", InputUtil.Type.KEYSYM, c.toggleHUD, "CrowUtils"))
        // Toggle HUD Rendering

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            while (openUtilMenu.wasPressed()) {
                if (WebBrowserHandler.isBrowserInit()) {
                    gameInstance.setScreen(BrowserScreen(gameInstance, Text.literal("DEBUG"), "https://google.com"))
                } else WebBrowserHandler.init()
                    gameInstance.setScreen(BrowserScreen(gameInstance, Text.literal("DEBUG"), "https://google.com"))
            }

            /*while (zoomHUD.wasPressed()) {
                c.webHUDZoomed = true
            }
            while (!zoomHUD.wasPressed() && c.webHUDZoomed) {
                c.webHUDZoomed = false
            }*/// TODO: Fix?

            if (reloadBrowser.wasPressed()) {
                webBrowser.reload()
            }

            if (zoomHUD.wasPressed()) {
                if (c.webHUDZoomed) {
                    c.webHUDZoomed = false
                } else {
                    c.webHUDZoomed = true
                }
            }

            if (toggleHUD.wasPressed()) {
                    if (c.webHUDEnabled) {
                        c.webHUDEnabled = false
                    } else {
                        c.webHUDEnabled = true
                    }
            }
        })
    }

    override fun onInitializeClient() {
        init()
    }
}


