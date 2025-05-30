package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.browser.WebBrowserHandler.extractCoords
import com.diena1dev.crowutils.browser.WebBrowserHandler.sendCommand
import org.apache.logging.log4j.LogManager
import org.cef.browser.CefBrowser
import org.cef.callback.CefJSDialogCallback
import org.cef.handler.CefJSDialogHandler
import org.cef.handler.CefJSDialogHandlerAdapter
import org.cef.misc.BoolRef
import org.lwjgl.glfw.GLFW

val logger = LogManager.getLogger()

@Suppress("unused")
object Config {
    // Default Keybinds
    val openMenu = GLFW.GLFW_KEY_V
    val openSettings = GLFW.GLFW_KEY_X // Debug Menu for now....
    val toggleHUD = GLFW.GLFW_KEY_H
    val zoomHUD = GLFW.GLFW_KEY_Z
    val reloadBrowser = GLFW.GLFW_KEY_BACKSLASH

    var isHUDToggled = true
    var isFullscreen = true
    var worldLoadStatus = false
    var hasInjectedCSS = false

    // Default Browser Settings
    var webHomePage = "https://survival.horizonsend.net"
    var webTransparency = true
    var webLayer: Float = 1f
    var webOffsetHorizontal = 5
    var webOffsetVertical = 5

    var webHUDEnabled = false
    var webHUDZoomed = false
    var webHUDSize = 40
    var webHUDMapScale = 2
    var webHUDHorizontal = 30
    var webHUDVertical = 30

    // Default Colors
    val backgroundPrimary = 0xFF2C2C2C
    val backgroundSecondary = 0xFF2C2C2C
    val buttonPrimary = 0xFF333333
    val buttonSecondary = 0xFF222222
    val buttonHighlighted = 0xFF158348
    val primaryText = 0xFF165119
    val secondaryText = 0xFF137476
    val errorText = 0xFF165212

    // Crow Purple
    val crowPurple = 0xFF147586

    // Web Browser Snippets
    // full credit to [oxmc](https://github.com/oxmc) for their help in finding a way to read JS outputs! :D
    var JSReaderHackResult = "- -"
    val JSReaderHack: CefJSDialogHandler = object : CefJSDialogHandlerAdapter() { // displayHandler workaround to get JS results from MCEF
        override fun onJSDialog(
            browser: CefBrowser,
            origin_url: String,
            dialog_type: CefJSDialogHandler.JSDialogType,
            message_text: String,
            default_prompt_text: String,
            callback: CefJSDialogCallback,
            suppress_message: BoolRef
        ): Boolean {
            var localText = message_text.toString()
            var result = extractCoords(localText).toString()
            sendCommand(result)
            return super.onJSDialog(
                browser,
                origin_url,
                dialog_type,
                message_text,
                default_prompt_text,
                callback,
                suppress_message
            )
        }
    }
    val CSSThemeScript = "const injectCSS = css => {" +
            "  let el = document.createElement('style');" +
            "  el.type = 'text/css';" +
            "  el.innerText = css;" +
            "  document.head.appendChild(el);" +
            "  return el;" +
            "  };" +
            "injectCSS('/* By diena1dev (Crow) */" +
            "" +
            "/* Changes scale of and moves World icons */" +
            ".dynmap .sublist .item {" +
            "    display: block;" +
            "    float: right;" +
            "" +
            "scale:95%;" +
            "" +
            "    height: 18px;" +
            "    width: 18px;" +
            "" +
            "    padding: 2px;" +
            "    margin: -20px 3px 1px 1px;" +
            "" +
            "    border-radius: 1px;" +
            "    -moz-border-radius: 1px;" +
            "" +
            "    background: rgba(32,32,32,0.6);" +
            "    border: 1px solid rgba(64,64,64,0.6);" +
            "}" +
            "" +
            "/* Removes the bar across the list */" +
            ".dynmap .panel .subsection {" +
            "    display: block;" +
            "    clear: both;" +
            "" +
            "    width: 100%;" +
            "    line-height: 18px;" +
            "    margin: 0 0 7px 0;" +
            "" +
            "    border-bottom: 1px solid rgba(0,0,0,0);" +
            "" +
            "}" +
            "" +
            "/* This makes the selected World easy to see */" +
            ".dynmap .sublist .item.selected {" +
            "    background: rgba(128,128,128,0.5);" +
            "    border: 1px solid rgba(255,255,255,255);" +
            "}" +
            "" +
            "/* This disables the Compass */" +
            ".compass, .compass_NE, .compass_SE, .compass_NW, .compass_SW {" +
            "    display: block;" +
            "    position: absolute;" +
            "    z-index: 10;" +
            "    top: 0px;" +
            "    right: 0px;" +
            "    height: 0px;" +
            "    width: 0px;" +
            "    background-repeat: no-repeat;" +
            "}');"
}