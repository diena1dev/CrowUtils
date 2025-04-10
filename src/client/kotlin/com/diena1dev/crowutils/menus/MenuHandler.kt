package com.diena1dev.crowutils.menus

import com.cinemamod.mcef.MCEF
import com.diena1dev.crowutils.browser.web.WebBrowserHandler
import com.diena1dev.crowutils.browser.web.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.browser.web.WebBrowserHandler.weburl
import com.diena1dev.crowutils.client.gameInstance
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
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
import org.cef.misc.CefCursorType

@Suppress("unused")
class MenuHandler: Screen(Text.literal("DEBUG")) {

    val logger = LogManager.getLogger()

    override fun init() {
        super.init()
        System.out.println("init from menuhandler called")
        weburl = "https://google.com"
        WebBrowserHandler.init()
        if (MCEF.isInitialized()) {
            //webBrowser.resize(width*gameInstance.window.scaledWidth, height*gameInstance.window.scaledHeight)
            //webBrowser.resize(((gameInstance.window.width*2)*gameInstance.window.scaledWidth), ((gameInstance.window.height*2)*gameInstance.window.scaledHeight))
            webBrowser.resize(gameInstance.window.width, gameInstance.window.height)
        }

        //val buttonWidget: ButtonWidget.Builder? = ButtonWidget.builder(Text.literal("Hello World")) { WebBrowserHandler.refreshBrowser() }
        val customWidget = CustomWidget(20, 20, 20, 20) // pos - x, y | button - x, y
        this.addDrawableChild(customWidget)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        context.fill(0, 0, gameInstance.window.width, gameInstance.window.height, 0xFFFFFFF)

        if (MCEF.isInitialized()) {
            RenderSystem.disableDepthTest()
            RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR)
            RenderSystem.setShaderTexture(0, webBrowser.renderer.textureID)

            val t = Tessellator.getInstance()
            val buffer = t.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR)

            buffer.vertex(1f, height - 1f, 0f).texture(0.0f, 1.0f).color(255, 255, 255, 255) // Bottom Left
            buffer.vertex(width - 1f, height - 1f, 0f).texture(1.0f, 1.0f).color(255, 255, 255, 255) // Bottom Right
            buffer.vertex(width - 1f, 1f, 0f).texture(1.0f, 0.0f).color(255, 255, 255, 255) // Top Right
            buffer.vertex(1f, 1f, 0f).texture(0.0f, 0.0f).color(255, 255, 255, 255) // Top Left

            BufferRenderer.drawWithGlobalProgram(buffer.end())
            RenderSystem.enableDepthTest()
        } else { return }
    }

    fun scaleX(x: Int): Int { return (x*2*(gameInstance.window.scaledWidth)) }
    fun scaleY(y: Int): Int { return (y*2*(gameInstance.window.scaledHeight)) }
    fun mouseX(x: Double): Int { return ((x-1)*gameInstance.window.scaledWidth).toInt() }
    fun mouseY(y: Double): Int { return ((y-1)*gameInstance.window.scaledWidth).toInt() }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        webBrowser.sendMousePress(mouseX(mouseX), mouseY(mouseY), button)
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        webBrowser.sendMouseRelease(mouseX(mouseX), mouseY(mouseY), button)
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun mouseMoved(mouseX: Double, mouseY: Double) {
        webBrowser.sendMouseMove(mouseX(mouseX), mouseY(mouseY))
        super.mouseMoved(mouseX, mouseY)
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, horizontalAmount: Double, verticalAmount: Double): Boolean {
        webBrowser.sendMouseWheel(mouseX(mouseX), mouseY(mouseY), verticalAmount, horizontalAmount.toInt())
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)
    }

    class CustomWidget(x: Int, y: Int, width: Int, height: Int) : ClickableWidget(x, y, width, height, Text.empty()) {

        override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
            // We'll just draw a simple rectangle/square for now.
            // x1, y1, x2, y2, startColor, endColor
            var startColor = 0xFF00FF00.toInt() // Green
            var endColor = 0xFF0000FF.toInt() // Blue

            if (isHovered()) {
                startColor = 0xFFFF0000.toInt() // Red
                endColor = 0xFF00FFFF.toInt() // Cyan
            }

            context.fillGradient(x, y, x + width, y + height, startColor, endColor)
        }

        override fun appendClickableNarrations(builder: NarrationMessageBuilder) {
            // For brevity, we'll just skip this for now - if you want to add narration to your widget, you can do so here.
            return
        }
    }
}