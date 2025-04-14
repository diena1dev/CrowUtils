package com.diena1dev.crowutils.config

import org.lwjgl.glfw.GLFW

@Suppress("unused")
class Config() {
    // Default Keybinds
    val openMenu = GLFW.GLFW_KEY_X
    val openSettings = GLFW.GLFW_KEY_V // Debug Menu for now....
    val toggleHUD = GLFW.GLFW_KEY_H

    var isHUDToggled = false

    // Default Browser Settings
    var webHomePage = "https://survival.horizonsend.net"
    var webTransparency = true
    var webLayer: Float = 1f
    var webOffsetHorizontal = 1
    var webOffsetVertical = 1

    var webHUDEnabled = false
    var webHUDMapScale = 1
    var webHUDHorizontal = 1
    var webHUDVertical = 1

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