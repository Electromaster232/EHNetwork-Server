package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInArmAnimation implements Packet<PacketListenerPlayIn> {

    public long timestamp; // Spigot

    public PacketPlayInArmAnimation() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        timestamp = System.currentTimeMillis(); // Spigot
        if (packetdataserializer.version != 47)
        {
            packetdataserializer.e();
        }
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }
}
