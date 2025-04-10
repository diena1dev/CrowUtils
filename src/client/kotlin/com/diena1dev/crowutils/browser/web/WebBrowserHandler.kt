package com.diena1dev.crowutils.browser.web

import com.cinemamod.mcef.MCEF
import com.cinemamod.mcef.MCEFBrowser
import org.cef.misc.CefCursorType

@Suppress("unused")
object WebBrowserHandler {
    lateinit var webBrowser: MCEFBrowser
    lateinit var weburl: String

    fun init() {
        if (!MCEF.isInitialized()) {
            weburl = "https://survival.horizonsend.net"
            MCEF.initialize()
            webBrowser = MCEF.createBrowser(weburl, true)
        }
    }

    fun refreshBrowser() {
        //if (webBrowser != null) { webBrowser.reload() }
        webBrowser.reload()
    }

    fun resizeBrowser(x: Int, y: Int) {
        if (webBrowser != null) { webBrowser.resize(x, y) }
    }
}

