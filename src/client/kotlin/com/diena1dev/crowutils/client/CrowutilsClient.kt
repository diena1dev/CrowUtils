package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.client.init.InitHandler
import net.fabricmc.api.ClientModInitializer

@Suppress("unused")
class CrowUtilsClient : ClientModInitializer {

    override fun onInitializeClient() {
        InitHandler.clientInit()
        InitHandler.browserInit()
        InitHandler.configInit()
        InitHandler.keybindInit()
        InitHandler.menuInit()
    }
}
