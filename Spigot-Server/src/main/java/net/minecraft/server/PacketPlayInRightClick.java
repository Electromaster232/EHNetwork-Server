package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInRightClick implements Packet<PacketListenerPlayIn> {

    public PacketPlayInRightClick() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }
}