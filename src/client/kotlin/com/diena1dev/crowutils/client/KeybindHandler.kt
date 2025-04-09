package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.menus.MenuHandler
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

import org.apache.logging.log4j.LogManager

@Suppress("unused")
object KeybindHandler: ClientModInitializer {

    val logger = LogManager.getLogger()

    fun init() {
        val openUtilMenu = KeyBindingHelper.registerKeyBinding(KeyBinding("Open Menu (WIP)", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "CrowUtils"))
        // Open Utility Menu
        val debugWebBrowserMenu = KeyBindingHelper.registerKeyBinding(KeyBinding("Open Web Browser (Debug)", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "CrowUtils"))
        // Open Web Browser Debug Menu

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client ->
            while (openUtilMenu.wasPressed()) {
                gameInstance.setScreen(MenuHandler())
                System.out.println("SAW PRESS")
            }
        })
    }

    override fun onInitializeClient() {
        init()
    }
}


