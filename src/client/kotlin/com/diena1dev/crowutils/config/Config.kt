package com.diena1dev.crowutils.config

import com.diena1dev.crowutils.browser.WebBrowserHandler
import org.cef.browser.CefBrowser
import org.cef.callback.CefJSDialogCallback
import org.cef.handler.CefJSDialogHandler
import org.cef.handler.CefJSDialogHandlerAdapter
import org.cef.misc.BoolRef
import org.lwjgl.glfw.GLFW

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
    val JSReaderHack: CefJSDialogHandler = object : CefJSDialogHandlerAdapter() { // displayHandler workaround to get JS results from MCEF
        override fun onJSDialog(
            browser: CefBrowser?,
            origin_url: String?,
            dialog_type: CefJSDialogHandler.JSDialogType?,
            message_text: String?,
            default_prompt_text: String?,
            callback: CefJSDialogCallback?,
            suppress_message: BoolRef?
        ): Boolean {
            WebBrowserHandler.JSReaderHackResult = message_text.toString()
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
    val CSSThemeScript = "\"const injectCSS = css => {\" +\n" +
            "                        \"  let el = document.createElement('style');\" +\n" +
            "                        \"  el.type = 'text/css';\" +\n" +
            "                        \"  el.innerText = css;\" +\n" +
            "                        \"  document.head.appendChild(el);\" +\n" +
            "                        \"  return el;\" +\n" +
            "                        \"};\" +\n" +
            "                        \"injectCSS('/* By diena1dev (Crow) */\" +\n" +
            "                        \"\" +\n" +
            "                        \"/* Changes scale of and moves World icons */\" +\n" +
            "                        \".dynmap .sublist .item {\" +\n" +
            "                        \"    display: block;\" +\n" +
            "                        \"    float: right;\" +\n" +
            "                        \"\" +\n" +
            "                        \"scale:95%;\" +\n" +
            "                        \"\" +\n" +
            "                        \"    height: 18px;\" +\n" +
            "                        \"    width: 18px;\" +\n" +
            "                        \"\" +\n" +
            "                        \"    padding: 2px;\" +\n" +
            "                        \"    margin: -20px 3px 1px 1px;\" +\n" +
            "                        \"\" +\n" +
            "                        \"    border-radius: 1px;\" +\n" +
            "                        \"    -moz-border-radius: 1px;\" +\n" +
            "                        \"\" +\n" +
            "                        \"    background: rgba(32,32,32,0.6);\" +\n" +
            "                        \"    border: 1px solid rgba(64,64,64,0.6);\" +\n" +
            "                        \"}\" +\n" +
            "                        \"\" +\n" +
            "                        \"/* Removes the bar across the list */\" +\n" +
            "                        \".dynmap .panel .subsection {\" +\n" +
            "                        \"    display: block;\" +\n" +
            "                        \"    clear: both;\" +\n" +
            "                        \"\" +\n" +
            "                        \"    width: 100%;\" +\n" +
            "                        \"    line-height: 18px;\" +\n" +
            "                        \"    margin: 0 0 7px 0;\" +\n" +
            "                        \"\" +\n" +
            "                        \"    border-bottom: 1px solid rgba(0,0,0,0);\" +\n" +
            "                        \"\" +\n" +
            "                        \"}\" +\n" +
            "                        \"\" +\n" +
            "                        \"/* This makes the selected World easy to see */\" +\n" +
            "                        \".dynmap .sublist .item.selected {\" +\n" +
            "                        \"    background: rgba(128,128,128,0.5);\" +\n" +
            "                        \"    border: 1px solid rgba(255,255,255,255);\" +\n" +
            "                        \"}\" +\n" +
            "                        \"\" +\n" +
            "                        \"/* This disables the Compass */\" +\n" +
            "                        \".compass, .compass_NE, .compass_SE, .compass_NW, .compass_SW {\" +\n" +
            "                        \"    display: block;\" +\n" +
            "                        \"    position: absolute;\" +\n" +
            "                        \"    z-index: 10;\" +\n" +
            "                        \"    top: 0px;\" +\n" +
            "                        \"    right: 0px;\" +\n" +
            "                        \"    height: 0px;\" +\n" +
            "                        \"    width: 0px;\" +\n" +
            "                        \"    background-repeat: no-repeat;\" +\n" +
            "                        \"}');\""
}