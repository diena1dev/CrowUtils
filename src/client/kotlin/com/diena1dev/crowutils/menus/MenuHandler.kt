package com.diena1dev.crowutils.menus

import com.cinemamod.mcef.MCEF
import com.diena1dev.crowutils.browser.web.WebBrowserHandler
import com.diena1dev.crowutils.browser.web.WebBrowserHandler.webBrowser
import com.diena1dev.crowutils.browser.web.WebBrowserHandler.weburl
import com.diena1dev.crowutils.client.gameInstance
import com.diena1dev.crowutils.config.Config
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

@Suppress("unused")
class MenuHandler: Screen(Text.literal("DEBUG")) {

    val logger = LogManager.getLogger()
    var horizontalOffset = 20
    var verticalOffset = 30


    override fun init() {
        super.init()
        System.out.println("init from menuhandler called")
        weburl = "https://google.com"
        WebBrowserHandler.init()
        if (MCEF.isInitialized()) {
            //webBrowser.resize(width*gameInstance.window.scaledWidth, height*gameInstance.window.scaledHeight)
            //webBrowser.resize(((gameInstance.window.width*2)*gameInstance.window.scaledWidth), ((gameInstance.window.height*2)*gameInstance.window.scaledHeight))
            //webBrowser.resize(((width-horizontalOffset)*gameInstance.window.scaleFactor.toInt()), ((height-verticalOffset)*gameInstance.window.scaleFactor.toInt()))
            webBrowser.resize(gameInstance.window.width*4, gameInstance.window.height*4)
        }

        //val buttonWidget: ButtonWidget.Builder? = ButtonWidget.builder(Text.literal("Hello World")) { WebBrowserHandler.refreshBrowser() }
        //val webBrowserBar = CustomWidget(gameInstance.window.width/2, verticalOffset+2, 3, 1) // pos - x, y | button - x, y
        //val webRefreshButton = CustomWidget((gameInstance.window.width/2)-7, verticalOffset+2, 1, 1)
        //this.addDrawableChild(webBrowserBar)
        //this.addDrawableChild(webRefreshButton)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        RenderHandler().drawBrowserSearchBar(context, textRenderer, mouseX, mouseY)
        context.fill(0, 0, gameInstance.window.width, gameInstance.window.height, -1, Config().backgroundPrimary.toInt())
        super.render(context, mouseX, mouseY, delta)

        if (MCEF.isInitialized()) {
            RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR)
            RenderSystem.setShaderTexture(0, webBrowser.renderer.textureID)

            val t = Tessellator.getInstance()
            val buffer = t.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR)

            // Width is from the LEFT to the RIGHT, while Height is from the TOP to the BOTTOM

            buffer.vertex(
             0f, gameInstance.window.height.toFloat(), Config().webLayer
            ).texture(0.0f, 1.0f).color(255, 255, 255, 255) // Bottom Left

            buffer.vertex(
                gameInstance.window.width.toFloat(), gameInstance.window.height.toFloat(), Config().webLayer
            ).texture(1.0f, 1.0f).color(255, 255, 255, 255) // Bottom Right

            buffer.vertex(
                gameInstance.window.width.toFloat(), 0f, Config().webLayer
            ).texture(1.0f, 0.0f).color(255, 255, 255, 255) // Top Right

            buffer.vertex(
                0f, 0f, Config().webLayer
            ).texture(0.0f, 0.0f).color(255, 255, 255, 255) // Top Left

            BufferRenderer.drawWithGlobalProgram(buffer.end())
        } else { return }
    }

    fun mouseScaleX(x: Double): Int { return (((x-(horizontalOffset/2)-2)*gameInstance.window.scaleFactor).toInt()) }
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