package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class PacketPlayOutMapChunk implements Packet<PacketListenerPlayOut> {

    public int a;
    public int b;
    public PacketPlayOutMapChunk.ChunkMap c;
    public boolean d;
    public Chunk mapChunk;

    public int size9;
    public byte[] bytes9;

    public PacketPlayOutMapChunk() {}

    public PacketPlayOutMapChunk(Chunk chunk, boolean flag, int i) {
        this.a = chunk.locX;
        this.b = chunk.locZ;
        this.d = flag;

        boolean world = !chunk.getWorld().worldProvider.o();

        this.c = a(chunk, flag, world, i);
        chunk.world.spigotConfig.antiXrayInstance.obfuscateSync(chunk.locX, chunk.locZ, c.b, c.a, chunk.world);
        mapChunk = chunk;

        this.bytes9 = new byte[a9(chunk, flag, world, i)];
        this.size9 = a9(new PacketDataSerializer(this.f()), chunk, flag, world, i);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readInt();
        this.d = packetdataserializer.readBoolean();
        this.c = new PacketPlayOutMapChunk.ChunkMap();
        this.c.b = packetdataserializer.readShort();
        this.c.a = packetdataserializer.a();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeBoolean(this.d);

        if (packetdataserializer.version == 47){
        packetdataserializer.writeShort((short) (this.c.b & '\uffff'));
        packetdataserializer.a(this.c.a);
        } else
        {
            packetdataserializer.b(this.size9);
            packetdataserializer.b(this.bytes9.length);
            packetdataserializer.writeBytes(this.bytes9);
        }
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    protected static int a(int i, boolean flag, boolean flag1) {
        int j = i * 2 * 16 * 16 * 16;
        int k = i * 16 * 16 * 16 / 2;
        int l = flag ? i * 16 * 16 * 16 / 2 : 0;
        int i1 = flag1 ? 256 : 0;

        return j + k + l + i1;
    }

    public static PacketPlayOutMapChunk.ChunkMap a(Chunk chunk, boolean flag, boolean flag1, int i) {
        ChunkSection[] achunksection = chunk.getSections();
        PacketPlayOutMapChunk.ChunkMap packetplayoutmapchunk_chunkmap = new PacketPlayOutMapChunk.ChunkMap();
        ArrayList arraylist = Lists.newArrayList();

        int j;

        for (j = 0; j < achunksection.length; ++j) {
            ChunkSection chunksection = achunksection[j];

            if (chunksection != null && (!flag || !chunksection.a()) && (i & 1 << j) != 0) {
                packetplayoutmapchunk_chunkmap.b |= 1 << j;
                arraylist.add(chunksection);
            }
        }

        packetplayoutmapchunk_chunkmap.a = new byte[a(Integer.bitCount(packetplayoutmapchunk_chunkmap.b), flag1, flag)];
        j = 0;
        Iterator iterator = arraylist.iterator();

        ChunkSection chunksection1;

        while (iterator.hasNext()) {
            chunksection1 = (ChunkSection) iterator.next();
            char[] achar = chunksection1.getIdArray();
            char[] achar1 = achar;
            int k = achar.length;

            for (int l = 0; l < k; ++l) {
                char c0 = achar1[l];

                packetplayoutmapchunk_chunkmap.a[j++] = (byte) (c0 & 255);
                packetplayoutmapchunk_chunkmap.a[j++] = (byte) (c0 >> 8 & 255);
            }
        }

        for (iterator = arraylist.iterator(); iterator.hasNext(); j = a(chunksection1.getEmittedLightArray().a(), packetplayoutmapchunk_chunkmap.a, j)) {
            chunksection1 = (ChunkSection) iterator.next();
        }

        if (flag1) {
            for (iterator = arraylist.iterator(); iterator.hasNext(); j = a(chunksection1.getSkyLightArray().a(), packetplayoutmapchunk_chunkmap.a, j)) {
                chunksection1 = (ChunkSection) iterator.next();
            }
        }

        if (flag) {
            a(chunk.getBiomeIndex(), packetplayoutmapchunk_chunkmap.a, j);
        }

        return packetplayoutmapchunk_chunkmap;
    }

    private static int a(byte[] abyte, byte[] abyte1, int i) {
        System.arraycopy(abyte, 0, abyte1, i, abyte.length);
        return i + abyte.length;
    }

    public static class ChunkMap {

        public byte[] a;
        public int b;

        public ChunkMap() {}
    }

    private ByteBuf f() {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(this.bytes9);
        byteBuf.writerIndex(0);
        return byteBuf;
    }

    public static int a9(PacketDataSerializer dataSerializer, Chunk chunk, boolean bl, boolean bl2, int n) {
        int n2 = 0;
        ChunkSection[] sectionArray = chunk.getSections();

        for (int i = 0; i < sectionArray.length; ++i) {
            ChunkSection section = sectionArray[i];
            if (section == null || bl && section.a()) continue;
            if ((n & 1 << i) == 0) continue;
            n2|=1 << i;

            section.writeSerializer(dataSerializer);

            dataSerializer.writeBytes(section.getEmittedLightArray().a());
            if (!bl2) continue;
            dataSerializer.writeBytes(section.getSkyLightArray().a());
        }
        if (bl) {
            dataSerializer.writeBytes(chunk.getBiomeIndex());
        }
        return n2;
    }

    protected static int a9(Chunk chunk, boolean bl, boolean bl2, int n) {
        int n2 = 0;
        ChunkSection[] sectionArray = chunk.getSections();

        for (int i = 0; i < sectionArray.length; ++i) {
            ChunkSection section = sectionArray[i];
            if (section == null || bl && section.a()) continue;
            if ((n & 1 << i) == 0) continue;

            n2 += section.getLength();

            n2+=section.getEmittedLightArray().a().length;
            if (!bl2) continue;
            n2+=section.getSkyLightArray().a().length;
        }
        if (bl) {
            n2+=chunk.getBiomeIndex().length;
        }
        return n2;
    }
}
