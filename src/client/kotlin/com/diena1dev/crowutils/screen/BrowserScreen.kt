package com.diena1dev.crowutils.screen

import com.diena1dev.crowutils.browser.WebBrowserHandler
import com.diena1dev.crowutils.browser.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.client.KeybindHandler
import com.diena1dev.crowutils.client.RenderHandler
import com.diena1dev.crowutils.client.gameInstance
import com.diena1dev.crowutils.config.Config
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import org.cef.misc.CefCursorType

@Suppress("unused")
class BrowserScreen(gameInstance: MinecraftClient, previousScreen: Text, url: String) : Screen(
    Text.literal("Browser")
) {
    val c = Config

    fun mouseScaleX(x: Double): Int { return ((x)*gameInstance.window.scaleFactor).toInt() }
    fun mouseScaleY(y: Double): Int { return ((y)*gameInstance.window.scaleFactor).toInt() }

    override fun init() {
        super.init()
        //RenderHandler.resizeBrowser(webBrowser, width, height, c.webOffsetHorizontal, c.webOffsetVertical)
        WebBrowserHandler.resizeBrowserAuto()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        if (!c.isFullscreen)
            RenderHandler.drawBrowser(webBrowser, width, height, c.webOffsetHorizontal, c.webOffsetVertical, false)
        else
            RenderHandler.drawBrowser(webBrowser, width, height, c.webOffsetHorizontal, c.webOffsetVertical, true)
    }

    override fun close() {
        // TODO: find why in the world the mouse cursor becomes not captive when exiting map.
        webBrowser.resize(((c.webHUDSize*6.5*gameInstance.window.scaleFactor).toInt())*2, (c.webHUDSize*6.5*gameInstance.window.scaleFactor).toInt())
        super.close() // Super calls the NOT overrided function, very helpful here!
    }

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        if ("key.keyboard.$chr" == KeyBindingHelper.getBoundKeyOf(KeybindHandler.openUtilMenu).toString()) {
            close()
        }
        return super.charTyped(chr, modifiers)
    }

    override fun mouseMoved(mouseX: Double, mouseY: Double) {
        webBrowser.sendMouseMove(mouseScaleX(mouseX), mouseScaleY(mouseY))
        super.mouseMoved(mouseX, mouseY)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        webBrowser.sendMousePress(mouseScaleX(mouseX), mouseScaleY(mouseY), button)
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        webBrowser.sendMouseRelease(mouseScaleX(mouseX), mouseScaleY(mouseY), button)
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun mouseScrolled(
        mouseX: Double,
        mouseY: Double,
        horizontalAmount: Double,
        verticalAmount: Double
    ): Boolean {
        webBrowser.sendMouseWheel(mouseScaleX(mouseX), mouseScaleY(mouseY), verticalAmount, 0)
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)
    }

    override fun resize(client: MinecraftClient?, width: Int, height: Int) {
        WebBrowserHandler.resizeBrowserAuto()
        super.resize(client, width, height)
    }
}