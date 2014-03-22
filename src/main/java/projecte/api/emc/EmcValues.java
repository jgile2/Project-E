package projecte.api.emc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projecte.items.PEItems;

public class EmcValues {

	private static final List<EmcValue> values = new ArrayList<EmcValue>();

	public static void setValueForStack(ItemStack stack, int value, EmcValueType type) {
		EmcValue val = getValueForStack(stack);
		if (val != null)
			values.remove(val);

		values.add(new EmcValue(stack, value, type));
	}

	public static void setValueForOredictName(String name, int value, EmcValueType type) {
		EmcValue val = getValueForOredictName(name);
		if (val != null)
			values.remove(val);

		values.add(new EmcValueOredict(name, value, type));
	}

	public static EmcValue getValueForStack(ItemStack stack) {
		for (EmcValue v : values)
			for (ItemStack s : v.getItems())
				if (s.isItemEqual(stack))
					return v;
		return null;
	}

	public static EmcValue getValueForOredictName(String name) {
		for (EmcValue v : values)
			for (ItemStack s : v.getItems())
				for (ItemStack is : OreDictionary.getOres(name))
					if (s.isItemEqual(is))
						return v;
		return null;
	}

	public static EmcValue[] getEmcValues(double value) {

		List<EmcValue> items = new ArrayList<EmcValue>();

		if (value > 0)
			for (EmcValue v : EmcValues.values)
				if (v.getValue() == value)
					items.add(v);

		return items.toArray(new EmcValue[items.size()]);
	}

	public static ItemStack[] getItemsWithValue(double value) {

		List<ItemStack> items = new ArrayList<ItemStack>();

		if (value > 0)
			for (EmcValue v : EmcValues.values)
				if (v.getValue() == value)
					for (ItemStack i : v.getItems())
						items.add(i);

		return items.toArray(new ItemStack[items.size()]);
	}

	public static ItemStack[] getExactDividersForValue(double value) {

		List<EmcValue> values = new ArrayList<EmcValue>();
		List<ItemStack> items = new ArrayList<ItemStack>();

		if (value > 0)
			for (EmcValue v : EmcValues.values)
				if (v.getValue() != value)
					if ((value / v.getValue()) % 1 == 0)
						values.add(v);

		Collections.sort(values, new EmcValueSorter());

		for (EmcValue v : values)
			for (ItemStack i : v.getItems())
				items.add(i);

		return items.toArray(new ItemStack[items.size()]);
	}

	public static ItemStack[] filter(ItemStack[] items, EmcValueType type) {
		List<ItemStack> filteredItems = new ArrayList<ItemStack>();

		for (ItemStack is : items) {
			EmcValue v = getValueForStack(is);
			if (v != null)
				if (v.getType() == type)
					filteredItems.add(is);
		}

		return filteredItems.toArray(new ItemStack[filteredItems.size()]);
	}

	private static boolean registeredDefault = false;

	public static void registerDefault() {
		if (!registeredDefault) {
			registeredDefault = true;

			/* Blocks */

			setValueForOredictName("logWood", 32, EmcValueType.MATTER);
			setValueForOredictName("woodLog", 32, EmcValueType.MATTER);

			setValueForOredictName("plankWood", 8, EmcValueType.MATTER);
			setValueForOredictName("woodPlank", 8, EmcValueType.MATTER);

			setValueForOredictName("craftingtable", 32, EmcValueType.MATTER);
			setValueForOredictName("craftingTable", 32, EmcValueType.MATTER);

			setValueForOredictName("slabWood", 8, EmcValueType.MATTER);
			setValueForOredictName("woodSlab", 8, EmcValueType.MATTER);

			setValueForOredictName("stairWood", 12, EmcValueType.MATTER);
			setValueForOredictName("woodStair", 12, EmcValueType.MATTER);

			setValueForOredictName("saplingTree", 32, EmcValueType.MATTER);
			setValueForOredictName("treeSapling", 32, EmcValueType.MATTER);

			setValueForOredictName("leavesTree", 1, EmcValueType.MATTER);
			setValueForOredictName("treeLeaves", 1, EmcValueType.MATTER);

			setValueForOredictName("cobblestone", 1, EmcValueType.MATTER);
			setValueForOredictName("blockCobble", 1, EmcValueType.MATTER);

			setValueForOredictName("blockStone", 1, EmcValueType.MATTER);

			setValueForOredictName("oreDiamond", 8192, EmcValueType.MATTER);
			setValueForOredictName("oreEmerald", 8192, EmcValueType.MATTER);
			setValueForOredictName("oreGold", 2048, EmcValueType.MATTER);
			setValueForOredictName("oreIron", 256, EmcValueType.MATTER);
			setValueForOredictName("oreLapis", 864, EmcValueType.MATTER);
			setValueForOredictName("oreQuartz", 64, EmcValueType.MATTER);
			setValueForOredictName("oreRedstone", 64, EmcValueType.MATTER);

			/* Items */

			setValueForOredictName("ingotIron", 256, EmcValueType.MATTER);
			setValueForOredictName("ingotGold", 2048, EmcValueType.MATTER);
			setValueForOredictName("gemDiamond", 8192, EmcValueType.MATTER);
			setValueForOredictName("gemEmerald", 8192, EmcValueType.MATTER);

			setValueForStack(new ItemStack(Items.coal), 128, EmcValueType.FUEL);
			setValueForStack(new ItemStack(PEItems.alchemicalCoal), 512, EmcValueType.FUEL);
		}
	}

}
