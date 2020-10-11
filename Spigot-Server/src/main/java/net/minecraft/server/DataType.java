package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

import com.google.common.base.Optional;
import com.mineplex.chunk.PaletteMediumList;

public class DataType
{
	private static final PaletteMediumList<DataTypeInferface<?>> dataTypes = new PaletteMediumList<DataTypeInferface<?>>(16);

	public static final DataTypeInferface<Byte> BYTE = new DataTypeInferface<Byte>()
	{

		@Override
		public void write(PacketDataSerializer em2, Byte by)
		{
			em2.writeByte(by.byteValue());
		}

		@Override
		public DataIndex<Byte> fromIndex(int n2)
		{
			return new DataIndex<Byte>(n2, this);
		}
	};
	public static final DataTypeInferface<Integer> INTEGER = new DataTypeInferface<Integer>()
	{

		@Override
		public void write(PacketDataSerializer em2, Integer n2)
		{
			em2.b(n2);
		}

		@Override
		public DataIndex<Integer> fromIndex(int n2)
		{
			return new DataIndex<Integer>(n2, this);
		}
	};
	public static final DataTypeInferface<Float> FLOAT = new DataTypeInferface<Float>()
	{

		@Override
		public void write(PacketDataSerializer em2, Float f2)
		{
			em2.writeFloat(f2.floatValue());
		}

		@Override
		public DataIndex<Float> fromIndex(int n2)
		{
			return new DataIndex<Float>(n2, this);
		}
	};
	public static final DataTypeInferface<String> STRING = new DataTypeInferface<String>()
	{

		@Override
		public void write(PacketDataSerializer em2, String string)
		{
			em2.a(string);
		}

		@Override
		public DataIndex<String> fromIndex(int n2)
		{
			return new DataIndex<String>(n2, this);
		}
	};
	public static final DataTypeInferface<IChatBaseComponent> ICHATBASECOMPONENT = new DataTypeInferface<IChatBaseComponent>()
	{

		@Override
		public void write(PacketDataSerializer em2, IChatBaseComponent eu2)
		{
			try
			{
				em2.a(eu2);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public DataIndex<IChatBaseComponent> fromIndex(int n2)
		{
			return new DataIndex<IChatBaseComponent>(n2, this);
		}
	};
	public static final DataTypeInferface<Optional<ItemStack>> OPT_ITEMSTACK = new DataTypeInferface<Optional<ItemStack>>()
	{

		@Override
		public void write(PacketDataSerializer em2, Optional<ItemStack> optional)
		{
			em2.a(optional.orNull());
		}

		@Override
		public DataIndex<Optional<ItemStack>> fromIndex(int n2)
		{
			return new DataIndex<Optional<ItemStack>>(n2, this);
		}
	};
	public static final DataTypeInferface<Optional<IBlockData>> OPT_IBLOCKDATA = new DataTypeInferface<Optional<IBlockData>>()
	{

		@Override
		public void write(PacketDataSerializer em2, Optional<IBlockData> optional)
		{
			if (optional.isPresent())
			{
				em2.b(Block.getCombinedId(optional.get()));
			}
			else
			{
				em2.b(0);
			}
		}

		@Override
		public DataIndex<Optional<IBlockData>> fromIndex(int n2)
		{
			return new DataIndex<Optional<IBlockData>>(n2, this);
		}
	};
	public static final DataTypeInferface<Boolean> BOOLEAN = new DataTypeInferface<Boolean>()
	{

		@Override
		public void write(PacketDataSerializer em2, Boolean bl2)
		{
			em2.writeBoolean(bl2);
		}

		@Override
		public DataIndex<Boolean> fromIndex(int n2)
		{
			return new DataIndex<Boolean>(n2, this);
		}
	};
	public static final DataTypeInferface<Vector3f> VECTOR3F = new DataTypeInferface<Vector3f>()
	{

		@Override
		public void write(PacketDataSerializer em2, Vector3f dc2)
		{
			em2.writeFloat(dc2.getX());
			em2.writeFloat(dc2.getY());
			em2.writeFloat(dc2.getZ());
		}

		@Override
		public DataIndex<Vector3f> fromIndex(int n2)
		{
			return new DataIndex<Vector3f>(n2, this);
		}
	};
	public static final DataTypeInferface<BlockPosition> BLOCKPOSITION = new DataTypeInferface<BlockPosition>()
	{

		@Override
		public void write(PacketDataSerializer em2, BlockPosition cj2)
		{
			em2.a(cj2);
		}

		@Override
		public DataIndex<BlockPosition> fromIndex(int n2)
		{
			return new DataIndex<BlockPosition>(n2, this);
		}
	};
	public static final DataTypeInferface<Optional<BlockPosition>> OPT_BLOCKPOSITION = new DataTypeInferface<Optional<BlockPosition>>()
	{

		@Override
		public void write(PacketDataSerializer em2, Optional<BlockPosition> optional)
		{
			em2.writeBoolean(optional.isPresent());
			if (optional.isPresent())
			{
				em2.a(optional.get());
			}
		}

		@Override
		public DataIndex<Optional<BlockPosition>> fromIndex(int n2)
		{
			return new DataIndex<Optional<BlockPosition>>(n2, this);
		}
	};
	public static final DataTypeInferface<EnumDirection> ENUM_DIRECTION = new DataTypeInferface<EnumDirection>()
	{

		@Override
		public void write(PacketDataSerializer em2, EnumDirection cq2)
		{
			em2.a(cq2);
		}

		@Override
		public DataIndex<EnumDirection> fromIndex(int n2)
		{
			return new DataIndex<EnumDirection>(n2, this);
		}
	};
	public static final DataTypeInferface<Optional<UUID>> OPT_UUID = new DataTypeInferface<Optional<UUID>>()
	{

		@Override
		public void write(PacketDataSerializer em2, Optional<UUID> optional)
		{
			em2.writeBoolean(optional.isPresent());
			if (optional.isPresent())
			{
				em2.a(optional.get());
			}
		}

		@Override
		public DataIndex<Optional<UUID>> fromIndex(int n2)
		{
			return new DataIndex<Optional<UUID>>(n2, this);
		}
	};

	public static void a(DataTypeInferface<?> kf2)
	{
		dataTypes.c(kf2);
	}

	public static DataTypeInferface<?> a(int n2)
	{
		return dataTypes.get(n2);
	}

	public static int b(DataTypeInferface<?> kf2)
	{
		return dataTypes.a(kf2);
	}

	static
	{
		DataType.a(BYTE);
		DataType.a(INTEGER);
		DataType.a(FLOAT);
		DataType.a(STRING);
		DataType.a(ICHATBASECOMPONENT);
		DataType.a(OPT_ITEMSTACK);
		DataType.a(BOOLEAN);
		DataType.a(VECTOR3F);
		DataType.a(BLOCKPOSITION);
		DataType.a(OPT_BLOCKPOSITION);
		DataType.a(ENUM_DIRECTION);
		DataType.a(OPT_UUID);
		DataType.a(OPT_IBLOCKDATA);
	}

}