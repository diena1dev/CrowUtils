package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.browser.WebBrowserHandler
import com.diena1dev.crowutils.browser.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.config.Config
import com.diena1dev.crowutils.menus.MenuHandler
import com.diena1dev.crowutils.screen.BrowserScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text

import org.apache.logging.log4j.LogManager

@Suppress("unused")
object KeybindHandler: ClientModInitializer {

    val logger = LogManager.getLogger()

    fun init() {
        val openUtilMenu = KeyBindingHelper.registerKeyBinding(KeyBinding("Open Menu (WIP)", InputUtil.Type.KEYSYM, Config().openMenu, "CrowUtils"))
        // Open Utility Menu
        val debugWebBrowserMenu = KeyBindingHelper.registerKeyBinding(KeyBinding("Open Web Browser (Debug)", InputUtil.Type.KEYSYM, Config().openSettings, "CrowUtils"))
        // Open Web Browser Debug Menu
        val experimentalHUDRendering = KeyBindingHelper.registerKeyBinding(KeyBinding("Toggle HUD (Experimental)", InputUtil.Type.KEYSYM, Config().toggleHUD, "CrowUtils"))
        // Toggle EXPERIMENTAL HUD Rendering

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            while (openUtilMenu.wasPressed()) {
                if (WebBrowserHandler.isBrowserInit()) {
                    gameInstance.setScreen(BrowserScreen(gameInstance, Text.literal("DEBUG"), "https://google.com"))
                } else WebBrowserHandler.init()
                    gameInstance.setScreen(BrowserScreen(gameInstance, Text.literal("DEBUG"), "https://google.com"))
            }

            while (debugWebBrowserMenu.wasPressed()) {
                if (WebBrowserHandler.isBrowserInit()) {
                    gameInstance.setScreen(MenuHandler())
                } else WebBrowserHandler.init()
                gameInstance.setScreen(MenuHandler())
            }

            var testToggle = false
            if (experimentalHUDRendering.wasPressed()) {
                System.out.println("$testToggle")
            }

            if (Config().isHUDToggled) {RenderHandler.drawHUDBrowser(webBrowser, 50, Config().isHUDToggled)}
        })
    }

    override fun onInitializeClient() {
        init()
    }
}


