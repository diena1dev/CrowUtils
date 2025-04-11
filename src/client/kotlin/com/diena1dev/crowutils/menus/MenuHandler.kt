package com.diena1dev.crowutils.menus

import com.cinemamod.mcef.MCEF
import com.diena1dev.crowutils.browser.web.WebBrowserHandler
import com.diena1dev.crowutils.browser.web.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.client.gameInstance
import com.diena1dev.crowutils.config.Config
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.text.Text

import org.apache.logging.log4j.LogManager

@Suppress("unused")
class   MenuHandler: Screen(Text.literal("DEBUG")) {

    val logger = LogManager.getLogger()
    var horizontalOffset = 20
    var verticalOffset = 30


    override fun init() {
        super.init()
        if (MCEF.isInitialized() && WebBrowserHandler.isBrowserInit()) {
            webBrowser.resize(width*gameInstance.window.scaleFactor.toInt(), height*gameInstance.window.scaleFactor.toInt())
            System.out.println(
                "'width': $width 'height': $height 'gameInstance.width': ${gameInstance.window.width}" +
                        "'gameInstance.height': ${gameInstance.window.height} gameInstance.scaledWidth: '${gameInstance.window.scaledWidth}' gameInstance.scaledHeight: '${gameInstance.window.scaledHeight}'" +
                        "gameInstance.scaleFactor: '${gameInstance.window.scaleFactor}'"
            )
        }

        //val webBrowserBar = CustomWidget(gameInstance.window.width/2, verticalOffset+2, 3, 1) // pos - x, y | button - x, y
        //val webRefreshButton = CustomWidget((gameInstance.window.width/2)-7, verticalOffset+2, 1, 1)
        //this.addDrawableChild(webBrowserBar)
        //this.addDrawableChild(webRefreshButton)

        // make custom function in RenderHandler for this ^
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        RenderHandler().drawBrowserSearchBar(context, textRenderer, mouseX, mouseY)
        context.fill(0, 0, width, height, -1, Config().backgroundPrimary.toInt())
        super.render(context, mouseX, mouseY, delta)

        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR)
        RenderSystem.setShaderTexture(0, webBrowser.renderer.textureID)

        val t = Tessellator.getInstance()
        val buffer = t.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR)

        // Width is from the LEFT to the RIGHT, while Height is from the TOP to the BOTTOM

        // when adding values to offsets and position, mirror those added values to the browser.resize() and mouseScale()
        // TO ENSURE CONSISTENT RESULTS!

        // "gameInstance.height" and "gameInstance.width" are both reading the actual window size.
        // meanwhile, "width" and "height" measure the SCALED resolution for screens and GUIs.
        // "gameInstance.scaleFactor" is what gameInstance width/height is divided by to get the *scaled* resolution
        // see: 1920px/4 = 480px, where 1920px is from gameInstance.width, 4 is from gameInstance.scaleFactor, and 480 from width (already scaled width from the screen.)

        buffer.vertex(
            0f, height.toFloat(), Config().webLayer
        ).texture(0.0f, 1.0f).color(255, 255, 255, 255) // Bottom Left

        buffer.vertex(
            width.toFloat(), height.toFloat(), Config().webLayer
        ).texture(1.0f, 1.0f).color(255, 255, 255, 255) // Bottom Right

        buffer.vertex(
            width.toFloat(), 0f, Config().webLayer
        ).texture(1.0f, 0.0f).color(255, 255, 255, 255) // Top Right

        buffer.vertex(
            0f, 0f, Config().webLayer
        ).texture(0.0f, 0.0f).color(255, 255, 255, 255) // Top Left

        BufferRenderer.drawWithGlobalProgram(buffer.end())
    }

    fun mouseScaleX(x: Double): Int { return (((x/*(horizontalOffset/2))*/*gameInstance.window.scaleFactor).toInt())) }
    fun mouseScaleY(y: Double): Int { return ((y)*gameInstance.window.scaleFactor).toInt() }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        webBrowser.sendMousePress(mouseScaleX(mouseX), mouseScaleY(mouseY), button)
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        webBrowser.sendMouseRelease(mouseScaleX(mouseX), mouseScaleY(mouseY), button)
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun mouseMoved(mouseX: Double, mouseY: Double) {
        webBrowser.sendMouseMove(mouseScaleX(mouseX), mouseScaleY(mouseY))
        super.mouseMoved(mouseX, mouseY)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, horizontalAmount: Double, verticalAmount: Double): Boolean {
        webBrowser.sendMouseWheel(mouseScaleX(mouseX), mouseScaleY(mouseY), verticalAmount, horizontalAmount.toInt())
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)
    }

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
}