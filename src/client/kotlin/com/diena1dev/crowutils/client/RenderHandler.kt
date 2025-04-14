package com.diena1dev.crowutils.client

import com.cinemamod.mcef.MCEFBrowser
import com.diena1dev.crowutils.config.Config
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gl.ShaderProgramKeys
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats

/**
 *  RenderHandler made by diena1dev, the Crow.
 *  Do not use without my permission!
 */


@Suppress("unused")
object RenderHandler {
    fun drawBrowser(browser: MCEFBrowser, width: Int, height: Int, widthOffset: Int, heightOffset: Int, isFullscreen: Boolean) {
        if (!isFullscreen) {
            RenderSystem.disableDepthTest()
            RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR)
            RenderSystem.setShaderTexture(0, browser.renderer.textureID)
            val tessellator = Tessellator.getInstance()
            val bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR)
            bufferBuilder.vertex(0f, height.toFloat(), 0f).texture(0f, 1f).color(255, 255, 255, 255)
            bufferBuilder.vertex(width.toFloat(), height.toFloat(), 0f).texture(1f, 1f).color(255, 255, 255, 255)
            bufferBuilder.vertex(width.toFloat(), 0f, 0f).texture(1f, 0f).color(255, 255, 255, 255)
            bufferBuilder.vertex(0f, 0f, 0f).texture(0f, 0f).color(255, 255, 255, 255)
            BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())
            RenderSystem.enableDepthTest()
        } else {
            RenderSystem.disableDepthTest()
            RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR)
            RenderSystem.setShaderTexture(0, browser.renderer.textureID)
            val tessellator = Tessellator.getInstance()
            val bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR)
            bufferBuilder.vertex(0f + widthOffset, height.toFloat() - heightOffset, 0f).texture(0f, 1f).color(255, 255, 255, 255)
            bufferBuilder.vertex(width.toFloat() - widthOffset, height.toFloat() - heightOffset, 0f).texture(1f, 1f).color(255, 255, 255, 255)
            bufferBuilder.vertex(width.toFloat() - widthOffset, 0f + heightOffset, 0f).texture(1f, 0f).color(255, 255, 255, 255)
            bufferBuilder.vertex(0f + widthOffset, 0f + heightOffset, 0f).texture(0f, 0f).color(255, 255, 255, 255)
            BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())
            RenderSystem.enableDepthTest()
        }
    }

    fun drawHUDBrowser(browser: MCEFBrowser, size: Int, isEnabled: Boolean) {
        //if (isEnabled) {
            RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX_COLOR)
            RenderSystem.setShaderTexture(0, browser.renderer.textureID)
            val tessellator = Tessellator.getInstance()
            val bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR)
            bufferBuilder.vertex(0f, size.toFloat(), 0f).texture(0f, 1f).color(255, 255, 255, 255)
            bufferBuilder.vertex(size.toFloat(), size.toFloat(), 0f).texture(1f, 1f).color(255, 255, 255, 255)
            bufferBuilder.vertex(size.toFloat(), 0f, 0f).texture(1f, 0f).color(255, 255, 255, 255)
            bufferBuilder.vertex(0f, 0f, 0f).texture(0f, 0f).color(255, 255, 255, 255)
            BufferRenderer.drawWithGlobalProgram(bufferBuilder.end())
        //} else {
        //    return
        //}
    }

    fun resizeBrowser(browser: MCEFBrowser, width: Int, height: Int, widthOffset: Int, heightOffset: Int) {
        browser.resize(width*gameInstance.window.scaleFactor.toInt() - widthOffset*2, height*gameInstance.window.scaleFactor.toInt() - heightOffset*2)
    }

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