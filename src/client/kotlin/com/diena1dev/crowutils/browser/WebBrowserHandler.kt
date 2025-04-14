package com.diena1dev.crowutils.browser

import com.cinemamod.mcef.MCEF
import com.cinemamod.mcef.MCEFBrowser
import com.diena1dev.crowutils.config.Config

@Suppress("unused")
object WebBrowserHandler {
    lateinit var webBrowser: MCEFBrowser

    fun init() {
        if (!MCEF.isInitialized()) {
            MCEF.shutdown()
            MCEF.initialize()
            webBrowser = MCEF.createBrowser(Config().webHomePage, true)
        } else if (!this::webBrowser.isInitialized && MCEF.isInitialized()) {
            webBrowser = MCEF.createBrowser(Config().webHomePage, true)
        }
    }

    fun refreshBrowser() {
        if (this::webBrowser.isInitialized) { webBrowser.reload() }
    }

    fun resizeBrowser(x: Int, y: Int) {
        if (this::webBrowser.isInitialized) { webBrowser.resize(x, y) }
    }

    fun isBrowserInit(): Boolean {
        var result: Boolean

        if (this::webBrowser.isInitialized) {
            result = true
        } else {
            result = false
        }

        return result
    }
}