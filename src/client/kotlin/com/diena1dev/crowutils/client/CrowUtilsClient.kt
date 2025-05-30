package com.diena1dev.crowutils.client

import com.diena1dev.crowutils.client.networking.Packets
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.client.MinecraftClient

import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.client.render.RenderPhase
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.packet.CustomPayload
import net.minecraft.text.Text
import net.minecraft.util.Identifier

val gameInstance: MinecraftClient = MinecraftClient.getInstance()

@Suppress("unused")
class CrowUtilsClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeybindHandler.init()
        HudLayerRegistrationCallback.EVENT.register { layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.MISC_OVERLAYS, RenderHandler.HUDLayer()) }

        for (packet in Packets.entries) {
            println("Registering packet ${packet.handler.id}.")

            //PayloadTypeRegistry.playS2C().register(MyCustomPayload.PACKET_ID, MyCustomPayload.CODEC); TODO
            /*ClientPlayNetworking.registerGlobalReceiver<CUSTOM_CHANNEL>(client, _, buf, _ ->
                // Read the data (must match server write format!)
                val message = buf.readUtf() // or `readString()` in Java

            // Run on client thread (safe UI update, etc.)
            gameInstance.execute {
                gameInstance.player?.sendMessage(Text.literal("Received from server: $message"), true)
            }*/
        }
    }
}

fun id(s: String) = Identifier.of("ion", s)
