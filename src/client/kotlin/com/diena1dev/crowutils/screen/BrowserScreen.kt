package com.diena1dev.crowutils.screen

import com.diena1dev.crowutils.browser.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.client.RenderHandler
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

@Suppress("unused")
class BrowserScreen(gameInstance: MinecraftClient, previousScreen: Text, url: String) : Screen(
    Text.literal("Browser")
) {
    val webVerticalOffset: Int = 20
    val webHorizontalOffset: Int = 10
    var isFullscreen = false

    override fun init() {
        super.init()
        RenderHandler.resizeBrowser(webBrowser, width, height, 20, 20)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        if (!isFullscreen)
            RenderHandler.drawBrowser(webBrowser, width, height, 20, 20, true)
    }

    override fun close() {
        super.close()
        webBrowser.resize(50, 50)
    }
}