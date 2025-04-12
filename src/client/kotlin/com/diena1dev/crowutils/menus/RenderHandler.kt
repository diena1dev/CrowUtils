package com.diena1dev.crowutils.menus

import com.diena1dev.crowutils.config.Config
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext

/**
 *  RenderHandler made by diena1dev, the Crow.
 *  Do not use without my permission!
 */


@Suppress("unused")
object RenderHandler {
    class RenderBrowserSearchBar(posX: Int, posY: Int, elementWidth: Int, elementHeight: Int) {
        fun drawBrowserSearchBar(
            context: DrawContext,
            textRenderer: TextRenderer,
            mouseX: Int, mouseY: Int,
            posX: Int, posY: Int,
            buttonSizeX:Int, buttonSizeY:Int
        ) {
            context.fill(posX, posY, buttonSizeX, buttonSizeY, 0, Config().buttonSecondary.toInt())
            context.drawText(textRenderer, "TEST", posX, posY, Config().primaryText.toInt(), true)
        }
    }

    class RenderClickableButton(posX: Int, posY: Int, elementWidth: Int, elementHeight: Int) {
        fun drawClickableButton() {}
    }


    /*
     class CustomWidget(x: Int, y: Int, width: Int, height: Int) : ClickableWidget(x, y, width, height, Text.empty()) {

        override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
            var color = Config().buttonPrimary

            if (isHovered()) {
                color = Config().buttonHighlighted
            }

            context.fill(x, y, width, height, color.toInt())
        }

        override fun appendClickableNarrations(builder: NarrationMessageBuilder) {
            // For brevity, we'll just skip this for now - if you want to add narration to your widget, you can do so here.
            return
        }
    }
     */
}