package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.lang3.ObjectUtils;
import com.google.common.base.Optional;

public class DataWatcher {

    private final Entity a;
    private boolean b = true;
    // Spigot Start
    private static final gnu.trove.map.TObjectIntMap classToId = new gnu.trove.map.hash.TObjectIntHashMap( 10, 0.5f, -1 );
    private final gnu.trove.map.TIntObjectMap dataValues = new gnu.trove.map.hash.TIntObjectHashMap( 10, 0.5f, -1 );
    // These exist as an attempt at backwards compatability for (broken) NMS plugins
    private static final Map<Class<?>, Integer> c = gnu.trove.TDecorators.wrap( classToId );
    private final Map<Integer, DataWatcher.WatchableObject> d = gnu.trove.TDecorators.wrap( dataValues );
    // Spigot End
    private boolean e;
    private ReadWriteLock f = new ReentrantReadWriteLock();
	private static final Map<Class<? extends Entity>, Integer> dataTypes = Maps.newHashMap();

    public DataWatcher(Entity entity) {
        this.a = entity;
    }

    public static <Y> DataIndex<Y> getIndex(Class<? extends Entity> class_, DataTypeInferface<Y> kf2)
	{
		int n2;
		if (dataTypes.containsKey(class_))
		{
			n2 = dataTypes.get(class_) + 1;
		}
		else
		{
			int n3 = 0;
			Class<? extends Entity> class_2 = class_;
			while (class_2 != Entity.class)
			{
				if (!dataTypes.containsKey(class_2 = (Class<? extends Entity>) class_2.getSuperclass()))
					continue;
				n3 = dataTypes.get(class_2) + 1;
				break;
			}
			n2 = n3;
		}
		if (n2 > 254)
		{
			throw new IllegalArgumentException("Data value id is too big with " + n2 + "! (Max is " + 254 + ")");
		}
		dataTypes.put(class_, n2);
		return kf2.fromIndex(n2);
	}

    public <T, Y> void a(int i, T t0, DataIndex<Y> index, Y newValue) {
        int integer = classToId.get(t0.getClass()); // Spigot

        if (integer == -1) { // Spigot
            throw new IllegalArgumentException("Unknown data type: " + t0.getClass());
        } else if (i > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 31 + ")");
        } else if (this.dataValues.containsKey(i)) { // Spigot
            throw new IllegalArgumentException("Duplicate id value for " + i + "!");
        } else {
            DataWatcher.WatchableObject datawatcher_watchableobject = new DataWatcher.WatchableObject(integer, i, t0, index, newValue); // Spigot

            this.f.writeLock().lock();
            this.dataValues.put(i, datawatcher_watchableobject); // Spigot
            this.f.writeLock().unlock();
            this.b = false;
        }
    }

    public <Y> void add(int i, int j, DataIndex<Y> index, Y newValue) {
        DataWatcher.WatchableObject datawatcher_watchableobject = new DataWatcher.WatchableObject(j, i, (Object) null, index, newValue);

        this.f.writeLock().lock();
        this.dataValues.put(i, datawatcher_watchableobject); // Spigot
        this.f.writeLock().unlock();
        this.b = false;
    }

    public byte getByte(int i) {
        return ((Byte) this.j(i).b()).byteValue();
    }

    public short getShort(int i) {
        return ((Short) this.j(i).b()).shortValue();
    }

    public int getInt(int i) {
        return ((Integer) this.j(i).b()).intValue();
    }

    public float getFloat(int i) {
        return ((Float) this.j(i).b()).floatValue();
    }

    public String getString(int i) {
        return (String) this.j(i).b();
    }

    public ItemStack getItemStack(int i) {
        return (ItemStack) this.j(i).b();
    }

    private DataWatcher.WatchableObject j(int i) {
        this.f.readLock().lock();

        DataWatcher.WatchableObject datawatcher_watchableobject;

        try {
            datawatcher_watchableobject = (DataWatcher.WatchableObject) this.dataValues.get(i); // Spigot
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Getting synched entity data");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Synched entity data");

            crashreportsystemdetails.a("Data ID", (Object) Integer.valueOf(i));
            throw new ReportedException(crashreport);
        }

        this.f.readLock().unlock();
        return datawatcher_watchableobject;
    }

    public Vector3f h(int i) {
        return (Vector3f) this.j(i).b();
    }

    public <T, Y> void watch(int i, T t0, DataIndex<Y> index, Y newValue) {
        DataWatcher.WatchableObject datawatcher_watchableobject = this.j(i);

        if (ObjectUtils.notEqual(t0, datawatcher_watchableobject.b()) || ObjectUtils.notEqual(newValue, datawatcher_watchableobject.getValue()))
        {
            datawatcher_watchableobject.a(t0, newValue);
            this.a.i(i);
            datawatcher_watchableobject.a(true);
            this.e = true;
        }

    }

    public void update(int i) {
        this.j(i).d = true;
        this.e = true;
    }

    public boolean a() {
        return this.e;
    }

    public static void a(List<DataWatcher.WatchableObject> list, PacketDataSerializer packetdataserializer) throws IOException {
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                DataWatcher.WatchableObject datawatcher_watchableobject = (DataWatcher.WatchableObject) iterator.next();

                a(packetdataserializer, datawatcher_watchableobject);
            }
        }

        if (packetdataserializer.version == 47)
            packetdataserializer.writeByte(127);
        else
            packetdataserializer.writeByte(255);
    }

    public List<DataWatcher.WatchableObject> b() {
        ArrayList arraylist = null;

        if (this.e) {
            this.f.readLock().lock();
            Iterator iterator = this.dataValues.valueCollection().iterator(); // Spigot

            while (iterator.hasNext()) {
                DataWatcher.WatchableObject datawatcher_watchableobject = (DataWatcher.WatchableObject) iterator.next();

                if (datawatcher_watchableobject.d()) {
                    datawatcher_watchableobject.a(false);
                    if (arraylist == null) {
                        arraylist = Lists.newArrayList();
                    }

                   // Spigot start - copy ItemStacks to prevent ConcurrentModificationExceptions
                    if ( datawatcher_watchableobject.b() instanceof ItemStack )
                    {
                        datawatcher_watchableobject = new WatchableObject(
                                datawatcher_watchableobject.c(),
                                datawatcher_watchableobject.a(),
                                ( (ItemStack) datawatcher_watchableobject.b() ).cloneItemStack()
                                , datawatcher_watchableobject.getIndex(), datawatcher_watchableobject.getValue()
                        );
                    }
                    // Spigot end

                    arraylist.add(datawatcher_watchableobject);
                }
            }

            this.f.readLock().unlock();
        }

        this.e = false;
        return arraylist;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.f.readLock().lock();
        Iterator iterator = this.dataValues.valueCollection().iterator(); // Spigot

        while (iterator.hasNext()) {
            DataWatcher.WatchableObject datawatcher_watchableobject = (DataWatcher.WatchableObject) iterator.next();

            a(packetdataserializer, datawatcher_watchableobject);
        }

        this.f.readLock().unlock();
        if (packetdataserializer.version == 47)
            packetdataserializer.writeByte(127);
        else
            packetdataserializer.writeByte(255);
    }

    public List<DataWatcher.WatchableObject> c() {
        ArrayList arraylist = Lists.newArrayList(); // Spigot

        this.f.readLock().lock();

        arraylist.addAll(this.dataValues.valueCollection()); // Spigot
        // Spigot start - copy ItemStacks to prevent ConcurrentModificationExceptions
        for ( int i = 0; i < arraylist.size(); i++ )
        {
            WatchableObject watchableobject = (WatchableObject) arraylist.get( i );
            if ( watchableobject.b() instanceof ItemStack )
            {
                ItemStack itemStack = ( (ItemStack) watchableobject.b() ).cloneItemStack();
                Object obj2 = watchableobject.getValue();

                if (watchableobject.getValue() instanceof ItemStack)
                {
                    obj2 = ( (ItemStack) watchableobject.getValue() ).cloneItemStack();
                }
                else if (watchableobject.getValue() instanceof Optional && ((Optional) watchableobject.getValue()).get() instanceof ItemStack)
                {
                    obj2 = Optional.of(((Optional<ItemStack>) watchableobject.getValue()).get().cloneItemStack());
                }
                watchableobject = new WatchableObject(
                        watchableobject.c(),
                        watchableobject.a(), itemStack, watchableobject.getIndex(), obj2
                );
                arraylist.set( i, watchableobject );
            }
        }
        // Spigot end

        this.f.readLock().unlock();
        return arraylist;
    }

    private static void a(PacketDataSerializer packetdataserializer, DataWatcher.WatchableObject datawatcher_watchableobject) throws IOException {
        if (packetdataserializer.version != 47)
        {
            DataIndex ke2 = datawatcher_watchableobject.getIndex();
            int n2 = DataType.b(ke2.b());
            if (n2 < 0)
            {
                throw new IllegalArgumentException("Unknown serializer type " + ke2.b());
            }
            packetdataserializer.writeByte(ke2.a());
            packetdataserializer.b(n2);
            ke2.b().write(packetdataserializer, datawatcher_watchableobject.getValue());
            return;
        }

        if (datawatcher_watchableobject.a() < 0)
            return;

        int i = (datawatcher_watchableobject.c() << 5 | datawatcher_watchableobject.a() & 31) & 255;

        packetdataserializer.writeByte(i);
        switch (datawatcher_watchableobject.c()) {
        case 0:
            packetdataserializer.writeByte(((Byte) datawatcher_watchableobject.b()).byteValue());
            break;

        case 1:
            packetdataserializer.writeShort(((Short) datawatcher_watchableobject.b()).shortValue());
            break;

        case 2:
            packetdataserializer.writeInt(((Integer) datawatcher_watchableobject.b()).intValue());
            break;

        case 3:
            packetdataserializer.writeFloat(((Float) datawatcher_watchableobject.b()).floatValue());
            break;

        case 4:
            packetdataserializer.a((String) datawatcher_watchableobject.b());
            break;

        case 5:
            ItemStack itemstack = (ItemStack) datawatcher_watchableobject.b();

            packetdataserializer.a(itemstack);
            break;

        case 6:
            BlockPosition blockposition = (BlockPosition) datawatcher_watchableobject.b();

            packetdataserializer.writeInt(blockposition.getX());
            packetdataserializer.writeInt(blockposition.getY());
            packetdataserializer.writeInt(blockposition.getZ());
            break;

        case 7:
            Vector3f vector3f = (Vector3f) datawatcher_watchableobject.b();

            packetdataserializer.writeFloat(vector3f.getX());
            packetdataserializer.writeFloat(vector3f.getY());
            packetdataserializer.writeFloat(vector3f.getZ());
        }

    }

    public static List<DataWatcher.WatchableObject> b(PacketDataSerializer packetdataserializer) throws IOException {
        ArrayList arraylist = null;

//        for (byte b0 = packetdataserializer.readByte(); b0 != 127; b0 = packetdataserializer.readByte()) {
//            if (arraylist == null) {
//                arraylist = Lists.newArrayList();
//            }
//
//            int i = (b0 & 224) >> 5;
//            int j = b0 & 31;
//            DataWatcher.WatchableObject datawatcher_watchableobject = null;
//
//            switch (i) {
//            case 0:
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, Byte.valueOf(packetdataserializer.readByte()));
//                break;
//
//            case 1:
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, Short.valueOf(packetdataserializer.readShort()));
//                break;
//
//            case 2:
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, Integer.valueOf(packetdataserializer.readInt()));
//                break;
//
//            case 3:
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, Float.valueOf(packetdataserializer.readFloat()));
//                break;
//
//            case 4:
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, packetdataserializer.c(32767));
//                break;
//
//            case 5:
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, packetdataserializer.i());
//                break;
//
//            case 6:
//                int k = packetdataserializer.readInt();
//                int l = packetdataserializer.readInt();
//                int i1 = packetdataserializer.readInt();
//
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, new BlockPosition(k, l, i1));
//                break;
//
//            case 7:
//                float f = packetdataserializer.readFloat();
//                float f1 = packetdataserializer.readFloat();
//                float f2 = packetdataserializer.readFloat();
//
//                datawatcher_watchableobject = new DataWatcher.WatchableObject(i, j, new Vector3f(f, f1, f2));
//            }
//
//            arraylist.add(datawatcher_watchableobject);
//        }

        return arraylist;
    }

    public boolean d() {
        return this.b;
    }

    public void e() {
        this.e = false;
    }

    static {
        // Spigot Start - remove valueOf
        classToId.put(Byte.class, 0);
        classToId.put(Short.class, 1);
        classToId.put(Integer.class, 2);
        classToId.put(Float.class, 3);
        classToId.put(String.class, 4);
        classToId.put(ItemStack.class, 5);
        classToId.put(BlockPosition.class, 6);
        classToId.put(Vector3f.class, 7);
        // Spigot End
    }

    public static class WatchableObject<Y> {

        private final int a;
        private final int b;
        private Object c;
        private boolean d;
        private DataIndex<Y> index;
        private Y value;

        public WatchableObject(int type, int oldIndex, Object object, DataIndex<Y> index, Y newValue) {
            this.b = oldIndex;
            this.c = object;
            this.a = type;
            this.d = true;
            this.index = index;
            this.value = newValue;
        }

        public int a() {
            return this.b;
        }

        public DataIndex<Y> getIndex()
		{
			return this.index;
		}

        public void a(Object object, Y newValue) {
            this.c = object;
            this.value = newValue;
        }

        public Y getValue()
        {
            return value;
        }

        public Object b() {
            return this.c;
        }

        public int c() {
            return this.a;
        }

        public boolean d() {
            return this.d;
        }

        public void a(boolean flag) {
            this.d = flag;
        }
    }
}
