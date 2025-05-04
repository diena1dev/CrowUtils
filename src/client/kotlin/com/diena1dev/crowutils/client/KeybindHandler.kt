package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.browser.WebBrowserHandler
import com.diena1dev.crowutils.browser.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.config.Config
import com.diena1dev.crowutils.screen.BrowserScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text

@Suppress("unused")
object KeybindHandler: ClientModInitializer {

    val c: Config = Config

    val openUtilMenu: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding("Open Browser Window (WIP)", InputUtil.Type.KEYSYM, c.openMenu, "CrowUtils"))
    // Open Utility/Browser Menu
    val reloadBrowser: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding("Reload Browser", InputUtil.Type.KEYSYM, c.reloadBrowser, "CrowUtils"))
    // Reload Browser - TODO: Replace with a GUI button
    val zoomHUD: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding("Zoom HUD", InputUtil.Type.KEYSYM, c.zoomHUD, "CrowUtils"))
    // Open Web Browser Debug Menu
    val toggleHUD: KeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding("Toggle HUD", InputUtil.Type.KEYSYM, c.toggleHUD, "CrowUtils"))
    // Toggle HUD Rendering

    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            if (gameInstance.isFinishedLoading && !c.worldLoadStatus) {
                WebBrowserHandler.init()
                c.worldLoadStatus = true
            }

            if (openUtilMenu.wasPressed()) {
                if (WebBrowserHandler.isBrowserInit()) {
                    gameInstance.setScreen(BrowserScreen(gameInstance, Text.literal("DEBUG"), "https://google.com"))
                    WebBrowserHandler.injectCSS()
                } else {
                    WebBrowserHandler.init()
                    gameInstance.setScreen(BrowserScreen(gameInstance, Text.literal("DEBUG"), "https://google.com"))
                    WebBrowserHandler.injectCSS()
                }
            }

            /* while (zoomHUD.wasPressed()) {
                c.webHUDZoomed = true
            }
            while (!zoomHUD.wasPressed() && c.webHUDZoomed) {
                c.webHUDZoomed = false
            } */// TODO: Fix?

            if (zoomHUD.wasPressed()) {
                if (c.webHUDZoomed) {
                    c.webHUDZoomed = false
                } else {
                    c.webHUDZoomed = true
                }
            }

            if (reloadBrowser.wasPressed()) {
                WebBrowserHandler.refreshBrowser()
            }

            if (toggleHUD.wasPressed()) {
                if (c.webHUDEnabled && WebBrowserHandler.isBrowserInit()) {
                    c.webHUDEnabled = false
                } else if (!c.webHUDEnabled && WebBrowserHandler.isBrowserInit()) {
                    c.webHUDEnabled = true
                    //WebBrowserHandler.resizeBrowserPrecise(((c.webHUDSize*6.5*gameInstance.window.scaleFactor).toInt())*2, (c.webHUDSize*6.5*gameInstance.window.scaleFactor).toInt())
                }
            }
        })
    }

    override fun onInitializeClient() {
        init()
    }
}


