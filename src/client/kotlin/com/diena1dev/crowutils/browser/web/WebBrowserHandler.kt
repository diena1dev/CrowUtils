package com.diena1dev.crowutils.browser.web

import com.cinemamod.mcef.MCEF
import com.cinemamod.mcef.MCEFBrowser

@Suppress("unused")
object WebBrowserHandler {
    lateinit var webBrowser: MCEFBrowser
    lateinit var weburl: String

    fun init() {
        if (!MCEF.isInitialized()) {
            weburl = "https://wiki.horizonsend.net"
            MCEF.initialize()
            webBrowser = MCEF.createBrowser(weburl, true)
        } else { println("[WARN] WebBrowser is not Initialized!") }
    }

    fun refreshBrowser() {
        //if (webBrowser != null) { webBrowser.reload() }
        webBrowser.reload()
    }

    fun resizeBrowser(x: Int, y: Int) {
        if (webBrowser != null) { webBrowser.resize(x, y) }
    }
}

