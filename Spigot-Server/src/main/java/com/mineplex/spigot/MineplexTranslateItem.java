package com.mineplex.spigot;

import java.lang.reflect.Field;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

import com.google.common.base.Objects;

import net.minecraft.server.ChatComponentText;
import net.minecraft.server.ChatMessage;
import net.minecraft.server.EntityTypes;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import net.minecraft.server.PacketDataSerializer;

public class MineplexTranslateItem
{
	private static final String[] potionNames = new String[128];
	private static Map<String, Integer> entityTypes;

	public static IChatBaseComponent convertChat(IChatBaseComponent eu2) throws Exception
	{
		Object[] arrobject = null;
		IChatBaseComponent eu3 = null;

		if (eu2 instanceof ChatComponentText)
		{
			eu3 = new ChatComponentText(((ChatComponentText) eu2).g());
		}
		else if (eu2 instanceof ChatMessage)
		{
			arrobject = ((ChatMessage) eu2).j();
			for (int i2 = 0; i2 < arrobject.length; ++i2)
			{
				Object object = arrobject[i2];
				if (!(object instanceof IChatBaseComponent))
					continue;
				arrobject[i2] = convertChat((IChatBaseComponent) object);
			}
			eu3 = new ChatMessage(((ChatMessage) eu2).i(), arrobject);
		}
		else
		{
			return eu2;
		}

		for (IChatBaseComponent eu4 : eu2.a())
		{
			eu3.addSibling(convertChat(eu4));
		}
		return eu3;
	}

	public static ItemStack receiveItemStack(int id, byte amount, short data, NBTTagCompound nbt)
	{
		ItemStack itemstack = new ItemStack(Item.getById(id == 438 ? 373 : id), amount, data);
		itemstack.setTag(nbt);

		switch (id)
		{
		case 383:
			if (nbt == null)
			{
				break;
			}

			if (!nbt.hasKey("EntityTag"))
			{
				break;
			}

			if (!((NBTTagCompound) nbt.get("EntityTag")).hasKey("id"))
			{
				break;
			}

			Integer entId = entityTypes.get(((NBTTagCompound) nbt.get("EntityTag")).getString("id"));

			itemstack.setData(entId);
			break;
		case 373:
			itemstack = receivePotion(itemstack, false);
			break;
		case 438:
			itemstack = receivePotion(itemstack, true);
			break;
		default:
			break;
		}

		return itemstack;
	}

	public static void sendItemStack(PacketDataSerializer serializer, ItemStack itemstack)
	{
		if (itemstack == null || itemstack.getItem() == null || serializer.version == 47)
		{
			if (itemstack != null && itemstack.getItem() != null && serializer.version == 47 && Item.getId(itemstack.getItem()) == 443)
                writeItemStack(serializer, new ItemStack(Item.getById(1)));
            else
                writeItemStack(serializer, itemstack);
			return;
		}

		switch (Item.getId(itemstack.getItem()))
		{
		case 383:
			itemstack = itemstack.cloneItemStack();

			CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));

			NBTTagCompound nbt = itemstack.getTag();

			if (nbt == null)
			{
				nbt = new NBTTagCompound();
			}

			String entity = EntityTypes.b(itemstack.getData());

			if (entity == null)
			{
				nbt.remove("EntityTag");
			}
			else
			{
				if (!nbt.hasKey("EntityTag"))
				{
					nbt.set("EntityTag", new NBTTagCompound());
				}

				((NBTTagCompound) nbt.get("EntityTag")).setString("id", entity);
			}

			serializer.writeShort(Material.MONSTER_EGG.getId());
			serializer.writeByte(itemstack.count);
			serializer.writeShort(0);

			serializer.a(nbt);

			return;
		case 373:
			sendPotion(serializer, itemstack.cloneItemStack());
			return;
		default:
			break;
		}

		writeItemStack(serializer, itemstack);
	}

	private static void sendBook(PacketDataSerializer serializer, ItemStack itemstack)
	{
		CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));

		NBTTagCompound nbt = itemstack.getTag();

		if (nbt == null)
		{
			writeItemStack(serializer, itemstack);
			return;
		}

		nbt.remove("generation");
		nbt.setInt("resolved", 1);
		NBTTagList list = nbt.getList("pages", 8);
		NBTTagList newList = new NBTTagList();

		for (int i = 0; i < list.size(); i++)
		{
			String string = list.getString(i);

			if (string.endsWith(",\"text\":\"\"}"))
			{
				string = string.replace(",\"text\":\"\"}", "}").replaceFirst("extra", "text");
			}
			else if (string.startsWith("\""))
				string = "{\"text\":" + string + "}";

			newList.add(new NBTTagString(string));

			/*IChatBaseComponent arg;
			try
			{
				arg = IChatBaseComponent.ChatSerializer.a(string);
				arg = convertChat(arg);
			}
			catch (Exception ex)
			{
				arg = new ChatComponentText(string);
			}

			list.a(i, new NBTTagString(IChatBaseComponent.ChatSerializer.a(arg)));*/
		}
		nbt.set("pages", newList);

		for (String n : nbt.c())
		{
			System.out.print("Key: " + n);
			System.out.print("Value: " + nbt.get(n));

			if (nbt.get(n) instanceof NBTTagList)
			{
				for (int i = 0; i < nbt.getList(n, 8).size(); i++)
				{
					System.out.print("List: " + nbt.getList(n, 8).getString(i));
				}
			}
		}

		serializer.writeShort(Item.getId(itemstack.getItem()));
		serializer.writeByte(itemstack.count);
		serializer.writeShort(itemstack.getData());

		serializer.a(nbt);
	}

	private static void writeItemStack(PacketDataSerializer serializer, ItemStack itemstack)
	{
		if (itemstack == null || itemstack.getItem() == null)
		{ // CraftBukkit - NPE fix itemstack.getItem()
			serializer.writeShort(-1);
		}
		else
		{
			serializer.writeShort(Item.getId(itemstack.getItem()));
			serializer.writeByte(itemstack.count);
			serializer.writeShort(itemstack.getData());
			NBTTagCompound nbttagcompound = null;

			if (itemstack.getItem().usesDurability() || itemstack.getItem().p())
			{
				// Spigot start - filter
				itemstack = itemstack.cloneItemStack();
				CraftItemStack.setItemMeta(itemstack, CraftItemStack.getItemMeta(itemstack));
				// Spigot end
				nbttagcompound = itemstack.getTag();
			}

			serializer.a(nbttagcompound);
		}
	}

	public static void sendPotion(PacketDataSerializer serializer, ItemStack item)
	{
		CraftItemStack.setItemMeta(item, CraftItemStack.getItemMeta(item));

		NBTTagCompound itemNbt = item.getTag();

		if (itemNbt == null)
		{
			itemNbt = new NBTTagCompound();
		}

		int dura = item.getData();
		boolean splash = (dura & 16384) == 16384;

		String string = potionNames[dura & 127];
		itemNbt.setString("Potion", string == null ? "minecraft:water" : string);

		int id = !splash ? 373 : 438;

		serializer.writeShort(id);
		serializer.writeByte(item.count);
		serializer.writeShort(0);

		serializer.a(itemNbt);
	}

	public static ItemStack receivePotion(ItemStack itemstack, boolean splash)
	{
		NBTTagCompound itemNbt = itemstack.getTag();

		if (itemNbt == null || !itemNbt.hasKey("Potion"))
		{
			return itemstack;
		}

		String type = itemNbt.getString("Potion");

		int dura = 0;

		if (type != null && !type.equals("minecraft:water"))
		{
			for (int i = 0; i < potionNames.length; i++)
			{
				String b = potionNames[i];

				if (Objects.equal(b, type))
				{
					dura = i;
					break;
				}
			}
		}

		if (splash)
		{
			dura += 16384;
		}
		else if (dura > 0)
		{
			dura += 8192;
		}

		itemstack.setData(dura);

		return itemstack;
	}

	static
	{
		potionNames[0] = "minecraft:water";
		potionNames[1] = "minecraft:regeneration";
		potionNames[2] = "minecraft:swiftness";
		potionNames[3] = "minecraft:fire_resistance";
		potionNames[4] = "minecraft:poison";
		potionNames[5] = "minecraft:healing";
		potionNames[6] = "minecraft:night_vision";
		potionNames[7] = null;
		potionNames[8] = "minecraft:weakness";
		potionNames[9] = "minecraft:strength";
		potionNames[10] = "minecraft:slowness";
		potionNames[11] = "minecraft:leaping";
		potionNames[12] = "minecraft:harming";
		potionNames[13] = "minecraft:water_breathing";
		potionNames[14] = "minecraft:invisibility";
		potionNames[15] = null;
		potionNames[16] = "minecraft:awkward";
		potionNames[17] = "minecraft:regeneration";
		potionNames[18] = "minecraft:swiftness";
		potionNames[19] = "minecraft:fire_resistance";
		potionNames[20] = "minecraft:poison";
		potionNames[21] = "minecraft:healing";
		potionNames[22] = "minecraft:night_vision";
		potionNames[23] = null;
		potionNames[24] = "minecraft:weakness";
		potionNames[25] = "minecraft:strength";
		potionNames[26] = "minecraft:slowness";
		potionNames[27] = "minecraft:leaping";
		potionNames[28] = "minecraft:harming";
		potionNames[29] = "minecraft:water_breathing";
		potionNames[30] = "minecraft:invisibility";
		potionNames[31] = null;
		potionNames[32] = "minecraft:thick";
		potionNames[33] = "minecraft:strong_regeneration";
		potionNames[34] = "minecraft:strong_swiftness";
		potionNames[35] = "minecraft:fire_resistance";
		potionNames[36] = "minecraft:strong_poison";
		potionNames[37] = "minecraft:strong_healing";
		potionNames[38] = "minecraft:night_vision";
		potionNames[39] = null;
		potionNames[40] = "minecraft:weakness";
		potionNames[41] = "minecraft:strong_strength";
		potionNames[42] = "minecraft:slowness";
		potionNames[43] = "minecraft:strong_leaping";
		potionNames[44] = "minecraft:strong_harming";
		potionNames[45] = "minecraft:water_breathing";
		potionNames[46] = "minecraft:invisibility";
		potionNames[47] = null;
		potionNames[48] = null;
		potionNames[49] = "minecraft:strong_regeneration";
		potionNames[50] = "minecraft:strong_swiftness";
		potionNames[51] = "minecraft:fire_resistance";
		potionNames[52] = "minecraft:strong_poison";
		potionNames[53] = "minecraft:strong_healing";
		potionNames[54] = "minecraft:night_vision";
		potionNames[55] = null;
		potionNames[56] = "minecraft:weakness";
		potionNames[57] = "minecraft:strong_strength";
		potionNames[58] = "minecraft:slowness";
		potionNames[59] = "minecraft:strong_leaping";
		potionNames[60] = "minecraft:strong_harming";
		potionNames[61] = "minecraft:water_breathing";
		potionNames[62] = "minecraft:invisibility";
		potionNames[63] = null;
		potionNames[64] = "minecraft:mundane";
		potionNames[65] = "minecraft:long_regeneration";
		potionNames[66] = "minecraft:long_swiftness";
		potionNames[67] = "minecraft:long_fire_resistance";
		potionNames[68] = "minecraft:long_poison";
		potionNames[69] = "minecraft:healing";
		potionNames[70] = "minecraft:long_night_vision";
		potionNames[71] = null;
		potionNames[72] = "minecraft:long_weakness";
		potionNames[73] = "minecraft:long_strength";
		potionNames[74] = "minecraft:long_slowness";
		potionNames[75] = "minecraft:long_leaping";
		potionNames[76] = "minecraft:harming";
		potionNames[77] = "minecraft:long_water_breathing";
		potionNames[78] = "minecraft:long_invisibility";
		potionNames[79] = null;
		potionNames[80] = "minecraft:awkward";
		potionNames[81] = "minecraft:long_regeneration";
		potionNames[82] = "minecraft:long_swiftness";
		potionNames[83] = "minecraft:long_fire_resistance";
		potionNames[84] = "minecraft:long_poison";
		potionNames[85] = "minecraft:healing";
		potionNames[86] = "minecraft:long_night_vision";
		potionNames[87] = null;
		potionNames[88] = "minecraft:long_weakness";
		potionNames[89] = "minecraft:long_strength";
		potionNames[90] = "minecraft:long_slowness";
		potionNames[91] = "minecraft:long_leaping";
		potionNames[92] = "minecraft:harming";
		potionNames[93] = "minecraft:long_water_breathing";
		potionNames[94] = "minecraft:long_invisibility";
		potionNames[95] = null;
		potionNames[96] = "minecraft:thick";
		potionNames[97] = "minecraft:regeneration";
		potionNames[98] = "minecraft:swiftness";
		potionNames[99] = "minecraft:long_fire_resistance";
		potionNames[100] = "minecraft:poison";
		potionNames[101] = "minecraft:strong_healing";
		potionNames[102] = "minecraft:long_night_vision";
		potionNames[103] = null;
		potionNames[104] = "minecraft:long_weakness";
		potionNames[105] = "minecraft:strength";
		potionNames[106] = "minecraft:long_slowness";
		potionNames[107] = "minecraft:leaping";
		potionNames[108] = "minecraft:strong_harming";
		potionNames[109] = "minecraft:long_water_breathing";
		potionNames[110] = "minecraft:long_invisibility";
		potionNames[111] = null;
		potionNames[112] = null;
		potionNames[113] = "minecraft:regeneration";
		potionNames[114] = "minecraft:swiftness";
		potionNames[115] = "minecraft:long_fire_resistance";
		potionNames[116] = "minecraft:poison";
		potionNames[117] = "minecraft:strong_healing";
		potionNames[118] = "minecraft:long_night_vision";
		potionNames[119] = null;
		potionNames[120] = "minecraft:long_weakness";
		potionNames[121] = "minecraft:strength";
		potionNames[122] = "minecraft:long_slowness";
		potionNames[123] = "minecraft:leaping";
		potionNames[124] = "minecraft:strong_harming";
		potionNames[125] = "minecraft:long_water_breathing";
		potionNames[126] = "minecraft:long_invisibility";
		potionNames[127] = null;

		try
		{
			Field field = EntityTypes.class.getDeclaredField("g");
			field.setAccessible(true);
			entityTypes = (Map) field.get(null);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}