package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInLeftClick implements Packet<PacketListenerPlayIn> {

    public PacketPlayInLeftClick() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }
}