package com.diena1dev.crowutils.client.networking.packets

import com.diena1dev.crowutils.client.networking.IonPacketHandler
import com.diena1dev.crowutils.client.networking.Packets
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import org.apache.logging.log4j.core.config.plugins.util.PluginManager


// Handshake receives a packet from the server, sent on player join to validate that they are a Void user.
// If they are a user, it will continue by sending data to the client, like REI entries.
// This prevents unnecessary usage of network bandwidth by sending junk packets to every client, when only one or
// two will be able to receive and use the packet. TODO
object HandshakePacket: IonPacketHandler() {
    override val name = "handshake"
    override fun s2c(
        client: MinecraftClient,
        handler: ClientPlayNetworkHandler,
        buf: PacketByteBuf,
        responseSender: PacketSender
    ) {
        MinecraftClient.getInstance().send {
            Packets.HANDSHAKE
        }
    }
}