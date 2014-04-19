package projecte.api.emc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.items.PEItems;
import cpw.mods.fml.common.Loader;

public class EmcRegistry {

	private static List<StackEmcValue> values = new ArrayList<StackEmcValue>();
	private static List<OredictEmcValue> oredictValues = new ArrayList<OredictEmcValue>();

	private static boolean hasReachedPostInit = false;
	private static Map<ItemStack, EmcData> assignedValues = new HashMap<ItemStack, EmcData>();
	private static List<EmcRecipe> recipes = new ArrayList<EmcRecipe>();

	public static void setValueForStack(ItemStack stack, double value, EmcValueType type) {
		if (hasReachedPostInit)
			return;

		StackEmcValue val = getStackValueForItem(stack);
		if (val.getType() == null)
			val.setType(type);
		val.addValue(new EmcValue(EmcValueSource.SPECIFIC, value));
	}

	public static void setValueForOredictName(String oredictName, double value, EmcValueType type) {
		if (hasReachedPostInit)
			return;

		OredictEmcValue val = getOredictValue(oredictName);
		val.value = new EmcValue(EmcValueSource.OREDICT, value);
		val.type = type;
	}

	public static StackEmcValue getStackValueForItem(ItemStack is) {
		for (StackEmcValue v : values)
			if (v.getItem().isItemEqual(is))
				return v;
		StackEmcValue val = new StackEmcValue(is);
		values.add(val);
		return val;
	}

	public static OredictEmcValue getOredictValue(String oredictName) {
		for (OredictEmcValue v : oredictValues)
			if (v.getName().equals(oredictName))
				return v;
		OredictEmcValue val = new OredictEmcValue(oredictName, 0, EmcValueType.MATTER);
		oredictValues.add(val);
		return val;
	}

	public static List<OredictEmcValue> getOredictValuesForStack(ItemStack is) {
		List<OredictEmcValue> values = new ArrayList<OredictEmcValue>();

		for (OredictEmcValue v : oredictValues) {
			for (ItemStack i : OreDictionary.getOres(v.getName())) {
				if (i.isItemEqual(is)) {
					values.add(v);
					break;
				}
			}
		}

		return values;
	}

	public static EmcData getValue(ItemStack is) {

		if (assignedValues.containsKey(is))
			return assignedValues.get(is);

		for (ItemStack i : assignedValues.keySet())
			if (is.isItemEqual(i))
				return assignedValues.get(i);

		return null;
	}

	public static ItemStack[] getItemsWithValue() {
		return getItemsWithValue(false);
	}

	private static ItemStack[] getItemsWithValue(boolean addNull) {
		if (assignedValues.size() == 0) {
			List<ItemStack> items = new ArrayList<ItemStack>();

			if (addNull)
				items.add(null);

			for (StackEmcValue v : values)
				items.add(v.getItem());

			for (OredictEmcValue v : oredictValues) {
				for (ItemStack is : OreDictionary.getOres(v.getName())) {
					boolean similar = false;

					for (ItemStack i : items) {
						if (i.isItemEqual(is)) {
							similar = true;
							break;
						}
					}

					if (!similar)
						items.add(is);
				}
			}

			ItemStack[] result = items.toArray(new ItemStack[] {});
			items.clear();
			return result;
		}
		return assignedValues.keySet().toArray(new ItemStack[] {});
	}

	public static EmcData[] filter(EmcData[] allData, EmcValueType valueType) {
		List<EmcData> data = new ArrayList<EmcData>();

		for (EmcData is : allData) {
			if (is.getType() == valueType)
				data.add(is);
		}

		EmcData[] result = data.toArray(new EmcData[] {});
		data.clear();
		return result;
	}

	public static ItemStack[] filter(ItemStack[] stacks, EmcValueType valueType) {
		List<ItemStack> items = new ArrayList<ItemStack>();

		for (ItemStack is : stacks) {
			EmcData data = getValue(is);
			if (data.getType() == valueType)
				items.add(is);
		}

		ItemStack[] result = items.toArray(new ItemStack[] {});
		items.clear();
		return result;
	}

	public static ItemStack[] filter(EmcValueType valueType) {
		return filter(getItemsWithValue(), valueType);
	}

	public static EmcData[] getDividersForValue(double value) {
		List<EmcData> values = new ArrayList<EmcData>();

		if (value > 0)
			for (ItemStack is : getItemsWithValue()) {
				EmcData v = getValue(is);
				if (v.getValue() != value)
					if ((value / v.getValue()) % 1 == 0)
						values.add(v);
			}

		Collections.sort(values, new EmcValueSorter());

		EmcData[] result = values.toArray(new EmcData[] {});
		values.clear();
		return result;
	}

	public static EmcData[] getItemsWithLessValue(double value) {
		List<EmcData> values = new ArrayList<EmcData>();

		if (value > 0)
			for (ItemStack is : getItemsWithValue()) {
				EmcData v = getValue(is);
				if (v.getValue() != value)
					if (v.getValue() < value)
						values.add(v);
			}

		Collections.sort(values, new EmcValueSorter());

		EmcData[] result = values.toArray(new EmcData[] {});
		values.clear();
		return result;
	}

	public static EmcData[] getItemsWithValue(double value) {
		List<EmcData> values = new ArrayList<EmcData>();

		if (value > 0)
			for (EmcData v : assignedValues.values())
				if (v.getValue() == value)
					values.add(v);

		EmcData[] result = values.toArray(new EmcData[] {});
		values.clear();
		return result;
	}
	
	public static EmcRecipe[] getAvailableRecipes(){
		return recipes.toArray(new EmcRecipe[]{});
	}
	
	public static EmcRecipe[] getRecipesForItem(ItemStack is){
		List<EmcRecipe> recipes = new ArrayList<EmcRecipe>();
		
		for(EmcRecipe r : getAvailableRecipes())
			if(r.getOutput().isItemEqual(is))
				recipes.add(r);
		
		EmcRecipe[] result = values.toArray(new EmcRecipe[] {});
		values.clear();
		return result;
	}

	public static void onReachPostInit() {
		if (Loader.instance().activeModContainer().getModId().equals(ModInfo.MOD_ID)) {
			hasReachedPostInit = true;
			ProjectE.log.log(Level.INFO, "Assigning EMC values. This can take a bit so don't worry if your log stops here for a while.");
			assignValues();
			ProjectE.log.log(Level.INFO, "Finished assigning EMC values.");
			ProjectE.log.log(Level.INFO, "Generating recipes. This can take a bit so don't worry if your log stops here for a while.");
			generateRecipes();
			ProjectE.log.log(Level.INFO, "Finished generating recipes. Generated: " + recipes.size());
		}
	}

	private static void assignValues() {
		ItemStack[] items = getItemsWithValue();
		for (ItemStack is : items) {
			List<EmcValue> values = new ArrayList<EmcValue>();
			EmcValueType type = null;

			StackEmcValue isVal = getStackValueForItem(is);
			values.addAll(isVal.getValues());
			type = isVal.getType();

			List<OredictEmcValue> oredictValues = getOredictValuesForStack(is);
			for (OredictEmcValue v : oredictValues) {
				if (type == null)
					type = v.getType();

				if (type == v.getType())
					values.add(v.getValue());
			}
			oredictValues.clear();

			double d = 0;
			for (EmcValue v : values) {
				double val = v.getValue(is);
				if (val < 0)
					continue;
				d += val;
			}
			if (values.size() == 0) {
				d = 0;
			} else {
				d /= values.size();
			}

			values.clear();

			EmcData data = new EmcData(is, type, d);
			assignedValues.put(is, data);
		}
	}

	private static void generateRecipes() {
		ItemStack[] items = getItemsWithValue(true);
		
		for(ItemStack is : items){
			EmcData val = getValue(is);
			EmcData[] less = getItemsWithLessValue(val.getValue());
			for(EmcData d : less){
				double v = 0;
				for(int i = 0; i < 9; i++){
					v += d.getValue();
					if(v == val.getValue()){
						ItemStack[] input = new ItemStack[i + 1];
						for(int j = 0; j < input.length; j++)
							input[j] = d.getItem().copy();
						recipes.add(new EmcRecipe(is.copy(), input));
						break;
					}
				}
			}
		}
	}

	private static boolean registeredDefault = false;

	public static void registerDefault() {
		if (!registeredDefault) {
			registeredDefault = true;

			// Blocks

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

			// Items 

			setValueForOredictName("ingotIron", 256, EmcValueType.MATTER);
			setValueForOredictName("ingotGold", 2048, EmcValueType.MATTER);
			setValueForOredictName("gemDiamond", 8192, EmcValueType.MATTER);
			setValueForOredictName("gemEmerald", 8192, EmcValueType.MATTER);

			setValueForOredictName("cloth", 48, EmcValueType.MATTER);

			setValueForStack(new ItemStack(Items.coal), 128, EmcValueType.FUEL);
			setValueForStack(new ItemStack(PEItems.alchemicalCoal), 512, EmcValueType.FUEL);
		}
	}

}
