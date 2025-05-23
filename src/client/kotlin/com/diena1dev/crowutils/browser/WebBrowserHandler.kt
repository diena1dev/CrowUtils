package com.diena1dev.crowutils.browser

import com.cinemamod.mcef.MCEF
import com.cinemamod.mcef.MCEFBrowser
import com.diena1dev.crowutils.client.gameInstance
import com.diena1dev.crowutils.config.Config
import net.minecraft.network.listener.ClientPacketListener
import org.apache.logging.log4j.LogManager


@Suppress("unused")
object WebBrowserHandler {
    lateinit var webBrowser: MCEFBrowser
    var c: Config = Config
    val logger = LogManager.getLogger()

    fun init() {
        if (!this::webBrowser.isInitialized && MCEF.isInitialized()) {
            webBrowser = MCEF.createBrowser(Config.webHomePage, true)
            webBrowser.client.addJSDialogHandler(c.JSReaderHack)
        }
    }

    fun refreshBrowser() {
        if (this::webBrowser.isInitialized) {
            c.hasInjectedCSS = false
            webBrowser.reload()
        }
    }

    fun resizeBrowserAuto() { // resizes intelligently to match window resolution
        if (this::webBrowser.isInitialized) {
            webBrowser.resize(
                gameInstance.window.width,
                gameInstance.window.height
            )
        }
    }

    fun resizeBrowserPrecise(x: Int, y: Int) { // resizes with the **exact** given x and y
        if (this::webBrowser.isInitialized) {
            webBrowser.resize(x, y)
        }
    }

    fun isBrowserInit(): Boolean { // function to check if the browser was initialized
        var result: Boolean
        if (this::webBrowser.isInitialized) {
            result = true
        } else {
            result = false
        }
        return result
    }

    fun injectCSS() { // injection of a custom theme for the horizon's end dynmap, shrinking the worlds list substantially
        if (!c.hasInjectedCSS && isBrowserInit()) {
            c.hasInjectedCSS = true
            webBrowser.executeJavaScript(c.CSSThemeScript, webBrowser.url, 1
            )
        }
    }

    fun onJumpClick() { // executes dialog popup for JSReaderHack to pickup
        webBrowser.executeJavaScript("window.alert(document.querySelector(\".coord-control-value\").textContent + \" logPointer\");", webBrowser.url, 100)
    }

    fun extractCoords(coords: String): String? {
        val regex = Regex("""(\d+),\s*(\d+).*logPointer""")
        val match = regex.find(coords)
        return match?.let { "jump ${it.groupValues[1]} ${it.groupValues[2]}" }
    }

    fun sendCommand(text: String?) {
        gameInstance.networkHandler?.sendCommand(text)
    }
}