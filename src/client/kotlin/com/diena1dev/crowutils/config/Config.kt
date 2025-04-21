package com.diena1dev.crowutils.config

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

    //Crow Purple
    val crowPurple = 0xFF147586
}