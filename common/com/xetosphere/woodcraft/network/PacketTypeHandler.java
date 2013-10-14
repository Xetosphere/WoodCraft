package com.xetosphere.woodcraft.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.xetosphere.woodcraft.lib.Reference;
import com.xetosphere.woodcraft.network.packet.PacketTileUpdate;
import com.xetosphere.woodcraft.network.packet.PacketTileWithItemUpdate;
import com.xetosphere.woodcraft.network.packet.PacketWC;

public enum PacketTypeHandler {
	TILE(PacketTileUpdate.class),
	TILE_WITH_ITEM(PacketTileWithItemUpdate.class);

	private Class<? extends PacketWC> clazz;

	PacketTypeHandler(Class<? extends PacketWC> clazz) {

		this.clazz = clazz;
	}

	public static PacketWC buildPacket(byte[] data) {

		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		PacketWC packet = null;

		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}

	public static PacketWC buildPacket(PacketTypeHandler type) {

		PacketWC packet = null;

		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return packet;
	}

	public static Packet populatePacket(PacketWC packetWC) {

		byte[] data = packetWC.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetWC.isChunkDataPacket;

		return packet250;
	}

}
