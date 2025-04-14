package com.diena1dev.crowutils

import com.diena1dev.crowutils.browser.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.client.RenderHandler
import com.diena1dev.crowutils.config.Config
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderTickCounter

@Suppress("unused")
class BrowserHUD: HudRenderCallback {
    override fun onHudRender(
        p0: DrawContext?,
        p1: RenderTickCounter?
    ) {
        RenderHandler.drawHUDBrowser(webBrowser, 50, true)
    }
}