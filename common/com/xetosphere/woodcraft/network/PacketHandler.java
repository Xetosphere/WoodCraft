package com.xetosphere.woodcraft.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.xetosphere.woodcraft.network.packet.PacketWC;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

		PacketWC packetWC = PacketTypeHandler.buildPacket(packet.data);

		packetWC.execute(manager, player);
	}

}
