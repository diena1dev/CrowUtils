package com.diena1dev.crowutils.menus

import com.diena1dev.crowutils.config.Config
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext

/**
 *  RenderHandler made by diena1dev, the Crow.
 *  Do not use without my permission!
 */


@Suppress("unused")
class RenderHandler() {
    fun drawBrowserSearchBar(
        context: DrawContext,
        textRenderer: TextRenderer,
        mouseX: Int, mouseY: Int,
    ) {
        val buttonSizeX = 10
        val buttonSizeY = 8

        var pos1 = 10
        var pos2 = 10

        context.fill(pos1, pos2, buttonSizeX, buttonSizeY, 0, Config().buttonSecondary.toInt())
        context.drawText(textRenderer, "TEST", pos1, pos2, Config().primaryText.toInt(), true)
    }
}