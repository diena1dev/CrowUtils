package com.diena1dev.crowutils.menus

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

@Suppress("unused")
class BrowserScreen(title: Text) : Screen(
    title
) {
    val gameInstance: MinecraftClient = MinecraftClient.getInstance()
    val startURL: String = "google.com"
    val webVerticalOffset: Int = 20
    val webHorizontalOffset: Int = 10
}